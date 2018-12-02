/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.restservice;


import java.util.List;
import org.rachidcorp.supsoutien.entities.Role;
import org.rachidcorp.supsoutien.metier.RoleMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fa-Dom AHOLOU
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/roles")
public class RoleRestService {
    
    @Autowired
    private RoleMetier rm;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> listRoles() {
        System.out.println("++++++++++");
        return rm.listRoles();
    }
    
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Role findRoleById(@PathVariable("id") Long id) {
        System.out.println("***********");
        return rm.findRoleByRoleId(id);
    }
    
}
