package com.example.SpringEcom.config;

import com.example.SpringEcom.config.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;

import com.example.SpringEcom.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2LoginSuccessHandler OAuth2LoginSuccessHandler;

    @Autowired
    private final MyUserDetailsService userDetailsService;

    @Autowired
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter, MyUserDetailsService userDetailsService, OAuth2LoginSuccessHandler OAuth2LoginSuccessHandler) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
        this.OAuth2LoginSuccessHandler = OAuth2LoginSuccessHandler;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http.csrf(customizer -> customizer.disable())
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/api/register", "/api/login")
                    .permitAll()
                    .requestMatchers("/api/user/**").hasAuthority("ROLE_USER")
                    .requestMatchers("/api/admin/**").hasAuthority("ROLE_ADMIN")
                    .anyRequest()
                    .authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2Login(oauth -> oauth.successHandler(OAuth2LoginSuccessHandler))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
        return config.getAuthenticationManager();
    }

}
