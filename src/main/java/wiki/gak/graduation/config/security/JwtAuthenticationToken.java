package wiki.gak.graduation.config.security;

import java.util.Collections;
import java.util.Objects;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Jwt Token.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 上午10:52
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

  private final Object openId;
  private final Object sessionKey;

  public JwtAuthenticationToken(String openId, String sessionKey) {
    super(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    this.openId = openId;
    this.sessionKey = sessionKey;
    setAuthenticated(false);
  }

  @Override
  public Object getCredentials() {
    return sessionKey;
  }

  @Override
  public Object getPrincipal() {
    return openId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JwtAuthenticationToken)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    JwtAuthenticationToken that = (JwtAuthenticationToken) o;

    if (!Objects.equals(openId, that.openId)) {
      return false;
    }
    return Objects.equals(sessionKey, that.sessionKey);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (openId != null ? openId.hashCode() : 0);
    result = 31 * result + (sessionKey != null ? sessionKey.hashCode() : 0);
    return result;
  }
}
