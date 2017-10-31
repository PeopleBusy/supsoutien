/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier;

import java.util.List;
import org.rachidcorp.supsoutien.entities.DemandeSoutien;
import org.springframework.data.domain.Page;

/**
 *
 * @author rachid
 */
public interface DemandeSoutienMetier {
    
    public DemandeSoutien findDemandeSoutienById(Long id);
    
    public DemandeSoutien saveDemandeSoutien(DemandeSoutien d);
    
    public DemandeSoutien updateDemandeSoutien(DemandeSoutien d);
 
    public Page<DemandeSoutien> findAllDemandesSoutiens(int page, int size);
    
    public Page<DemandeSoutien> findDemandesSoutiensByEtat(boolean etatDemande, int page, int size);
    
    public Page<DemandeSoutien> findDemandesSoutiensAssignToCoach(Long coachId, int page, int size);
    
    public Page<DemandeSoutien> findDemandesSoutiensByEtudiant(Long etudiantId, int page, int size);
    
    public List<DemandeSoutien> findDemandesSoutiensEncoursByMatiere(Long matiereId);
    
    public DemandeSoutien findDemandeSoutiensEncoursByEtudiantMatiere(Long matiereId, Long etudiantId);
    
    public Page<DemandeSoutien> findDemandesAllSoutiensByPromotion(Integer promotionId, int page, int size);

    public List<DemandeSoutien> findDemandesSoutiensByEtatByPromotion(Integer promotionId, boolean etatDemande, int page, int size);
    
}
