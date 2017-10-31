/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.repository;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author rachid
 */
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    
    @Query("select p from Promotion p where p.id <= :x")
    public List<Promotion> findPromotionsInferieures(@Param("x") Integer id);
}
