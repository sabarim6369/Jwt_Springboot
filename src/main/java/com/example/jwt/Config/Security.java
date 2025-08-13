package com.example.jwt.Config;

import com.example.jwt.Jwtutils.Filterperrequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Security {
    @Autowired
    private  Filterperrequest filterperrequest;


    @Bean
    public SecurityFilterChain securityfilter(HttpSecurity http) throws Exception{
        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(authz->authz
                        .requestMatchers("/auth/login").permitAll()  // allow login
//                        .requestMatchers("/auth/**").authenticated()
                                .anyRequest().permitAll()


                )  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filterperrequest, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
    @Bean
    public UserDetailsService userdetail() {
        UserDetails user = User.withUsername("sabari")
                .password("{noop}123") // {noop} means "no encoding"
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
