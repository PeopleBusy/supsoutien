/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.repository;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Matiere;
import org.rachidcorp.supsoutien.entities.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author rachid
 */
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    
    @Query("select m from Matiere m order by m.promotionId asc")
    public Page<Matiere> findAllMatieres(Pageable p);
    public List<Matiere> findByPromotionId(Promotion promotionId);
}
