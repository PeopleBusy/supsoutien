/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.restservice;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Promotion;
import org.rachidcorp.supsoutien.metier.PromotionMetier;
import org.rachidcorp.supsoutien.metier.UserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rachid ABDEL
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/promotions")
public class PromotionRestService {

    @Autowired
    private PromotionMetier pm;
    
    @Autowired
    private UserMetier um;


    @RequestMapping(method = RequestMethod.POST)
    public Promotion savePromotion(@RequestBody Promotion p) { 
        return null;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Promotion updatePromotion(@RequestBody Promotion p, @PathVariable("id") Integer id) {
        p.setId(id);
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody Promotion p) {
        //um.deleteUser(u);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePromotionById(@PathVariable("id") Integer id) {
//        User u = um.findUserById(id);
//        um.deleteUser(u);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Promotion> listAllPromotions() {
        return pm.findAllPromotions();
    }
    
    @RequestMapping(value = "/findPromotionsInferieures/{id}", method = RequestMethod.GET)
    public List<Promotion> findPromotionsInferieures(@PathVariable("id") Long id) {
        return pm.findPromotionsInferieures(um.findUserById(id).getPromotionId().getId());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Promotion findPromotionById(@PathVariable("id") Integer id) {
        return pm.findPromotionById(id);
    }

}
