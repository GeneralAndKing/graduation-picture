package wiki.gak.graduation.model.constant;

import lombok.Getter;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:47
 */
@Getter
public enum AtlasType {
  /**
   * 生活照
   */
  LIFE(600, 600),
  /**
   * 一寸招
   */
  ONE_INCH(400, 400),
  /**
   * 学士照
   */
  BACHELOR(400, 300);

  private final Integer imgWidth;
  private final Integer imgHeight;

  AtlasType(Integer imgWidth, Integer imgHeight) {
    this.imgWidth = imgWidth;
    this.imgHeight = imgHeight;
  }
}
