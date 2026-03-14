package com.christian.incident.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableMethodSecurity
@EnableWebMvc

public class SpringSecurityConfig {
    //SecurityFilterChain intercepta todas as requisições antes de chegar ao controller, esses filtros podem bloquear acessos ou verificar permissões, proteger ataques, etc...
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity htpp) throws Exception{
        return htpp
                .csrf(csrf -> csrf.disable()) // não necessario na aplicação porque não é utilizado cookies.
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                                .anyRequest().authenticated()
                ).sessionManagement(
                        session -> session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS)
                        ).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder () { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return
                authenticationConfiguration.getAuthenticationManager();
    }
}
