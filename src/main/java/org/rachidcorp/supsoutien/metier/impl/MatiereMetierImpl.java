/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier.impl;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Matiere;
import org.rachidcorp.supsoutien.entities.Promotion;
import org.rachidcorp.supsoutien.metier.MatiereMetier;
import org.rachidcorp.supsoutien.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author rachid
 */
@Service
public class MatiereMetierImpl implements MatiereMetier {

    @Autowired
    private MatiereRepository mr;

    @Override
    public Matiere findMatiereById(Long id) {
        return mr.findOne(id);
    }

    @Override
    public List<Matiere> findMatieresByPromotion(Promotion promotionId) {
        return mr.findByPromotionId(promotionId);
    }

    @Override
    public Matiere saveMatiere(Matiere m) {
        return mr.saveAndFlush(m);
    }

    @Override
    public Page<Matiere> findAllMatieres(int page, int size) {
        return mr.findAllMatieres(new PageRequest(page, size));
    }

    @Override
    public void saveListMatiere(List<Matiere> lm) {
        lm.forEach((m) -> {
            mr.save(m);
        });
    }

}
