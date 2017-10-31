/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.repository;

import java.util.Date;
import org.rachidcorp.supsoutien.entities.CreneauMatiereCoach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author rachid
 */
public interface CreneauMatiereCoachRepository extends JpaRepository<CreneauMatiereCoach, Long> {
    
    @Query("select c from CreneauMatiereCoach c where c.coachId.id like :x order by c.id desc")
    public Page<CreneauMatiereCoach> findCreneauxMatiereCoach(@Param("x") Long id, Pageable pageable);
    
    @Query("select c from CreneauMatiereCoach c where c.coachId.id like :x order by c.id desc")
    public Page<CreneauMatiereCoach> findCreneauxMatiereCoachById(@Param("x") Long id, Pageable pageable);
    
    @Query("select c from CreneauMatiereCoach c where c.matiereId.promotionId.id = :x AND c.validerParStaff = 1 AND c.validerParCoachFinSession = 0 AND c.id NOT IN (SELECT i.creneauId.id FROM InscriptionEtudiantCreneau i WHERE i.etudiantId.id = :w) order by c.id desc")
    public Page<CreneauMatiereCoach> findAllCreneauxMatiereDisponibleByPromotion(@Param("x") Integer promotionId, @Param("w") Long userid, Pageable pageable);
    
    @Query("select c from CreneauMatiereCoach c where c.coachId.id like :w AND c.dateHeureDebutSoutien = :y")
    public CreneauMatiereCoach checkIfCreneauMatiereExist(@Param("w") Long id, @Param("y") Date dateHeureD);
    
    @Query("select MIN(c) from CreneauMatiereCoach c where c.coachId.id like :w AND c.matiereId.id = :y AND c.validerParStaff = 1")
    public CreneauMatiereCoach checkIfCoachMatiereUneFoisValiderByStaff(@Param("w") Long idCoach, @Param("y") Long idMatiere);
    
    @Query("select c from CreneauMatiereCoach c where c.matiereId.id = :y AND c.validerParStaff = 1 AND c.dateHeureDebutSoutien >= CURRENT_DATE AND c.validerParCoachFinSession = 0 AND c.id NOT IN (SELECT i.creneauId.id FROM InscriptionEtudiantCreneau i WHERE i.etudiantId.id = :w)")
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiere(@Param("y") Long idMatiere, @Param("w") Long userid, Pageable pageable);
    
    @Query("select c from CreneauMatiereCoach c where c.matiereId.id = :y AND c.validerParStaff = 1  AND c.validerParCoachFinSession = 0 AND c.id NOT IN (SELECT i.creneauId.id FROM InscriptionEtudiantCreneau i WHERE i.etudiantId.id = :w)")
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiereAsDeamnde(@Param("y") Long idMatiere, @Param("w") Long userid, Pageable pageable);
    
    @Query("select c from CreneauMatiereCoach c where c.validerParStaff = :x")
    public Page<CreneauMatiereCoach> findSoutiensMatiereCoach(@Param("x") int valide, Pageable pageable);
    
    @Query("select c from CreneauMatiereCoach c where c.coachId IS NOT NULL order by c.id desc")
    public Page<CreneauMatiereCoach> findAllSoutiensMatiereCoach(Pageable pageable);
    
}
