/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rachidcorp;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author rachid
 */
@Configuration
public class DataSourceConfig {
    private String dbURL;
    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    @Bean
    public DataSource dataSource() {
        
        dbHost = System.getenv("DB_HOST");
        dbPort = System.getenv("DB_PORT");
        dbName = System.getenv("DB_NAME");
        dbUser = System.getenv("DB_USER");
        dbPassword = System.getenv("DB_PASSWORD");
        
        dbURL  = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        
        DataSource dataSource = new DataSource(); // org.apache.tomcat.jdbc.pool.DataSource;
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(dbURL);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setTestWhileIdle(true);     
        //dataSource.setTimeBetweenEvictionRunsMillis(");
        dataSource.setValidationQuery("SELECT 1");
        
//        dataSource.setUrl("jdbc:mysql://localhost:3306/Supsoutien");
//        dataSource.setUsername("scs");
//        dataSource.setPassword("Supinf0");
        
        return dataSource;   

    }

}
