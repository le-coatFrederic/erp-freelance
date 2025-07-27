package com.fredlecoat.freelanceerp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // Permettre tous les accès depuis localhost (127.0.0.1 et ::1 pour IPv6)
                        .requestMatchers(request ->
                                isLocalhost(request.getRemoteAddr())
                        ).permitAll()
                        // Autres règles d'autorisation pour les autres IPs
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // Optionnel : désactiver CSRF si nécessaire
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    /**
     * Vérifie si l'adresse IP correspond à localhost
     */
    private boolean isLocalhost(String remoteAddr) {
        return "127.0.0.1".equals(remoteAddr) ||
                "0:0:0:0:0:0:0:1".equals(remoteAddr) ||
                "::1".equals(remoteAddr) ||
                "localhost".equals(remoteAddr);
    }
}
