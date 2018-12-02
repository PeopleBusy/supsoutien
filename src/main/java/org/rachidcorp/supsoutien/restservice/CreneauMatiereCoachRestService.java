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
import org.rachidcorp.supsoutien.entities.InscriptionEtudiantCreneau;
import org.rachidcorp.supsoutien.entities.Matiere;
import org.rachidcorp.supsoutien.entities.User;
import org.rachidcorp.supsoutien.metier.CreneauMatiereCoachMetier;
import org.rachidcorp.supsoutien.metier.DemandeSoutienMetier;
import org.rachidcorp.supsoutien.metier.InscriptionEtudiantCreneauMetier;
import org.rachidcorp.supsoutien.metier.MatiereMetier;
import org.rachidcorp.supsoutien.metier.UserMetier;
import org.rachidcorp.supsoutien.utils.Helpers;
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
@RequestMapping(value = "/api/creneaumatierecoach")
public class CreneauMatiereCoachRestService {

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

    private CreneauMatiereCoach cmc;

    @Autowired
    private SmtpMailSender sms;

    private String subject = "";

    private String body = "";
    
    private String url = Helpers.APP_URL;

    SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");

    public boolean checkCreneauMatiere(Long id, Date dateHeureD) {
        return cm.checkIfCreneauMatiereExist(id, dateHeureD) != null;
    }

    public boolean checkIfCoachMatiereUneFoisValiderByStaff(Long idCoach, Long idMatiere) {
        cmc = cm.checkIfCoachMatiereUneFoisValiderByStaff(idCoach, idMatiere);
        return cmc != null;
    }

    public void sendCreneauMsgToStaff(User u, Matiere m, User coach, Date dtdeb, Date dtfin, HttpServletRequest request) throws MessagingException {
        subject = "Séance de soutien " + m.getCodeMatiere() + " créee";
        body = "Bonjour " + u.getPrenom() + ", <br/><br/>";
        body += "Le coach " + coach.getNom() + " " + coach.getPrenom() + " a crée une séance de soutien pour la matière " + m.getCodeMatiere();
        body += " du " + dt.format(dtdeb) + " à " + dt.format(dtfin);
        body += "</br></br>Veuillez cliquer <a class='btn btn-primary' href='" + url + "'>sur ce lien</a> pour vous connecter et valider la séance.";

        sms.sendWithoutCC(u.getEmail(), subject, body);
    }

    public void sendCreneauMsgToEtudiant(User etud, Matiere m, User coach, Date dtdeb, Date dtfin, HttpServletRequest request) throws MessagingException {
        subject = "Séance de soutien " + m.getCodeMatiere() + " créee";
        body = "Bonjour " + etud.getPrenom() + ", <br/><br/>";
        body += "Le coach " + coach.getNom() + " " + coach.getPrenom() + " a crée une séance de soutien pour la matière " + m.getCodeMatiere();
        body += " du " + dt.format(dtdeb) + " à " + dt.format(dtfin);
        body += "</br></br>Veuillez cliquer <a class='btn btn-primary' href='" + url + "'>sur ce lien</a> pour vous connecter et vous inscire la séance.";

        sms.sendWithoutCC(etud.getEmail(), subject, body);
    }
    
    public void sendSeanceAnnuleeMsgToEtudiant(User etud, Matiere m, User coach, Date dtdeb, Date dtfin) throws MessagingException {

        subject = "Séance de soutien " + m.getCodeMatiere() + " annulée";
        body = "Bonjour " + etud.getPrenom() + ", <br/><br/>";
        body += "Le coach " + coach.getNom() + " " + coach.getPrenom() + " a annulé la séance de soutien pour la matière " + m.getCodeMatiere();
        body += " du " + dt.format(dtdeb) + " à " + dt.format(dtfin);

        sms.sendWithoutCC(etud.getEmail(), subject, body);
    }

    public void sendMatiereValideMsgToCoach(User u, Matiere m) throws MessagingException {

        subject = "Séance de soutien " + m.getCodeMatiere() + " validée";
        body = "Bonjour " + u.getPrenom() + ", <br/><br/>";
        body += "La matière " + m.getCodeMatiere() + " vous a été validée avec sur la plateforme supsoutien.";

        sms.sendWithoutCC(u.getEmail(), subject, body);
    }
    
    public void sendSeanceAnnuleeMsgToCoach(User u, Matiere m) throws MessagingException {

        subject = "Séance de soutien " + m.getCodeMatiere() + " annulée";
        body = "Bonjour " + u.getPrenom() + ", <br/><br/>";
        body += "Vous avez annulé la séance de soutien pour la matière " + m.getCodeMatiere();

        sms.sendWithoutCC(u.getEmail(), subject, body);
    }
    
    public void sendSeanceValideeMsgToCoach(User u, Matiere m , Date dtdeb, Date dtfin) throws MessagingException {

        subject = "Séance de soutien " + m.getCodeMatiere() + " validée";
        body = "Bonjour " + u.getPrenom() + ", <br/><br/>";
        body += "Vous avez validé la séance de soutien " + m.getCodeMatiere();
        body += " du " + dt.format(dtdeb) + " à " + dt.format(dtfin);

        sms.sendWithoutCC(u.getEmail(), subject, body);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public CreneauMatiereCoach saveCreneauMatiereCoach(@RequestBody CreneauMatiereCoach c, @PathVariable("id") Long id, 
            HttpServletRequest request) throws MessagingException {
        
        c.setCoachId(um.findUserById(id));

        if (checkCreneauMatiere(c.getCoachId().getId(), c.getDateHeureDebutSoutien())) {
            return null;

        } else {
            //Verifier si cette matiere a deja ete validee pour le coach
            if (checkIfCoachMatiereUneFoisValiderByStaff(c.getCoachId().getId(), c.getMatiereId().getId())) {
                c.setValiderParStaff(1);
                c.setDateValidationStaff(cmc.getDateValidationStaff());
                c.setStaffId(cmc.getStaffId());

                for (DemandeSoutien dm : dsm.findDemandesSoutiensEncoursByMatiere(c.getMatiereId().getId())) {
                    sendCreneauMsgToEtudiant(dm.getEtudiantId(), c.getMatiereId(), c.getCoachId(), c.getDateHeureDebutSoutien(), c.getDateHeureFinSoutien(), request);
                    dm.setEtatDemande(true);//traitee
                    dsm.updateDemandeSoutien(dm);
                }

            } else {
                c.setValiderParStaff(0);

            }
            c.setValiderParCoachFinSession(0);//0 = encours, 1 = confirmer, 2 = Annuler

            for (User u : um.getAllStaff()) {
                sendCreneauMsgToStaff(u, mm.findMatiereById(c.getMatiereId().getId()), c.getCoachId(), c.getDateHeureDebutSoutien(), c.getDateHeureFinSoutien(), request);
            }
            return cm.saveCreneauMatiereCoach(c);
        }

    }

    @RequestMapping(value = "/validerOuRejetterMatiereCoachByStaff/{staffId}", method = RequestMethod.PUT)
    public CreneauMatiereCoach validerOuRejetterMatiereCoachByStaff(@RequestBody CreneauMatiereCoach c, @PathVariable("staffId") Long staffId
            , HttpServletRequest request) throws MessagingException {
        
        if (c.getValiderParStaff() == 1) {
            //C'est valide, il faut rejetter
            c.setValiderParStaff(2); //0= encours, 1=valide, 2=rejetter

        } else {
            //C'est pas valide, il faut valider
            c.setValiderParStaff(1);

            sendMatiereValideMsgToCoach(c.getCoachId(), c.getMatiereId());
            
            for (DemandeSoutien dm : dsm.findDemandesSoutiensEncoursByMatiere(c.getMatiereId().getId())) {
                sendCreneauMsgToEtudiant(dm.getEtudiantId(), c.getMatiereId(), c.getCoachId(), c.getDateHeureDebutSoutien(), c.getDateHeureFinSoutien(), request);
                dm.setEtatDemande(true);//traitee
                dsm.updateDemandeSoutien(dm);
            }

        }

        c.setDateValidationStaff(new Date());
        c.setStaffId(um.findUserById(staffId));
        return cm.updateCreneauMatiereCoach(c);
    }

    @RequestMapping(value = "/validerOuAnnlerCreneauMatiereCoachFinSession/{confirm}/{nbPresent}", method = RequestMethod.PUT)
    public CreneauMatiereCoach validerOuAnnlerCreneauMatiereCoachFinSession(@RequestBody CreneauMatiereCoach c, @PathVariable("nbPresent") int nbPresent,
            @PathVariable("confirm") int confirm) throws MessagingException {

        c.setValiderParCoachFinSession(confirm);//0 = encours, 1 = valider fin session, 2 = Annuler seance
        
        if(confirm == 2) {
            //Seance annulee
            sendSeanceAnnuleeMsgToCoach(c.getCoachId(), c.getMatiereId());
            
            for (InscriptionEtudiantCreneau i : iecm.findInsriptionsEtudiantsByCreneau(c.getId())) {
                sendSeanceAnnuleeMsgToEtudiant(i.getEtudiantId(), c.getMatiereId(), c.getCoachId(), c.getDateHeureDebutSoutien(), c.getDateHeureFinSoutien());
            }
            
        }else if(confirm == 1){
            sendSeanceValideeMsgToCoach(c.getCoachId(), c.getMatiereId(), c.getDateHeureDebutSoutien(), c.getDateHeureFinSoutien());
        }
        
        c.setNbPresents(nbPresent);
        c.setDateHeureValidationCoach(new Date());
        return cm.updateCreneauMatiereCoach(c);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CreneauMatiereCoach updateCreneauMatiereCoach(@RequestBody CreneauMatiereCoach c, @PathVariable("id") Long id) {
        c.setId(id);
        return cm.updateCreneauMatiereCoach(c);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CreneauMatiereCoach findCreneauMatiereCoachById(@PathVariable("id") Long id) {
        return cm.findCreneauMatiereCoachById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCreneauMatiereCoachById(@PathVariable("id") Long id) {
//        CreneauMatiereCoach c = cm.findCreneauMatiereCoachById(id);
//        cm.
    }
    
    @RequestMapping(value = "/findAllSoutiensMatiereCoach", method = RequestMethod.GET)
    public Page<CreneauMatiereCoach> findAllSoutiensMatiereCoach(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return cm.findAllSoutiensMatiereCoach(page, size);
    }

    @RequestMapping(value = "/findAllCreneauxMatiereDisponibleByPromotion/{id}", method = RequestMethod.GET)
    public Page<CreneauMatiereCoach> findAllCreneauxMatiereDisponibleByPromotion(@PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return cm.findAllCreneauxMatiereDisponibleByPromotion(um.findUserById(id).getPromotionId().getId(), id, page, size);
    }

    @RequestMapping(value = "/findCreneauxMatiereCoachById/{id}", method = RequestMethod.GET)
    public Page<CreneauMatiereCoach> findCreneauxMatiereCoachById(@PathVariable("id") Long id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {

        return cm.findCreneauxMatiereCoachById(id, page, size);
    }

    @RequestMapping(value = "/findSoutiensMatiereCoach/{valide}", method = RequestMethod.GET)
    public Page<CreneauMatiereCoach> findSoutiensMatiereCoach(@PathVariable("valide") int valide,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return cm.findSoutiensMatiereCoach(valide, page, size);
    }

    @RequestMapping(value = "/findCreneauxDisponiblesByMatiere/{idMatiere}/{userId}", method = RequestMethod.GET)
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiere(@PathVariable("idMatiere") Long idMatiere,
            @PathVariable("userId") Long userId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return cm.findCreneauxDisponiblesByMatiere(idMatiere, userId, page, size);
    }
    
    @RequestMapping(value = "/findCreneauxDisponiblesByMatiereAsDeamnde/{idMatiere}/{userId}", method = RequestMethod.GET)
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiereAsDeamnde(@PathVariable("idMatiere") Long idMatiere,
            @PathVariable("userId") Long userId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return cm.findCreneauxDisponiblesByMatiereAsDeamnde(idMatiere, userId, page, size);
    }

}
