package wiki.gak.graduation.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import wiki.gak.graduation.util.JwtUtil;

/**
 * Jwt filter.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 上午10:39
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private static final String TOKEN_TYPE = "Bearer";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.hasText(authorization) && authorization.startsWith(TOKEN_TYPE)) {
      String bearerToken = authorization.substring(6);
      JSONObject parse = jwtUtil.parse(bearerToken);
      JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(
          parse.getAsString("sub"), parse.getAsString("sessionKey"));
      authenticationToken.setDetails(parse);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
    filterChain.doFilter(request, response);
  }
}
