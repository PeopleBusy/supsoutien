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
    
//    @RequestMapping(method = RequestMethod.POST)
//    public User saveUser(@RequestBody User u) { //@RequestBody pour recupérer les données dans la requête et
//        return um.saveUser(u);     //les sérialiser puis les retourner au format JSON via @RestController.
//    }                               //Avec @RestController les reponses sont aussi retournées au format JSON
//
//    
//    @RequestMapping(value = "/activerOuDesactiverUser",method = RequestMethod.PUT)
//    public User activerOuDesactiverUser(@RequestBody Long id) { 
//        User u = um.findUserById(id);
//        if(u.getEtat()){
//            u.setEtat(false);
//        }else{
//            u.setEtat(true);
//        }
//        return um.saveUser(u);  
//    }
//    
//    @RequestMapping(method = RequestMethod.PUT)
//    public User updateUser(@RequestBody User u, @PathVariable("id") Long id) {
//        return um.updateUser(u);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE)
//    public void deleteUser(@RequestBody User u) {
//        um.deleteUser(u);
//    }
//    
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public void deleteUserById(@PathVariable("id") Long id){
//        User u = um.findUserById(id);
//        um.deleteUser(u);
//    }
//
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public Page<User> listUsers(@RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "10") int size) {
//        return um.listUsers(page, size);
//    }
//    
//    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
//    public User findUserById(@PathVariable("id") Long id) {
//        System.out.println("+++");
//        return um.findUserById(id);
//    }
//
//    @RequestMapping(value = "/idbooster/{idBooster}",method = RequestMethod.GET)
//    public User findUserByIdBooster(@PathVariable("id") Long idBooster) {
//        return um.findUserByIdBooster(idBooster);
//    }
//    
//    @RequestMapping(value = "/getLoggedUser",method = RequestMethod.GET)
//    public User getLoggedUser(HttpServletRequest hsr) {
//        HttpSession hs = hsr.getSession();
//        SecurityContext sc = (SecurityContext) hs.getAttribute("SPRING_SECURITY_CONTEXT");
//        Long idBooster = Long.getLong(sc.getAuthentication().getName());
//        
//        return um.findUserByIdBooster(idBooster);
//    }
    
    
}
