/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.entities;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "matiere")
public class Matiere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "code_matiere")
    private String codeMatiere;
    
    @Column(name = "lib_matiere")
    private String libMatiere;
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matiereId")
//    private List<CreneauMatiereCoach> creneauMatiereCoachList;
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Promotion promotionId;

    public Matiere(String codeMatiere, String libMatiere, Promotion promotionId) {
        this.codeMatiere = codeMatiere;
        this.libMatiere = libMatiere;
        this.promotionId = promotionId;
    }

    public Matiere() {
    }

    public Matiere(Long id) {
        this.id = id;
    }

    public Matiere(Long id, String codeMatiere) {
        this.id = id;
        this.codeMatiere = codeMatiere;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeMatiere() {
        return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
        this.codeMatiere = codeMatiere;
    }

    public String getLibMatiere() {
        return libMatiere;
    }

    public void setLibMatiere(String libMatiere) {
        this.libMatiere = libMatiere;
    }

//    public List<CreneauMatiereCoach> getCreneauMatiereCoachList() {
//        return creneauMatiereCoachList;
//    }
//
//    public void setCreneauMatiereCoachList(List<CreneauMatiereCoach> creneauMatiereCoachList) {
//        this.creneauMatiereCoachList = creneauMatiereCoachList;
//    }

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
        if (!(object instanceof Matiere)) {
            return false;
        }
        Matiere other = (Matiere) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rachidcorp.supsoutien.Matiere[ id=" + id + " ]";
    }
    
}
