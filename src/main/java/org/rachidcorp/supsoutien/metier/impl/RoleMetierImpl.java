/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier.impl;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Role;
import org.rachidcorp.supsoutien.metier.RoleMetier;
import org.rachidcorp.supsoutien.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fa-Dom AHOLOU
 */
@Service
public class RoleMetierImpl implements RoleMetier {
    
    @Autowired
    private RoleRepository rr;

    @Override
    public List<Role> listRoles() {
        return rr.findAll();
    }

    @Override
    public Role findRoleByRoleId(Long id) {
        return rr.findOne(id);
    }
}
