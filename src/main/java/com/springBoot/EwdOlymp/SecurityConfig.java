package com.springBoot.EwdOlymp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder());
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	http.csrf(csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository())).
    	authorizeHttpRequests(requests -> 
    		requests.requestMatchers("/login**").permitAll()
    				.requestMatchers("/css/**").permitAll()	
    				.requestMatchers("/sporten").hasAnyRole("USER","ADMIN")
    				.requestMatchers("/wedstrijd").hasAnyRole("USER","ADMIN")
    				.requestMatchers("/mijnTicketten").hasRole("USER")
    				.requestMatchers("/nieuweWedstrijd").hasRole("ADMIN")
    				.requestMatchers("/nieuweTicket").hasRole("USER")
    				.requestMatchers("/rest/**").permitAll()
    				.requestMatchers("/access-denied").permitAll()
                    


    				.anyRequest().hasAnyRole("USER", "ADMIN"))
                    .formLogin(form -> 
                            form.defaultSuccessUrl("/sport", true)
                                 .loginPage("/login"))
                                 .exceptionHandling(e -> e.accessDeniedPage("/access-denied"));
 ;

    	
    	return http.build();
    	        
    }
}