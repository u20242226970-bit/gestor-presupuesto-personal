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
 * Configuración de Spring Security con JWT.
 * - Sin sesiones (stateless): cada petición debe traer su token.
 * - Endpoints públicos: login, registro, recursos estáticos, Swagger.
 * - Resto de endpoints: requieren un token JWT válido.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Bean para encriptar contraseñas con BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean del AuthenticationManager, requerido por el AuthController
     * para validar las credenciales durante el login.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Cadena principal de filtros de seguridad.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. Desactivamos CSRF (no se necesita en APIs stateless con JWT)
            .csrf(AbstractHttpConfigurer::disable)

            // 2. Definimos qué rutas son públicas y cuáles requieren autenticación
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas
                .requestMatchers(
                    "/api/auth/**",
                    "/",
                    "/index.html",
                    "/register.html",
                    "/app.js",
                    "/style.css",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/static/**",
                    "/favicon.ico",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Rutas protegidas por rol
                .requestMatchers("/api/categorias/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/gastos/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/metas/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/abonos/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/usuarios/**").hasAnyRole("ADMIN", "USER")

                // Cualquier otra petición requiere autenticación
                .anyRequest().authenticated()
            )

            // 3. Política de sesiones: STATELESS
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 4. Insertamos nuestro filtro JWT antes del de username/password
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}