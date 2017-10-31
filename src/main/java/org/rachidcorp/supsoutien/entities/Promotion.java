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
import javax.persistence.Table;

/**
 *
 * @author rachid
 */
@Entity
@Table(name = "promotion")
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "code_promotion")
    private String codePromotion;
    
    @Column(name = "lib_promotion")
    private String libPromotion;
//    @OneToMany(mappedBy = "promotionId")
//    private List<User> userList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotionId")
//    private List<Matiere> matiereList;

    public Promotion() {
    }

    public Promotion(Integer id) {
        this.id = id;
    }

    public Promotion(Integer id, String codePromotion) {
        this.id = id;
        this.codePromotion = codePromotion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodePromotion() {
        return codePromotion;
    }

    public void setCodePromotion(String codePromotion) {
        this.codePromotion = codePromotion;
    }

    public String getLibPromotion() {
        return libPromotion;
    }

    public void setLibPromotion(String libPromotion) {
        this.libPromotion = libPromotion;
    }

//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }
//
//    public List<Matiere> getMatiereList() {
//        return matiereList;
//    }
//
//    public void setMatiereList(List<Matiere> matiereList) {
//        this.matiereList = matiereList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promotion)) {
            return false;
        }
        Promotion other = (Promotion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.rachidcorp.supsoutien.Promotion[ id=" + id + " ]";
    }
    
}
