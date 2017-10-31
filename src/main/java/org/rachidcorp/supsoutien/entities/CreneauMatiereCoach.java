/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author rachid
 */
@Entity
@Table(name = "creneau_matiere_coach")
@NamedQueries({
    @NamedQuery(name = "CreneauMatiereCoach.findAll", query = "SELECT c FROM CreneauMatiereCoach c")})
public class CreneauMatiereCoach implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "valider_par_staff")
    private int validerParStaff;
    @Column(name = "date_validation_staff")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValidationStaff;
    @Column(name = "date_heure_debut_soutien")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureDebutSoutien;
    @Column(name = "date_heure_fin_soutien")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureFinSoutien;
    @Column(name = "valider_par_coach_fin_session")
    private int validerParCoachFinSession;
    @Column(name = "date_heure_validation_coach")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateHeureValidationCoach;
    @Lob
    @Size(max = 65535)
    @Column(name = "commentaire")
    private String commentaire;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creneauId")
//    private List<InscriptionEtudiantCreneau> inscriptionEtudiantCreneauList;
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    @ManyToOne
    private User coachId;
    
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    @ManyToOne
    private User staffId;
    
    @JoinColumn(name = "matiere_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Matiere matiereId;
    
    @Column(name = "nb_inscrits")
    private int nbInscrits;
    
    @Column(name = "nb_presents")
    private int nbPresents;

    public CreneauMatiereCoach() {
    }

    public CreneauMatiereCoach(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValiderParStaff() {
        return validerParStaff;
    }

    public void setValiderParStaff(int validerParStaff) {
        this.validerParStaff = validerParStaff;
    }

    public User getStaffId() {
        return staffId;
    }

    public void setStaffId(User staffId) {
        this.staffId = staffId;
    }


    public Date getDateValidationStaff() {
        return dateValidationStaff;
    }

    public void setDateValidationStaff(Date dateValidationStaff) {
        this.dateValidationStaff = dateValidationStaff;
    }

    public Date getDateHeureDebutSoutien() {
        return dateHeureDebutSoutien;
    }

    public void setDateHeureDebutSoutien(Date dateHeureDebutSoutien) {
        this.dateHeureDebutSoutien = dateHeureDebutSoutien;
    }

    public Date getDateHeureFinSoutien() {
        return dateHeureFinSoutien;
    }

    public void setDateHeureFinSoutien(Date dateHeureFinSoutien) {
        this.dateHeureFinSoutien = dateHeureFinSoutien;
    }

    public int getValiderParCoachFinSession() {
        return validerParCoachFinSession;
    }

    public void setValiderParCoachFinSession(int validerParCoachFinSession) {
        this.validerParCoachFinSession = validerParCoachFinSession;
    }

    public Date getDateHeureValidationCoach() {
        return dateHeureValidationCoach;
    }

    public void setDateHeureValidationCoach(Date dateHeureValidationCoach) {
        this.dateHeureValidationCoach = dateHeureValidationCoach;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

//    public List<InscriptionEtudiantCreneau> getInscriptionEtudiantCreneauList() {
//        return inscriptionEtudiantCreneauList;
//    }
//
//    public void setInscriptionEtudiantCreneauList(List<InscriptionEtudiantCreneau> inscriptionEtudiantCreneauList) {
//        this.inscriptionEtudiantCreneauList = inscriptionEtudiantCreneauList;
//    }

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

    public int getNbInscrits() {
        return nbInscrits;
    }

    public void setNbInscrits(int nbInscrits) {
        this.nbInscrits = nbInscrits;
    }

    public int getNbPresents() {
        return nbPresents;
    }

    public void setNbPresents(int nbPresents) {
        this.nbPresents = nbPresents;
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
        if (!(object instanceof CreneauMatiereCoach)) {
            return false;
        }
        CreneauMatiereCoach other = (CreneauMatiereCoach) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rachidcorp.supsoutien.CreneauMatiereCoach[ id=" + id + " ]";
    }
    
}
