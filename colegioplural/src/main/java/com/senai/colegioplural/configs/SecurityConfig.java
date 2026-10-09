package com.senai.colegioplural.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.senai.colegioplural.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {
    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

@Bean public UserDetailsService userDetailsService() {
return username -> usuarioRepository.findByEmail(username)
.map(u ->
User.builder().username(u.getEmail()).password(u.getSenha()).roles(u.getPerfil()).
build())
.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
}

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService uds, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(uds);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/aluno", "/responsavel",
                                "/usuario/buscar", "/usuario/listar")
                        .authenticated()
                        .requestMatchers(HttpMethod.POST, "/aluno", "/responsavel",
                                "/usuario/cadastrar")
                        .hasAnyRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.PUT, "/aluno", "/responsavel",
                                "/usuario/atualizar")
                        .hasAnyRole("FUNCIONARIO")
                        .requestMatchers(HttpMethod.DELETE, "/aluno", "/responsavel",
                                "/usuario/deletar")
                        .hasAnyRole("FUNCIONARIO")
                        .requestMatchers("/atendimento").hasAnyRole("FUNCIONARIO")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
