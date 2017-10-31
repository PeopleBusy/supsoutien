/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.restservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.rachidcorp.SmtpMailSender;
import org.rachidcorp.supsoutien.entities.CreneauMatiereCoach;
import org.rachidcorp.supsoutien.entities.InscriptionEtudiantCreneau;
import org.rachidcorp.supsoutien.entities.Matiere;
import org.rachidcorp.supsoutien.entities.User;
import org.rachidcorp.supsoutien.metier.CreneauMatiereCoachMetier;
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
@RequestMapping(value = "/api/inscriptionetudiantcreneau")
public class InscriptionEtudiantCreneauRestService {

    @Autowired
    private MatiereMetier mm;

    @Autowired
    private UserMetier um;

    @Autowired
    private CreneauMatiereCoachMetier cm;

    @Autowired
    private InscriptionEtudiantCreneauMetier iecm;

    @Autowired
    private SmtpMailSender sms;
    
    private String subject = "";

    private String body = "";
    
    private String url = "";
    
    private SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
    
    public void sendInscriptionMsgToCoach(User et, CreneauMatiereCoach c, HttpServletRequest request) throws MessagingException {
        
        url = request.getRequestURL().toString().split("supsoutien")[0] +"supsoutien/login";
        
        subject = "Demande d'inscription à la séance de soutien " + c.getMatiereId().getCodeMatiere();
        body = "Bonjour " + c.getCoachId().getPrenom() + ", <br/><br/>";
        body += "L'étudiant <span style='font-weight: bold;'>" + et.getNom() + " " + et.getPrenom() + "</span> s'est inscrit à";
        body += " votre séance de soutien <span style='font-weight: bold;'>" + c.getMatiereId().getCodeMatiere() + "</span> du </br>";
        body += "<span style='font-weight: bold;'>" + dt.format(c.getDateHeureDebutSoutien()) + " à " + dt.format(c.getDateHeureFinSoutien()) + "</span>. </br></br>";
        body += "Veuillez vous connecter à la plateforme <a href='" + url + "'>SUPSOUTIEN</a> pour confirmer la séance.";

        sms.sendWithoutCC(c.getCoachId().getEmail(), subject, body);
    }
    
    public void sendInscriptionMsgToEtudiant(User et, CreneauMatiereCoach c) throws MessagingException {
        
        subject = "Inscription à la séance de soutien " + c.getMatiereId().getCodeMatiere();
        body = "Bonjour " + et.getPrenom() + ", <br/><br/>";
        body += "Vous vous êtes inscrit à la séance de soutien " + c.getMatiereId().getCodeMatiere();
        body += "<span style='font-weight: bold;'> du " + dt.format(c.getDateHeureDebutSoutien()) + " à " + dt.format(c.getDateHeureFinSoutien()) + "</span>. <br/><br/>";
        body += "Un message de confirmation vous sera envoyé dès que le coach aura confirmé votre inscription à la séance sur la plateforme.";

        sms.sendWithoutCC(et.getEmail(), subject, body);
    }
    
    public void sendConfirmationInscriptionMsgToEtudiant(User et, CreneauMatiereCoach c) throws MessagingException {
        
        subject = "Séance de soutien " + c.getMatiereId().getCodeMatiere() + " confirmée";
        body = "Bonjour " + et.getPrenom() + ", <br/><br/>";
        body += "Le coach " + c.getCoachId().getNom() + " " + c.getCoachId().getPrenom() + " a confirmé votre inscription à la séance de soutien " + c.getMatiereId().getCodeMatiere();
        body += "<span style='font-weight: bold;'> du " + dt.format(c.getDateHeureDebutSoutien()) + " à " + dt.format(c.getDateHeureFinSoutien()) + "</span>. ";

        sms.sendWithoutCC(et.getEmail(), subject, body);
    }

    @RequestMapping(value = "/saveInsriptionCreneauxMatiere/{userId}", method = RequestMethod.POST)
    public InscriptionEtudiantCreneau saveInscriptionEtudiantCreneau(@RequestBody CreneauMatiereCoach c,
            @PathVariable("userId") Long userId, HttpServletRequest request) throws MessagingException {

        InscriptionEtudiantCreneau i = new InscriptionEtudiantCreneau();
        i.setEtudiantId(um.findUserById(userId));
        i.setCreneauId(c);
        
        sendInscriptionMsgToCoach(i.getEtudiantId(), c, request);
        sendInscriptionMsgToEtudiant(i.getEtudiantId(), c);
        
        i.setConfirmMailSendByEtudiant(true);
        i.setContenuMailEtudiant(body);//

        i.setConfirmParCoach(0);//0 = encours, 1 = confirmer, 2 = Annuler
        i.setDateHeureInscription(new Date());
        
        c.setNbInscrits(c.getNbInscrits() + 1);
        cm.saveCreneauMatiereCoach(c);

        return iecm.saveInscriptionEtudiantCreneau(i);
    }

    @RequestMapping(value = "/updateInsriptionCreneauxMatiere/{id}/{confirm}", method = RequestMethod.PUT)
    public InscriptionEtudiantCreneau updateInscriptionEtudiantCreneau(@RequestBody InscriptionEtudiantCreneau i,
            @PathVariable("id") Long id, @PathVariable("confirm") int confirm) throws MessagingException {
        
        i.setId(id);
        i.setConfirmParCoach(confirm);//0 = encours, 1 = confirmer, 2 = Annuler

        if (confirm == 1) {
            
            sendConfirmationInscriptionMsgToEtudiant(i.getEtudiantId(), i.getCreneauId());
            
            i.setContenuMailCoach(body);
            
        } else {
            //2
        }
        //
        return iecm.updateInscriptionEtudiantCreneau(i);
    }

    @RequestMapping(value = "/findInsriptionCreneauxMatiereByEtudiant/{userId}", method = RequestMethod.GET)
    public Page<InscriptionEtudiantCreneau> findInsriptionCreneauxMatiereByEtudiant(@PathVariable("userId") Long userId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return iecm.findInsriptionCreneauxMatiereByEtudiant(userId, page, size);
    }
    
    @RequestMapping(value = "/findAllInsriptionCreneauxMatiere", method = RequestMethod.GET)
    public Page<InscriptionEtudiantCreneau> findAllInsriptionCreneauxMatiere(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return iecm.findAllInsriptionCreneauxMatiere(page, size);
    }

    @RequestMapping(value = "/findInsriptionsEtudiantsByCreneau/{id}", method = RequestMethod.GET)
    public List<InscriptionEtudiantCreneau> findInsriptionsEtudiantsByCreneau(@PathVariable("id") Long id) {
        return iecm.findInsriptionsEtudiantsByCreneau(id);
    }
}
