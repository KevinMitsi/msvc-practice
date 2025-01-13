package com.example.user_microservice.infraestructure.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    public static final String SCOPE_READ = "SCOPE_read";
    public static final String SCOPE_WRITE = "SCOPE_write";


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/user/authorized").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyAuthority(SCOPE_READ, SCOPE_WRITE)
                        .requestMatchers(HttpMethod.POST, "/api/v1/user/**").hasAuthority(SCOPE_WRITE)
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/user/{id}").hasAuthority(SCOPE_WRITE)
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/msvc-users-client")
                )
                .oauth2Client(Customizer.withDefaults())
                .oauth2ResourceServer(server ->
                        server.jwt(Customizer.withDefaults()));
        return http.build();
    }

}
