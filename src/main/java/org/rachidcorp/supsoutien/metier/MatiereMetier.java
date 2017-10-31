/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Matiere;
import org.rachidcorp.supsoutien.entities.Promotion;
import org.springframework.data.domain.Page;

/**
 *
 * @author rachid
 */
public interface MatiereMetier {
    public Matiere saveMatiere(Matiere m);
    public void saveListMatiere(List<Matiere> lm);
    public Page<Matiere> findAllMatieres(int page, int size);
    public Matiere findMatiereById(Long id);
    public List<Matiere> findMatieresByPromotion(Promotion promotionId);
}
