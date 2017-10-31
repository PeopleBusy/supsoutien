/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier;

import java.util.List;
import org.rachidcorp.supsoutien.entities.InscriptionEtudiantCreneau;
import org.springframework.data.domain.Page;

/**
 *
 * @author rachid
 */
public interface InscriptionEtudiantCreneauMetier {
    
    public InscriptionEtudiantCreneau saveInscriptionEtudiantCreneau(InscriptionEtudiantCreneau i);
    public InscriptionEtudiantCreneau updateInscriptionEtudiantCreneau(InscriptionEtudiantCreneau i);
    public Page<InscriptionEtudiantCreneau> findInsriptionCreneauxMatiereByEtudiant(Long id, int page, int size);
    public List<InscriptionEtudiantCreneau> findInsriptionsEtudiantsByCreneau(Long id);
    public Page<InscriptionEtudiantCreneau> findAllInsriptionCreneauxMatiere(int page, int size);
}
