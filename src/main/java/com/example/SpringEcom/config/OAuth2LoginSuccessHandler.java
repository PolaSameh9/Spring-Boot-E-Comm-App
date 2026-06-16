package com.example.SpringEcom.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.SpringEcom.model.User;
import com.example.SpringEcom.service.JwtService;
import com.example.SpringEcom.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler{

    private final JwtService jwtService;
    private final UserService userService;

    public OAuth2LoginSuccessHandler(JwtService jwtService, UserService userService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException{
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userService.findByEmail(email).orElse(new User());

        String token = jwtService.generateToken(user.getEmail());

        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        
    }

}
