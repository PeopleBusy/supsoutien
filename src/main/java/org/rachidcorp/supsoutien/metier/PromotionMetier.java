/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Promotion;

/**
 *
 * @author rachid
 */
public interface PromotionMetier {
    public Promotion findPromotionById(Integer id);
    public List<Promotion> findAllPromotions();
    public List<Promotion> findPromotionsInferieures(Integer id);
}
