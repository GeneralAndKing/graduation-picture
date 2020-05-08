package wiki.gak.graduation.util;

import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author BugRui
 * @date 2020/5/8 23:15
 **/
@Slf4j
@Component
public class ImageUtil {

  /**
   * 从url读取图片并返回bufferedImage
   *
   * @param url
   * @return
   */
  public BufferedImage read(URL url) {
    try {
      return ImageIO.read(url);
    } catch (IOException e) {
      e.printStackTrace();
      //TODO 抛出异常
      throw new RuntimeException();
    }
  }

  public BufferedImage read(String path) {
    try {
      return ImageIO.read(new File(path));
    } catch (IOException e) {
      e.printStackTrace();
      //TODO 抛出异常
      throw new RuntimeException();
    }
  }

  public Boolean overlap(Graphics2D g, BufferedImage image, Integer x, Integer y) {
    return g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
  }

  public BufferedImage createCanvas(Integer width, Integer height) {
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
    Graphics2D graphics = bi.createGraphics();
    bi = graphics.getDeviceConfiguration()
        .createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    return bi;
  }
}
