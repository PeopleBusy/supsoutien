/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier.impl;

import java.util.List;
import org.rachidcorp.supsoutien.entities.User;
import org.rachidcorp.supsoutien.metier.UserMetier;
import org.rachidcorp.supsoutien.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rachid ABDEL
 */
@Service
public class UserMetierImpl implements UserMetier {
    
    @Autowired
    private UserRepository ur;
    
    @Override
    public User saveUser(User u) {
        return ur.save(u);
    }

    @Override
    public User updateUser(User u) {
        return ur.saveAndFlush(u);
    }

    @Override
    public void deleteUser(User u) {
        ur.delete(u);
    }
    
    @Override
    public void deleteUserById(Long id) {
        User u = ur.findOne(id);
        ur.delete(u);
    }

    @Override
    public Page<User> listUsers(int page, int size) {
        return ur.findAll(new PageRequest(page, size));
    }
    
    @Override
    public User findUserByIdBooster(Long idBooster) {
        return ur.findByIdBooster(idBooster);
    }

    @Override
    public User findUserById(Long id) {
       return ur.findOne(id);
    }

    @Override
    public Page<User> rechercherUserByIdBooster(Long idBooster, Pageable pageable) {
        return ur.rechercherUserByIdBooster(idBooster, pageable);
    }

    @Override
    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long idBooster = Long.valueOf(auth.getName());

        return ur.findByIdBooster(idBooster);
    }

    @Override
    public List<User> getAllStaff() {
        return ur.getAllStaff();
    }
        

    
}
