/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp.supsoutien.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Rachid ABDEL
 */
public class QuickPasswordEncodingGenerator {
    /**
     * @param args
     */
    public static void main(String[] args) {
            String passwordhash = "admin";
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            System.out.println("passadminhash: " + passwordEncoder.encode(passwordhash));
            
            String password1hash = "coach";
            BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
            System.out.println("passuserhash: " + passwordEncoder1.encode(password1hash));
    }
}
