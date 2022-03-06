package gg.koo.kooapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomSecurityContextRepository implements ServerSecurityContextRepository {

    private final CustomAuthenticationManager authenticationManager;

    @Autowired
    public CustomSecurityContextRepository(CustomAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String accessToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (accessToken != null) {
            Authentication auth = new UsernamePasswordAuthenticationToken(accessToken, accessToken);
            SecurityContextHolder.getContext().setAuthentication(auth);

            return authenticationManager.authenticate(auth).map(authentication -> new SecurityContextImpl(authentication));
        }

        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "There is no authorization authority"));
    }
}
