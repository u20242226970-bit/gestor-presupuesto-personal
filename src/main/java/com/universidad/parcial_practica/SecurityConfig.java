package com.universidad.parcial_practica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuracion de Spring Security con JWT.
 * Las paginas HTML son publicas; la proteccion real esta en los endpoints /api/**.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                // Endpoints publicos de autenticacion
                .requestMatchers("/api/auth/**").permitAll()

                // Recursos estaticos: TODAS las paginas HTML, CSS y JS son publicas.
                // El navegador no envia el token JWT al navegar entre paginas,
                // por eso los archivos deben ser accesibles. La proteccion real
                // ocurre en los endpoints /api/** que SI requieren token.
                .requestMatchers(
                    "/",
                    "/index.html",
                    "/register.html",
                    "/dashboard.html",
                    "/gastos.html",
                    "/metas.html",
                    "/perfil.html",
                    "/leaderboard.html",
                    "/app.js",
                    "/style.css",
                    "/*.html",
                    "/*.css",
                    "/*.js",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/images/**",
                    "/favicon.ico"
                ).permitAll()

                // Swagger
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()

                // Endpoints de la API protegidos por rol (requieren token JWT valido)
                .requestMatchers("/api/categorias/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/gastos/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/metas/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/abonos/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/usuarios/**").hasAnyRole("ADMIN", "USER")

                // Cualquier otra peticion requiere autenticacion
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}