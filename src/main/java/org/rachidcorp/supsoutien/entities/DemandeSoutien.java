/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rachid
 */
@Entity
@Table(name = "demande_soutien")
public class DemandeSoutien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "etat_demande")
    private boolean etatDemande;
    
    @JoinColumn(name = "etudiant_id", referencedColumnName = "id")
    @ManyToOne
    private User etudiantId;
    
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    @ManyToOne
    private User coachId;
    
    @JoinColumn(name = "matiere_id", referencedColumnName = "id")
    @ManyToOne
    private Matiere matiereId;

    public DemandeSoutien() {
    }

    public DemandeSoutien(Long id) {
        this.id = id;
    }

    public DemandeSoutien(Long id, boolean etatDemande) {
        this.id = id;
        this.etatDemande = etatDemande;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(boolean etatDemande) {
        this.etatDemande = etatDemande;
    }

    public User getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(User etudiantId) {
        this.etudiantId = etudiantId;
    }

    public User getCoachId() {
        return coachId;
    }

    public void setCoachId(User coachId) {
        this.coachId = coachId;
    }

    public Matiere getMatiereId() {
        return matiereId;
    }

    public void setMatiereId(Matiere matiereId) {
        this.matiereId = matiereId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DemandeSoutien)) {
            return false;
        }
        DemandeSoutien other = (DemandeSoutien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rachidcorp.supsoutien.entities.DemandeSoutien[ id=" + id + " ]";
    }
    
}
