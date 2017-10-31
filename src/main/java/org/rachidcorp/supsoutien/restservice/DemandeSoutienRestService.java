/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.restservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.rachidcorp.SmtpMailSender;
import org.rachidcorp.supsoutien.entities.CreneauMatiereCoach;
import org.rachidcorp.supsoutien.entities.DemandeSoutien;
import org.rachidcorp.supsoutien.entities.Matiere;
import org.rachidcorp.supsoutien.entities.User;
import org.rachidcorp.supsoutien.metier.CreneauMatiereCoachMetier;
import org.rachidcorp.supsoutien.metier.DemandeSoutienMetier;
import org.rachidcorp.supsoutien.metier.InscriptionEtudiantCreneauMetier;
import org.rachidcorp.supsoutien.metier.MatiereMetier;
import org.rachidcorp.supsoutien.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rachid ABDEL
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/demandesoutien")
public class DemandeSoutienRestService {

    @Autowired
    private CreneauMatiereCoachMetier cm;

    @Autowired
    private UserMetier um;
    
    @Autowired
    private MatiereMetier mm;
    
    @Autowired
    private DemandeSoutienMetier dsm;
    
    @Autowired
    private InscriptionEtudiantCreneauMetier iecm;
    
    
    @Autowired
    private SmtpMailSender sms;

    private String subject = "";

    private String body = "";
    
    private String url = "";
    
    private SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");


    public void sendDemandeSoutienMsgToCoach(DemandeSoutien ds, User coach, HttpServletRequest request) throws MessagingException {
        
        url = request.getRequestURL().toString().split("supsoutien")[0] +"supsoutien/login";
        
        subject = "Demande de séance de soutien " + ds.getMatiereId().getCodeMatiere();
        body = "Bonjour " + coach.getPrenom() + ", <br/><br/>";
        body += "Vous avez été assigné une demande de soutien pour la matière " + ds.getMatiereId().getCodeMatiere();
  
        body += "</br></br>Veuillez cliquer <a class='btn btn-primary' href='" + url + "'>sur ce lien</a> pour vous connecter et créer une séance de soutien.";

        sms.sendWithoutCC(coach.getEmail(), subject, body);
    }
    
    public void sendDemandeSoutienMsgToEtudiant(User u, Matiere m, User coach, Date dtdeb, Date dtfin, HttpServletRequest request) throws MessagingException {
        
        url = request.getRequestURL().toString().split("supsoutien")[0] +"supsoutien/login" + u.getIdBooster();
        
        subject = "Demande de séance de soutien " + m.getCodeMatiere() + " créee";
        body = "Bonjour " + u.getPrenom() + ", <br/><br/>";
        body += "Le coach " + coach.getNom() + " " + coach.getPrenom() + " a crée une séance de soutien pour la matière " + m.getCodeMatiere();
        body += " du " + dt.format(dtdeb) + " à " + dt.format(dtfin);
        body += "</br></br>Veuillez cliquer <a class='btn btn-primary' href='" + url + "'>sur ce lien</a> pour vous connecter et valider la séance.";

        sms.sendWithoutCC(u.getEmail(), subject, body);
    }
    
    public void sendDemandeSoutienMsgToStaff(User staf, User etud, Matiere m) throws MessagingException {
        
        subject = "Demande de séance de soutien " + m.getCodeMatiere();
        body = "Bonjour " + staf.getPrenom() + ", <br/><br/>";
        body += "L'etudiant " + etud.getNom() + " " + etud.getPrenom() + " a fait une demande de soutien pour la matière " + m.getCodeMatiere();
        body += " sur la plateforme supsoutien.";

        sms.sendWithoutCC(staf.getEmail(), subject, body);
    }
    
    @RequestMapping(value = "/saveDemandeSoutien/{id}/{idMatiere}",method = RequestMethod.POST)
    public DemandeSoutien saveDemandeSoutien(@PathVariable("idMatiere") Long idMatiere, @PathVariable("id") Long id) throws MessagingException {
        
        User etud = um.findUserById(id);
        
        Matiere m = mm.findMatiereById(idMatiere);
        
        DemandeSoutien ds = new DemandeSoutien();
        ds.setEtatDemande(false);//false =  encours, true = traitee
        ds.setMatiereId(m);
        ds.setEtudiantId(etud);
        
        for (User staf : um.getAllStaff()) {
            sendDemandeSoutienMsgToStaff(staf, etud, m);
        }
        
        ds = dsm.saveDemandeSoutien(ds);
//        
//        CreneauMatiereCoach c = new CreneauMatiereCoach();
//        c.setMatiereId(m);
//        c.setValiderParCoachFinSession(0);
//        c.setValiderParStaff(1);
//        
//        cm.saveCreneauMatiereCoach(c);
        
        
        return ds;

    }

    @RequestMapping(value = "/assignerDemandeSoutienToCoach/{id}",method = RequestMethod.PUT)
    public DemandeSoutien assignerDemandeSoutienToCoach(@RequestBody DemandeSoutien ds, @PathVariable("id") Long id, 
            HttpServletRequest request) throws MessagingException {
        
        User coach = um.findUserById(id);
        
        ds.setEtatDemande(true);//false =  encours, true = traitee
        ds.setCoachId(coach);
        
        sendDemandeSoutienMsgToCoach(ds, coach, request);
        
        return dsm.updateDemandeSoutien(ds);

    }
    
    @RequestMapping(value = "/findDemandeSoutiensEncoursByEtudiantMatiere/{matiereId}/{etudiantid}",method = RequestMethod.GET)
    public boolean findDemandeSoutiensEncoursByEtudiantMatiere(@PathVariable("etudiantid") Long etudiantid,
            @PathVariable("matiereId") Long matiereId){
        
        return dsm.findDemandeSoutiensEncoursByEtudiantMatiere(matiereId, etudiantid) != null; //Aucune demande en cours de cette matiere pour cet etudiant
        

    }
    
    @RequestMapping(value = "/findAllDemandesSoutiens", method = RequestMethod.GET)
    public Page<DemandeSoutien> findAllDemandesSoutiens(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return dsm.findAllDemandesSoutiens(page, size);
    }
    
    @RequestMapping(value = "/findDemandesSoutiensByEtat/{etatDemande}", method = RequestMethod.GET)
    public Page<DemandeSoutien> findDemandesSoutiensByEtat(@PathVariable("etatDemande") boolean etatDemande,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return dsm.findDemandesSoutiensByEtat(etatDemande, page, size);
    }
    
    @RequestMapping(value = "/findDemandesSoutiensByPromotion/{etudiantid}", method = RequestMethod.GET)
    public Page<DemandeSoutien> findDemandesSoutiensByPromotion(@PathVariable("etudiantid") Long etudiantid,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return dsm.findDemandesAllSoutiensByPromotion(um.findUserById(etudiantid).getPromotionId().getId(), page, size);
    }

}
