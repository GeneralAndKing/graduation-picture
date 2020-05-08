package wiki.gak.graduation.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信配置.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午10:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "application.wechat")
public class WeChatProperties {

  /**
   * 微信小程序 app id
   */
  private String appId;

  /**
   * 微信小程序 app secret
   */
  private String secret;

  /**
   * 微信小程序 base url
   */
  private String baseUrl = "https://api.weixin.qq.com";

}
