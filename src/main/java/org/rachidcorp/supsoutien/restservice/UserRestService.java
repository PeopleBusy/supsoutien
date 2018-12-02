/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.restservice;

import java.util.Date;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.rachidcorp.supsoutien.entities.User;
import org.rachidcorp.supsoutien.metier.UserMetier;
import org.rachidcorp.SmtpMailSender;
import org.rachidcorp.supsoutien.metier.PromotionMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.rachidcorp.supsoutien.metier.RoleMetier;
import org.rachidcorp.supsoutien.utils.Helpers;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Rachid ABDEL
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/users")
public class UserRestService {

    @Autowired
    private UserMetier um;

    @Autowired
    private RoleMetier rm;

    @Autowired
    private PromotionMetier pm;

    @Autowired
    private SmtpMailSender sms;

    private String subject = "";

    private String body = "";
    
    private String url = Helpers.APP_URL;

    BCryptPasswordEncoder passwordEncoder;

    public boolean checkIdBooster(Long idbooster) {
        User u = um.findUserByIdBooster(idbooster);
        return u != null;
    }

//    @RequestMapping(value = "/checkIdBooster/{idBooster}", method = RequestMethod.GET)
//    public boolean checkIfIdBoosterExist(@PathVariable("idBooster") Long idbooster) {
//        return checkIdBooster(idbooster);
//    }
    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User u) { //@RequestBody pour recupérer les données dans la requête et
        //les sérialiser puis les retourner au format JSON via @RestController.
        //Avec @RestController les reponses sont aussi retournées au format JS
        u.setDateCreation(new Date());

        passwordEncoder = new BCryptPasswordEncoder();
        u.setMotPasse(passwordEncoder.encode(u.getMotPasse()));

        return um.saveUser(u);
    }

    public void sendConfirmationMsgToUser(User u, HttpServletRequest request) throws MessagingException {

        url += "api/users/activerCompte/"+u.getIdBooster();
        subject = "Compte supsoutien crée";
        body = "Bonjour " + u.getPrenom() + ", <br/><br/>";
        body += "Vous avez crée un compte sur la plateforme Supsoutien.</br></br>";
        body += "Veuillez cliquer <a class='btn btn-primary' href='" + url + "'>sur ce lien</a> pour activer votre compte.";

        sms.sendWithoutCC(u.getEmail(), subject, body);
    }
    
    @RequestMapping(value = "/activerCompte/{idBooster}", method = RequestMethod.GET)
    public RedirectView activerCompte(@PathVariable("idBooster") Long idBooster, HttpServletRequest request) {
        
        User u = um.findUserByIdBooster(idBooster);
        u.setEtat(true);
        
        um.updateUser(u);
        
        return new RedirectView(url);
    }

    @RequestMapping(value = "/creerCompteAdmin", method = RequestMethod.POST)
    public User creerCompteAdmin(@RequestBody User u) {

        if (checkIdBooster(u.getIdBooster())) {
            //Idbooster existe deja (compte deja cree)
            return null;
        } else {
            passwordEncoder = new BCryptPasswordEncoder();
            u.setMotPasse(passwordEncoder.encode(u.getMotPasse()));

            u.setDateCreation(new Date());
            u.setRoleLib(rm.findRoleByRoleId(Long.valueOf(1))); //ROLE_ADMIN (Id = 1)
            return um.saveUser(u);
        }

    }

    @RequestMapping(value = "/creerCompteEtudiant/{promotionId}", method = RequestMethod.POST)
    public User creerCompteEtudiant(@RequestBody User u, @PathVariable("promotionId") Integer promotionId,
            HttpServletRequest request) throws MessagingException {

        if (checkIdBooster(u.getIdBooster())) {
            return null;
        } else {

            passwordEncoder = new BCryptPasswordEncoder();
            u.setMotPasse(passwordEncoder.encode(u.getMotPasse()));

            u.setPromotionId(pm.findPromotionById(promotionId));
            u.setEtat(false);//Compte Inactif

            u.setDateCreation(new Date());
            u.setRoleLib(rm.findRoleByRoleId(Long.valueOf(2))); //ROLE_ETUDIANT (Id = 2)

            sendConfirmationMsgToUser(u, request);

            return um.saveUser(u);
        }

    }

    @RequestMapping(value = "/creerCompteCoach/{promotionId}", method = RequestMethod.POST)
    public User creerCompteCoach(@RequestBody User u, @PathVariable("promotionId") Integer promotionId,
            HttpServletRequest request) throws MessagingException {

        if (checkIdBooster(u.getIdBooster())) {
            return null;
        } else {
            passwordEncoder = new BCryptPasswordEncoder();
            u.setMotPasse(passwordEncoder.encode(u.getMotPasse()));

            u.setPromotionId(pm.findPromotionById(promotionId));
            u.setEtat(false);//Compte Inactif

            u.setDateCreation(new Date());
            u.setRoleLib(rm.findRoleByRoleId(Long.valueOf(3))); //ROLE_COACH (Id = 3)

            sendConfirmationMsgToUser(u, request);

            return um.saveUser(u);
        }

    }

    @RequestMapping(value = "/creerCompteStaff", method = RequestMethod.POST)
    public User creerCompteStaff(@RequestBody User u,
            HttpServletRequest request) throws MessagingException {

        if (checkIdBooster(u.getIdBooster())) {
            return null;
        } else {
            passwordEncoder = new BCryptPasswordEncoder();
            u.setMotPasse(passwordEncoder.encode(u.getMotPasse()));

            u.setEtat(false);//Compte Inactif

            u.setDateCreation(new Date());
            u.setRoleLib(rm.findRoleByRoleId(Long.valueOf(4))); //ROLE_STAFF (Id = 4)

            sendConfirmationMsgToUser(u, request);

            return um.saveUser(u);
        }

    }

    @RequestMapping(value = "/activerOuDesactiverUser", method = RequestMethod.PUT)
    public User activerOuDesactiverUser(@RequestBody Long id) {
        User u = um.findUserById(id);
        if (u.getEtat()) {
            u.setEtat(false);
        } else {
            u.setEtat(true);
        }
        return um.updateUser(u);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User u, @PathVariable("id") Long id) {
        return um.updateUser(u);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User u) {
        um.deleteUser(u);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable("id") Long id) {
        User u = um.findUserById(id);
        um.deleteUser(u);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<User> listUsers(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return um.listUsers(page, size);
    }

    @RequestMapping(value = "/rechercherUserByIdBooster/{idBooster}", method = RequestMethod.GET)
    public Page<User> rechercherUserByIdBooster(@RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size, @PathVariable("idBooster") Long idBooster) {
        return um.rechercherUserByIdBooster(idBooster, new PageRequest(page, size));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("id") Long id) {
        return um.findUserById(id);
    }

    @RequestMapping(value = "/idbooster/{idBooster}", method = RequestMethod.GET)
    public User findUserByIdBooster(@PathVariable("idBooster") Long idBooster) {
        return um.findUserByIdBooster(idBooster);
    }

    @RequestMapping(value = "/getLoggedUser", method = RequestMethod.GET)
    public User getLoggedUser() {
        return um.getLoggedUser();
    }

}
