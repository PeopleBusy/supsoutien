/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier.impl;

import java.util.Date;
import org.rachidcorp.supsoutien.entities.CreneauMatiereCoach;
import org.rachidcorp.supsoutien.metier.CreneauMatiereCoachMetier;
import org.rachidcorp.supsoutien.repository.CreneauMatiereCoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author rachid
 */
@Service
public class CreneauMatiereCoachMetierImpl implements CreneauMatiereCoachMetier {
    
    @Autowired
    private CreneauMatiereCoachRepository cmcr;

    @Override
    public Page<CreneauMatiereCoach> findCreneauxMatiereCoachById(Long id, int page, int size) {
        return cmcr.findCreneauxMatiereCoachById(id, new PageRequest(page, size));
    }

    @Override
    public CreneauMatiereCoach saveCreneauMatiereCoach(CreneauMatiereCoach c) {
        return cmcr.save(c);
    }

    @Override
    public CreneauMatiereCoach updateCreneauMatiereCoach(CreneauMatiereCoach c) {
        return cmcr.saveAndFlush(c);
    }

    @Override
    public void deleteCreneauMatiereCoachById(Long Id) {
        cmcr.delete(Id);
    }

    @Override
    public CreneauMatiereCoach checkIfCreneauMatiereExist(Long id, Date dateHeureD) {
        return cmcr.checkIfCreneauMatiereExist(id, dateHeureD);
    }

    @Override
    public CreneauMatiereCoach findCreneauMatiereCoachById(Long id) {
        return cmcr.findOne(id);
    }

    @Override
    public CreneauMatiereCoach checkIfCoachMatiereUneFoisValiderByStaff(Long idCoach, Long idMatiere) {
        return cmcr.checkIfCoachMatiereUneFoisValiderByStaff(idCoach, idMatiere);
    }

    @Override
    public Page<CreneauMatiereCoach> findSoutiensMatiereCoach(int valide, int page, int size) {
        return cmcr.findSoutiensMatiereCoach(valide, new PageRequest(page, size));
    }

    @Override
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiere(Long idMatiere, Long userid, int page, int size) {
        return cmcr.findCreneauxDisponiblesByMatiere(idMatiere, userid, new PageRequest(page, size));
    }

    @Override
    public Page<CreneauMatiereCoach> findAllCreneauxMatiereDisponibleByPromotion(Integer promotionId, Long userid, int page, int size) {
        return cmcr.findAllCreneauxMatiereDisponibleByPromotion(promotionId, userid, new PageRequest(page, size));
    }

    @Override
    public Page<CreneauMatiereCoach> findAllSoutiensMatiereCoach(int page, int size) {
        return cmcr.findAllSoutiensMatiereCoach(new PageRequest(page, size));
    }

    @Override
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiereAsDeamnde(Long idMatiere, Long userid, int page, int size) {
        return cmcr.findCreneauxDisponiblesByMatiereAsDeamnde(idMatiere, userid, new PageRequest(page, size));
    }
}
