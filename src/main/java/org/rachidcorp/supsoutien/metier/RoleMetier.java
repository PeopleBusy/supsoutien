/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.metier;

import java.util.List;
import org.rachidcorp.supsoutien.entities.Role;

/**
 *
 * @author Fa-Dom AHOLOU
 */
public interface RoleMetier {
   public List<Role> listRoles();
   public Role findRoleByRoleId(Long id);
}
