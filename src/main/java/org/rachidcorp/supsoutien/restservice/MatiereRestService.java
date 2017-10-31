/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.restservice;

import java.util.ArrayList;
import java.util.List;
import org.rachidcorp.supsoutien.entities.Matiere;
import org.rachidcorp.supsoutien.entities.Promotion;
import org.rachidcorp.supsoutien.metier.MatiereMetier;
import org.rachidcorp.supsoutien.metier.PromotionMetier;
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
@RequestMapping(value = "/api/matieres")
public class MatiereRestService {

    @Autowired
    private MatiereMetier mm;

    @Autowired
    private UserMetier um;

    @Autowired
    private PromotionMetier pm;

    @RequestMapping(method = RequestMethod.POST)
    public Matiere saveMatiere(@RequestBody Matiere m) {
        return mm.saveMatiere(m);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Matiere updateMatiere(@RequestBody Matiere m, @PathVariable("id") Long id) {
        m.setId(id);
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteMatiereById(@PathVariable("id") Integer id) {
//        User u = um.findUserById(id);
//        um.deleteUser(u);
    }

    @RequestMapping(value = "/findAllMatieres", method = RequestMethod.GET)
    public Page<Matiere> findAllMatieres(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        return mm.findAllMatieres(page, size);
    }

    @RequestMapping(value = "/chargerMatieres", method = RequestMethod.GET)
    public Page<Matiere> chargerMatieres(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        Promotion p;
        Matiere m;
        List<Matiere> lm;
        
        //Promotion 1
        lm = new ArrayList<>();
        p = pm.findPromotionById(1);
        lm.add(new Matiere("1ADS", "Algorithmic in Python", p));
        lm.add(new Matiere("1ARI", "Arithmetic and Cryptography", p));
        lm.add(new Matiere("1CNA", "CCNA Exploration - Modules 1  & 2-part1 et 2", p));
        lm.add(new Matiere("1CPA", "Computer Architecture", p));
        lm.add(new Matiere("1ENG", "English Debates", p));
        lm.add(new Matiere("1GCC", "C Language", p));
        lm.add(new Matiere("1SET", "Set Theory", p));
        lm.add(new Matiere("1LAL", "Linear Algebra", p));
        lm.add(new Matiere("1LAW", "IT Law - Internet Law and Intellectual Property", p));
        lm.add(new Matiere("1LIN", "Linux Technologies - System Fundamentals", p));
        lm.add(new Matiere("1MER", "Merise modeling for databases", p));
        lm.add(new Matiere("1MGT", "Enter the digital world", p));
        lm.add(new Matiere("1MSA", "Windows Server 2012 Introduction", p));
        lm.add(new Matiere("1ORC", "SQL Fundamentals", p));
        lm.add(new Matiere("1OSS", "Operating Systems Fundamental", p));
        lm.add(new Matiere("1SEC", "Information System Security", p));
        lm.add(new Matiere("1WEB", "HTML & JavaScript - User Interface", p));
        
        mm.saveListMatiere(lm);
        
        //Promotion 2
        lm = new ArrayList<>();
        p = pm.findPromotionById(2);
        lm.add(new Matiere("2ADS", "Advanced Algorithmics", p));
        lm.add(new Matiere("2CMP", "Compilation - Languages and translators", p));
        lm.add(new Matiere("2CNB", "CCNA Routing & Switching Part 2   (1/2)", p));
        lm.add(new Matiere("2MSA", "Microsoft Windows 2012 Administration", p));
        lm.add(new Matiere("2ENG", "English Debates", p));
        lm.add(new Matiere("2GRA", "Graphs theory", p));
        lm.add(new Matiere("2JVA", "Java Standard Edition", p));
        lm.add(new Matiere("2JWB", "Web Strategy", p));
        lm.add(new Matiere("2LAW", "IT Law -Network Administration and Fraud", p));
        lm.add(new Matiere("2LIN", "Linux Technologies - Edge computing", p));
        lm.add(new Matiere("2MGT", "Modeling for Business Analysis", p));
        lm.add(new Matiere("2OOP", "Object Oriented Programming", p));
        lm.add(new Matiere("2ORC", "PL/SQL Fundamentals", p));
        lm.add(new Matiere("2CPP", "C++ Language", p));
        lm.add(new Matiere("2NET", "Microsoft .NET Foundations and Enterprise Applications", p));
        lm.add(new Matiere("2PBS", "Probabilities & Statistics", p));
        lm.add(new Matiere("2UML", "UML", p));
        lm.add(new Matiere("2WEB", "Web programming with PHP", p));
        
        mm.saveListMatiere(lm);
        
        //Promotion 3
        lm = new ArrayList<>();
        p = pm.findPromotionById(3);
        lm.add(new Matiere("3AIT", "Artificial Intelligence - Functional programming", p));
        lm.add(new Matiere("3AND", "Android Application Dev & Programming", p));
        lm.add(new Matiere("3APL", "Swift and Cocoa Development", p));
        lm.add(new Matiere("3ASP", "Building Web Applications with ASP.NET MVC ", p));
        lm.add(new Matiere("3CNS", "CCNA Security", p));
        lm.add(new Matiere("3ENG", "English Debates", p));
        lm.add(new Matiere("3JVA", "Enterprise Application Development", p));
        lm.add(new Matiere("3LAW", "Labour Law and IT", p));
        lm.add(new Matiere("3LIN", "Data Center Solutions", p));
        lm.add(new Matiere("3MET", "ITIL Foundations", p));
        lm.add(new Matiere("3MGT", "IT Management 3 - Project Management", p));
        lm.add(new Matiere("3MSA", "Windows Server 2012 Active Directory Domain Services", p));
        lm.add(new Matiere("3ORC", "Oracle Dabatabase Administration part1 et 2", p));
        lm.add(new Matiere("3WEB", "Advanced Web Programming with NodeJS", p));
        lm.add(new Matiere("3WIN", "Microsoft Windows Universal Applications Development", p));

        mm.saveListMatiere(lm);
        
        //Promotion 4
        lm = new ArrayList<>();
        p = pm.findPromotionById(4);
        lm.add(new Matiere("4BIS", "BI fundamentals", p));
        lm.add(new Matiere("4CLD", "Introduction to Cloud Computing Technologies Training", p));
        lm.add(new Matiere("4AIT", "Artificial Intelligence - Logic Programming", p));
        lm.add(new Matiere("4ENG", "English Debates", p));
        lm.add(new Matiere("4EPS", "Digital Enterpreneurship", p));
        lm.add(new Matiere("4ERP", "ERP Solution", p));
        lm.add(new Matiere("4JVA", "Java EE - Enterprise programming", p));
        lm.add(new Matiere("4LAW", "IT Law- Personal Data Protection", p));
        lm.add(new Matiere("4MET", "Agile Software Development with Scrum", p));
        lm.add(new Matiere("4MGT", "Finance & Accounting", p));
        lm.add(new Matiere("4MOS", "Microsoft SharePoint 2013", p));
        lm.add(new Matiere("4MSE", "Planning, deploying , managing Exchange Server 2013", p));
        lm.add(new Matiere("4VIP", "VoIP", p));
        lm.add(new Matiere("4VTZ", "Deploying Vmware vSphere", p));

        mm.saveListMatiere(lm);
        
        //Promotion 5
        lm = new ArrayList<>();
        p = pm.findPromotionById(5);
        lm.add(new Matiere("5BCP", "Disaster Recovery Planning : Ensuring Business Continuity", p));
        lm.add(new Matiere("5BIS", "BI Solutions", p));
        lm.add(new Matiere("5DAT", "Big Data Fundamentals", p));
        lm.add(new Matiere("5EMI", "Emotional Intelligence : Achieving Leadership Success", p));
        lm.add(new Matiere("5ENG", "English Debates", p));
        lm.add(new Matiere("5LAW", "IT Contract Law", p));
        lm.add(new Matiere("5VTZ", "Application Virtualization", p));
        lm.add(new Matiere("5MGT", "IT Management 5 / IT Performance", p));
        lm.add(new Matiere("5ORC", "Datawarehouse", p));
        lm.add(new Matiere("5MET", "CobiT 5 Foundation Training", p));
        lm.add(new Matiere("5TGF", "Preparing for TOGAF Accreditation", p));

        mm.saveListMatiere(lm);

       
        return mm.findAllMatieres(page, size);
    }

    @RequestMapping(value = "/findMatieresByPromotion/{idPromotion}", method = RequestMethod.GET)
    public List<Matiere> findMatieresByPromotion(@PathVariable("idPromotion") Integer idPromotion) {
        return mm.findMatieresByPromotion(pm.findPromotionById(idPromotion));
    }

    @RequestMapping(value = "/findMatieresByEtudiantPromotion/{id}", method = RequestMethod.GET)
    public List<Matiere> findMatieresByEtudiantPromotion(@PathVariable("id") Long id) {
        return mm.findMatieresByPromotion(pm.findPromotionById(um.findUserById(id).getPromotionId().getId()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Matiere findMatiereById(@PathVariable("id") Long id) {
        return mm.findMatiereById(id);
    }

}
