/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier.impl;

import java.util.List;
import org.rachidcorp.supsoutien.entities.DemandeSoutien;
import org.rachidcorp.supsoutien.metier.DemandeSoutienMetier;
import org.rachidcorp.supsoutien.repository.DemandeSoutienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author rachid
 */
@Service
public class DemandeSoutienMetierImpl implements DemandeSoutienMetier {
    
    @Autowired
    private DemandeSoutienRepository dsr;

    @Override
    public DemandeSoutien findDemandeSoutienById(Long id) {
        return dsr.findOne(id);
    }

    @Override
    public DemandeSoutien saveDemandeSoutien(DemandeSoutien d) {
        return dsr.save(d);
    }
    
    @Override
    public DemandeSoutien updateDemandeSoutien(DemandeSoutien d) {
        return dsr.saveAndFlush(d);
    }

    @Override
    public Page<DemandeSoutien> findAllDemandesSoutiens(int page, int size) {
        return dsr.findAllDemandesSoutiens(new PageRequest(page, size));
    }

    @Override
    public Page<DemandeSoutien> findDemandesSoutiensByEtat(boolean etatDemande, int page, int size) {
        return dsr.findDemandesSoutiensByEtat(etatDemande, new PageRequest(page, size));
    }

    @Override
    public Page<DemandeSoutien> findDemandesSoutiensAssignToCoach(Long coachId, int page, int size) {
        return dsr.findDemandesSoutiensAssignToCoach(coachId, new PageRequest(page, size));
    }

    @Override
    public Page<DemandeSoutien> findDemandesSoutiensByEtudiant(Long etudiantId, int page, int size) {
        return dsr.findDemandesSoutiensByEtudiant(etudiantId, new PageRequest(page, size));
    }

    @Override
    public List<DemandeSoutien> findDemandesSoutiensEncoursByMatiere(Long matiereId) {
        return dsr.findDemandesSoutiensEncoursByMatiere(matiereId);
    }

    @Override
    public DemandeSoutien findDemandeSoutiensEncoursByEtudiantMatiere(Long matiereId, Long etudiantId) {
        return dsr.findDemandeSoutiensEncoursByEtudiantMatiere(matiereId, etudiantId);
    }

    @Override
    public List<DemandeSoutien> findDemandesSoutiensByEtatByPromotion(Integer promotionId, boolean etatDemande, int page, int size) {
        return dsr.findDemandesSoutiensByEtatByPromotion(promotionId, etatDemande, new PageRequest(page, size));
    }

    @Override
    public Page<DemandeSoutien> findDemandesAllSoutiensByPromotion(Integer promotionId, int page, int size) {
        return dsr.findDemandesAllSoutiensByPromotion(promotionId, new PageRequest(page, size));
    }

}
