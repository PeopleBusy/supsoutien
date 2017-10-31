/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.repository;

import java.util.List;
import org.rachidcorp.supsoutien.entities.InscriptionEtudiantCreneau;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author rachid
 */
public interface InscriptionEtudiantCreneauRepository extends JpaRepository<InscriptionEtudiantCreneau, Long> {
    @Query("select i from InscriptionEtudiantCreneau i where i.etudiantId.id = :x order by i.id desc")
    public Page<InscriptionEtudiantCreneau> findInsriptionCreneauxMatiereByEtudiant(@Param("x") Long id, Pageable pageable);
    
    @Query("select i from InscriptionEtudiantCreneau i where i.creneauId.id = :x")
    public List<InscriptionEtudiantCreneau> findInsriptionsEtudiantsByCreneau(@Param("x") Long id);
    
    @Query("select i from InscriptionEtudiantCreneau i order by i.id desc")
    public Page<InscriptionEtudiantCreneau> findAllInsriptionCreneauxMatiere(Pageable pageable);
}
