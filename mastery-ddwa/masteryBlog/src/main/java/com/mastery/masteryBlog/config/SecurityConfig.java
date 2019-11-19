/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastery.masteryBlog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author corey
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    

    @Autowired
    UserDetailsService userDetails;

//    @Autowired
//    public void configureGlobalInMemory(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("{noop}password").roles("USER", "EMPLOYEE")
//                .and()
//                .withUser("admin").password("{noop}password").roles("ADMIN", "USER")
//                .and();
//    }
    @Autowired
    public void configureGlobalInDB(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin-post", "/employee-post", "/user-management", "/editPost", "/pending-posts", "/submit-pending").hasRole("ADMIN")
                .antMatchers("/employee-post").hasAnyRole("EMPLOYEE", "ADMIN")
                .antMatchers("/", "/home", "/categories", "/show-category", "/static").permitAll()
                .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?login_error=1")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
    

}