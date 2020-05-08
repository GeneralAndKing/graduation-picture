package wiki.gak.graduation.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import wiki.gak.graduation.model.constant.Area;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午1:30
 */
@Data
@Component
@ConfigurationProperties(prefix = "application.qiniu")
public class QiNiuProperties {
  /**
   * 七牛云开发者中心 accessKey
   */
  private String accessKey;
  /**
   * 七牛云开发者中心 secretKey
   */
  private String secretKey;
  /**
   * 空间名称 bucket
   */
  private String bucket;
  /**
   * 地区
   */
  private Area area;
  /**
   * domain
   */
  private String domain;
  /**
   * dirName
   */
  private String dirName;
}

