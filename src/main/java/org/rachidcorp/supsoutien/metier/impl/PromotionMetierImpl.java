/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier.impl;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Promotion;
import org.rachidcorp.supsoutien.metier.PromotionMetier;
import org.rachidcorp.supsoutien.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rachid
 */
@Service
public class PromotionMetierImpl implements PromotionMetier{
    
    @Autowired
    private PromotionRepository pr;
    
    @Override
    public Promotion findPromotionById(Integer id) {
        return pr.findOne(id);
    }

    @Override
    public List<Promotion> findAllPromotions() {
        return pr.findAll();
    }

    @Override
    public List<Promotion> findPromotionsInferieures(Integer id) {
        return pr.findPromotionsInferieures(id);
    }

}
