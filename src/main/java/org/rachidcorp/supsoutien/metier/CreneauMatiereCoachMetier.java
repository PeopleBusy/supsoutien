/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier;

import java.util.Date;
import org.rachidcorp.supsoutien.entities.CreneauMatiereCoach;
import org.springframework.data.domain.Page;

/**
 *
 * @author rachid
 */
public interface CreneauMatiereCoachMetier {
    public CreneauMatiereCoach findCreneauMatiereCoachById(Long id);
    
    public CreneauMatiereCoach saveCreneauMatiereCoach(CreneauMatiereCoach c);
    
    public CreneauMatiereCoach updateCreneauMatiereCoach(CreneauMatiereCoach c);
    
    public void deleteCreneauMatiereCoachById(Long Id);
    
    public Page<CreneauMatiereCoach> findCreneauxMatiereCoachById(Long id, int page, int size);
    
    public CreneauMatiereCoach checkIfCreneauMatiereExist(Long id, Date dateHeureD);
    
    public CreneauMatiereCoach checkIfCoachMatiereUneFoisValiderByStaff(Long idCoach, Long idMatiere);
    
    public Page<CreneauMatiereCoach> findSoutiensMatiereCoach(int valide, int page, int size);
    
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiere(Long idMatiere, Long userid, int page, int size);
    
    public Page<CreneauMatiereCoach> findCreneauxDisponiblesByMatiereAsDeamnde(Long idMatiere, Long userid, int page, int size);
    
    public Page<CreneauMatiereCoach> findAllCreneauxMatiereDisponibleByPromotion(Integer promotionId, Long userid, int page, int size);
    
    public Page<CreneauMatiereCoach> findAllSoutiensMatiereCoach(int page, int size);
    
}
