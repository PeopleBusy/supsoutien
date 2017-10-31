/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Rachid ABDEL
 */
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/users/staff").setViewName("users/staff");
        registry.addViewController("/users/list").setViewName("users/list");
        registry.addViewController("/users/matiere").setViewName("users/matiere");
        registry.addViewController("/coach/new_soutien").setViewName("coach/new_soutien");
        registry.addViewController("/coach/list_soutien").setViewName("coach/list_soutien");
        registry.addViewController("/staff/list_soutien").setViewName("staff/list_soutien");
        registry.addViewController("/staff/list_inscription").setViewName("staff/list_inscription");         
        registry.addViewController("/staff/list_demande").setViewName("staff/list_demande");
        registry.addViewController("/etudiant/new_inscription").setViewName("etudiant/new_inscription");
        registry.addViewController("/etudiant/list_inscription").setViewName("etudiant/list_inscription");
        registry.addViewController("/etudiant/list_demande").setViewName("etudiant/list_etudiant_demande");
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/404").setViewName("404");
        registry.addViewController("/500").setViewName("500");
    }
    
    
}
