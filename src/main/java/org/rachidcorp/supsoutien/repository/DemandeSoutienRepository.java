/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.repository;

import java.util.List;
import org.rachidcorp.supsoutien.entities.DemandeSoutien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author rachid
 */
public interface DemandeSoutienRepository extends JpaRepository<DemandeSoutien, Long> {
    
    @Query("select d from DemandeSoutien d order by d.id desc")
    public Page<DemandeSoutien> findAllDemandesSoutiens(Pageable pageable);
    
    @Query("select d from DemandeSoutien d where d.etatDemande = :x order by d.id desc")
    public Page<DemandeSoutien> findDemandesSoutiensByEtat(@Param("x") boolean etatDemande, Pageable pageable);
    
    @Query("select d from DemandeSoutien d where d.coachId.id = :x order by d.id desc")
    public Page<DemandeSoutien> findDemandesSoutiensAssignToCoach(@Param("x") Long coachId, Pageable pageable);
    
    @Query("select d from DemandeSoutien d where d.etudiantId.id = :x order by d.id desc")
    public Page<DemandeSoutien> findDemandesSoutiensByEtudiant(@Param("x") Long etudiantId, Pageable pageable);
    
    @Query("select d from DemandeSoutien d where d.matiereId.id = :x AND d.etatDemande = false order by d.id desc")
    public List<DemandeSoutien> findDemandesSoutiensEncoursByMatiere(@Param("x") Long matiereId);
    
    @Query("select d from DemandeSoutien d where d.matiereId.id = :x AND d.etudiantId.id = :y AND d.etatDemande = false order by d.id desc")
    public DemandeSoutien findDemandeSoutiensEncoursByEtudiantMatiere(@Param("x") Long matiereId, @Param("y") Long etudiantId);
    
    @Query("select d from DemandeSoutien d where d.matiereId.promotionId.id = :x order by d.id desc")
    public Page<DemandeSoutien> findDemandesAllSoutiensByPromotion(@Param("x") Integer promotionId, Pageable pageable);
    
    @Query("select d from DemandeSoutien d where d.matiereId.promotionId.id = :x AND d.etatDemande = :y order by d.id desc")
    public List<DemandeSoutien> findDemandesSoutiensByEtatByPromotion(@Param("x") Integer promotionId, @Param("y") boolean etatDemande, Pageable pageable);
    
    
}
