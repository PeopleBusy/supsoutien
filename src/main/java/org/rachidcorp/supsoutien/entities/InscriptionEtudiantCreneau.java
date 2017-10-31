/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author rachid
 */
@Entity
@Table(name = "inscription_etudiant_creneau")
public class InscriptionEtudiantCreneau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_heure_inscription")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureInscription;
    
    @Column(name = "confirm_mail_send_by_etudiant")
    private Boolean confirmMailSendByEtudiant;
    
    @Lob
    @Size(max = 65535)
    @Column(name = "contenu_mail_etudiant")
    private String contenuMailEtudiant;
    
    @Column(name = "confirm_par_coach")
    private int confirmParCoach;
    @Lob
    @Size(max = 65535)
    @Column(name = "contenu_mail_coach")
    private String contenuMailCoach;
    @JoinColumn(name = "etudiant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User etudiantId;
    @JoinColumn(name = "creneau_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CreneauMatiereCoach creneauId;

    public InscriptionEtudiantCreneau() {
    }

    public InscriptionEtudiantCreneau(Long id) {
        this.id = id;
    }

    public InscriptionEtudiantCreneau(Long id, Date dateHeureInscription) {
        this.id = id;
        this.dateHeureInscription = dateHeureInscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateHeureInscription() {
        return dateHeureInscription;
    }

    public void setDateHeureInscription(Date dateHeureInscription) {
        this.dateHeureInscription = dateHeureInscription;
    }

    public Boolean getConfirmMailSendByEtudiant() {
        return confirmMailSendByEtudiant;
    }

    public void setConfirmMailSendByEtudiant(Boolean confirmMailSendByEtudiant) {
        this.confirmMailSendByEtudiant = confirmMailSendByEtudiant;
    }

    public String getContenuMailEtudiant() {
        return contenuMailEtudiant;
    }

    public void setContenuMailEtudiant(String contenuMailEtudiant) {
        this.contenuMailEtudiant = contenuMailEtudiant;
    }

    public int getConfirmParCoach() {
        return confirmParCoach;
    }

    public void setConfirmParCoach(int confirmParCoach) {
        this.confirmParCoach = confirmParCoach;
    }

    public String getContenuMailCoach() {
        return contenuMailCoach;
    }

    public void setContenuMailCoach(String contenuMailCoach) {
        this.contenuMailCoach = contenuMailCoach;
    }

    public User getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(User etudiantId) {
        this.etudiantId = etudiantId;
    }

    public CreneauMatiereCoach getCreneauId() {
        return creneauId;
    }

    public void setCreneauId(CreneauMatiereCoach creneauId) {
        this.creneauId = creneauId;
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
        if (!(object instanceof InscriptionEtudiantCreneau)) {
            return false;
        }
        InscriptionEtudiantCreneau other = (InscriptionEtudiantCreneau) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rachidcorp.supsoutien.InscriptionEtudiantCreneau[ id=" + id + " ]";
    }
    
}
