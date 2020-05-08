package wiki.gak.graduation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import wiki.gak.graduation.model.properties.WeChatProperties;

/**
 * 应用相关配置.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午10:40
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final WeChatProperties weChatProperties;

  /**
   * Wechat 请求工具.
   *
   * @return RestTemplate
   */
  @Bean
  public RestTemplate weChatTemplate() {
    return new RestTemplateBuilder()
        .rootUri(weChatProperties.getBaseUrl())
        .build();
  }

}
