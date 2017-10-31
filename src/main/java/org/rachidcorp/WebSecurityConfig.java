package org.rachidcorp;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, DataSource ds) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(ds)
            .passwordEncoder(passwordEncoder())
            .usersByUsernameQuery("select id_booster as principal, mot_passe as credentials, etat from user where etat = true and id_booster = ?")
            .authoritiesByUsernameQuery("select id_booster as principal, role_lib as role from user where id_booster = ?");
                
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/images/**").permitAll()
                .antMatchers("/dashboard").authenticated()
                .antMatchers("/coach/*").access("(hasRole('ROLE_COACH'))")
                .antMatchers("/staff/*").access("(hasRole('ROLE_STAFF'))")
                .antMatchers("/etudiant/*").access("(hasRole('ROLE_ETUDIANT') or hasRole('ROLE_COACH'))")
                .antMatchers("/users/*").access("(hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')) and isFullyAuthenticated()")
                .anyRequest().permitAll().and()
		.formLogin().loginPage("/login").permitAll().usernameParameter("id_booster").passwordParameter("mot_passe")
                .defaultSuccessUrl("/dashboard").and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and().exceptionHandling().accessDeniedPage("/403").and()
		.csrf().disable();
    }

}
