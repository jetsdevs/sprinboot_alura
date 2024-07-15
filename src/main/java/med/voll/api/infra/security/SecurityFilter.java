package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component  //Componente objeto do spring pra carregar tipo generico.
public class SecurityFilter extends OncePerRequestFilter {
//OncePerRequestFilter garante que sera executada uma vez por requisão

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//recuperando o token por Bearer
        var tokenJWT = recuperarToken(request);
        System.out.println(tokenJWT);


        //chamando o proximo filtro(garantir que essa linha esta sendo chamado)
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            throw new RuntimeException("Token invalido!");
        }
        return authorizationHeader.replace("Bearer", "");
    }
}
