package gg.koo.kooapi.security.config;

import gg.koo.kooapi.security.CustomAuthenticationManager;
import gg.koo.kooapi.security.CustomSecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("1111"))
                .roles("USER")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("1111"))
                .roles("ADMIN", "USER")
                .build();

        return new MapReactiveUserDetailsService(user, admin);
    }

    @Bean
    protected SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers("/about", "/login").permitAll()
                .pathMatchers(HttpMethod.GET, "/guilds/**/admin").hasRole("ADMIN")
                .pathMatchers(HttpMethod.GET, "/guilds/**/user").hasRole("USER")
                .pathMatchers(HttpMethod.GET, "/guilds/**").authenticated()
                .anyExchange().authenticated()
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}