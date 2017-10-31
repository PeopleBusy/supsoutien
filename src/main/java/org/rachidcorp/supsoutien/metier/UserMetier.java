/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier;

import java.util.List;
import org.rachidcorp.supsoutien.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Fa-Dom AHOLOU
 */
public interface UserMetier {
    public User saveUser(User u);
    public User updateUser(User u);
    public void deleteUser(User u);
    public void deleteUserById(Long Id);
    public Page<User> listUsers(int page, int size);
    public Page<User> rechercherUserByIdBooster(Long idBooster, Pageable pageable);
    public User findUserById(Long id);
    public User findUserByIdBooster(Long idBooster);
    public User getLoggedUser();
    public List<User> getAllStaff();
}
