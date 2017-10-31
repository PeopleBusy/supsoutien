/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier.impl;

import java.util.List;
import org.rachidcorp.supsoutien.entities.InscriptionEtudiantCreneau;
import org.rachidcorp.supsoutien.metier.InscriptionEtudiantCreneauMetier;
import org.rachidcorp.supsoutien.repository.InscriptionEtudiantCreneauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author rachid
 */
@Service
public class InscriptionEtudiantCreneauMetierImpl implements InscriptionEtudiantCreneauMetier {
    
    @Autowired
    private InscriptionEtudiantCreneauRepository iecr;

    @Override
    public Page<InscriptionEtudiantCreneau> findInsriptionCreneauxMatiereByEtudiant(Long id, int page, int size) {
        return iecr.findInsriptionCreneauxMatiereByEtudiant(id, new PageRequest(page, size));
    }

    @Override
    public InscriptionEtudiantCreneau saveInscriptionEtudiantCreneau(InscriptionEtudiantCreneau i) {
        return iecr.save(i);
    }

    @Override
    public InscriptionEtudiantCreneau updateInscriptionEtudiantCreneau(InscriptionEtudiantCreneau i) {
        return iecr.saveAndFlush(i);
    }

    @Override
    public List<InscriptionEtudiantCreneau> findInsriptionsEtudiantsByCreneau(Long id) {
        return iecr.findInsriptionsEtudiantsByCreneau(id);
    }

    @Override
    public Page<InscriptionEtudiantCreneau> findAllInsriptionCreneauxMatiere(int page, int size) {
        return iecr.findAllInsriptionCreneauxMatiere(new PageRequest(page, size));
    }

    
}
