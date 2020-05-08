package wiki.gak.graduation.util;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import wiki.gak.graduation.model.exception.ResourceFileException;

/**
 * 图片处理工具类.
 *
 * @author BugRui
 * @date 2020/5/8 23:15
 **/
@Slf4j
@Component
public class ImageUtil {

  /**
   * 从url读取图片并返回bufferedImage
   *
   * @param url url
   * @return BufferedImage
   */
  public BufferedImage read(URL url) {
    try {
      return ImageIO.read(url);
    } catch (IOException e) {
      throw new ResourceFileException("图片读取发生了错误", e);
    }
  }

  /**
   * 从url读取图片并返回bufferedImage
   *
   * @param path 文件路径
   * @return BufferedImage
   */
  public BufferedImage read(String path) {
    try {
      return ImageIO.read(new File(path));
    } catch (IOException e) {
      throw new ResourceFileException("图片读取发生了错误", e);
    }
  }

  /**
   * 多个图层创建.
   *
   * @param g     图片
   * @param image 图片
   * @param x     x
   * @param y     y
   * @return 结果
   */
  public Boolean overlap(Graphics2D g, BufferedImage image, Integer x, Integer y) {
    return g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
  }

  /**
   * Canvas 创建
   *
   * @param width  宽
   * @param height 高
   * @return 结果
   */
  public BufferedImage createCanvas(Integer width, Integer height) {
    return new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR)
        .createGraphics()
        .getDeviceConfiguration()
        .createCompatibleImage(width, height, Transparency.TRANSLUCENT);
  }
}
