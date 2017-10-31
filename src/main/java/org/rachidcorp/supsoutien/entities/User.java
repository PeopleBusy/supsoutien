/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rachid
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
 
    @Column(name = "id_booster")
    private Long idBooster;
    
    @Column(name = "mot_passe")
    private String motPasse;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;
    
    @Column(name = "email")
    private String email;

    @Column(name = "etat")
    private boolean etat;

    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etudiantId")
    private List<InscriptionEtudiantCreneau> inscriptionEtudiantCreneauList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coachId")
    private List<CreneauMatiereCoach> creneauMatiereCoachList;
    
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staffId")
    private List<CreneauMatiereCoach> creneauMatiereCoachList1;
       
    @JoinColumn(name = "role_lib", referencedColumnName = "lib_role")
    @ManyToOne(optional = false)
    private Role roleLib;
    
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    @ManyToOne
    private Promotion promotionId;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long idBooster, String motPasse, String nom, String prenom, boolean etat) {
        this.idBooster = idBooster;
        this.motPasse = motPasse;
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBooster() {
        return idBooster;
    }

    public void setIdBooster(Long idBooster) {
        this.idBooster = idBooster;
    }
    
    @JsonIgnore
    public String getMotPasse() {
        return motPasse;
    }
    
    @JsonProperty
    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean getEtat() {
        return etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<InscriptionEtudiantCreneau> getInscriptionEtudiantCreneauList() {
        return inscriptionEtudiantCreneauList;
    }

    public void setInscriptionEtudiantCreneauList(List<InscriptionEtudiantCreneau> inscriptionEtudiantCreneauList) {
        this.inscriptionEtudiantCreneauList = inscriptionEtudiantCreneauList;
    }

    public List<CreneauMatiereCoach> getCreneauMatiereCoachList() {
        return creneauMatiereCoachList;
    }

    public void setCreneauMatiereCoachList(List<CreneauMatiereCoach> creneauMatiereCoachList) {
        this.creneauMatiereCoachList = creneauMatiereCoachList;
    }

    public List<CreneauMatiereCoach> getCreneauMatiereCoachList1() {
        return creneauMatiereCoachList1;
    }

    public void setCreneauMatiereCoachList1(List<CreneauMatiereCoach> creneauMatiereCoachList1) {
        this.creneauMatiereCoachList1 = creneauMatiereCoachList1;
    }

    public void setRoleLib(Role roleLib) {
        this.roleLib = roleLib;
    }

    public boolean isEtat() {
        return etat;
    }

    public Role getRoleLib() {
        return roleLib;
    }

    public Promotion getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Promotion promotionId) {
        this.promotionId = promotionId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rachidcorp.supsoutien.User[ id=" + id + " ]";
    }
    
}
