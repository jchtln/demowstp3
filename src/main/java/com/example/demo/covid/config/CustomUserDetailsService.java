package com.example.demo.covid.config;

import com.example.demo.covid.Controleur;
import com.example.demo.covid.modele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomUserDetailsService implements UserDetailsService {
    private static final String[] ROLES_POLICE = {"POLICE"};
    private static final String[] ROLES_USER = {"USER"};

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = Controleur.getUtilisateur(username);
        if(utilisateur==null){
            System.out.print(utilisateur + "not found");
        }
        String[] roles = utilisateur.getUsername().equals("police") ? ROLES_POLICE : ROLES_USER;
        UserDetails userDetails = User.builder()
                .username(utilisateur.getUsername())
                .password(passwordEncoder.encode(utilisateur.getPassword()))
                .roles(roles)
                .build();

        return userDetails;
    }
}
