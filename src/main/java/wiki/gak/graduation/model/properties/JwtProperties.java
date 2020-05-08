package wiki.gak.graduation.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 上午12:15
 */
@Data
@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {

  /**
   * jwt issuer
   */
  private String issuer = "http://ayue.wiki";

  /**
   * 有效时间
   */
  private Long expirationMinutes = 60L;

}
