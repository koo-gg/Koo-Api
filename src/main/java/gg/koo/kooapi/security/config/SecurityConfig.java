package gg.koo.kooapi.security.config;

import gg.koo.kooapi.security.CustomAuthenticationManager;
import gg.koo.kooapi.security.CustomSecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final CustomAuthenticationManager authenticationManager;
    private final CustomSecurityContextRepository securityContextRepository;

    public SecurityConfig(CustomAuthenticationManager authenticationManager, CustomSecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    protected SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/about", "/login").permitAll()
                .pathMatchers(HttpMethod.GET, "/guilds/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER")
                .anyExchange().authenticated()
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}