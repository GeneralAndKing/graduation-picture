package wiki.gak.graduation.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Jwt Provider，提供 JWT 授权决策
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 下午12:17
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
    String sessionKey = jwtAuthenticationToken.getCredentials().toString();
    String openId = jwtAuthenticationToken.getPrincipal().toString();
    ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
    if (openId.equalsIgnoreCase(operations.get(sessionKey))) {
      jwtAuthenticationToken.setAuthenticated(true);
      return jwtAuthenticationToken;
    } else {
      throw new BadCredentialsException("用户令牌验证失败");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
