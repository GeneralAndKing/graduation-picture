package wiki.gak.graduation.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import wiki.gak.graduation.model.constant.AtlasType;
import wiki.gak.graduation.model.entity.Atlas;
import wiki.gak.graduation.model.entity.Picture;
import wiki.gak.graduation.model.exception.ResourceFileException;
import wiki.gak.graduation.util.ImageUtil;

/**
 * @author BugRui
 * @date 2020/5/8 23:29
 **/
@Component
@RequiredArgsConstructor
public class AtlasProcessor {

  @NotNull
  final ImageUtil imageUtil;

  /**
   * 组合所有图片为一个相册
   *
   * @param atlas       相册
   * @param pictureList 图片列表
   * @return 结果
   */
  BufferedImage process(Atlas atlas, List<Picture> pictureList, AtlasType atlasType) {
    List<Integer> rule = atlas.getRule();
    //获取规则中最大一行作为画布最大宽度
    Integer canvasWidth = Collections.max(rule);
    Integer canvasHeight = rule.size();
    //创建画布
    BufferedImage canvas = imageUtil.createCanvas(canvasWidth, canvasHeight);
    //获取画笔
    Graphics2D graphics = canvas.createGraphics();
    for (Picture picture :
        pictureList) {
      Integer x = picture.getX();
      Integer y = picture.getY();
      //TODO 从网络获取图片 根据atlasType中宽高获取图片
      BufferedImage bufferedImage = null;
      //TODO 可做个性化 某某图片压住一个角
      if (imageUtil.overlap(graphics, bufferedImage, (x - 1) * atlasType.getImgWidth(),
          (y - 1) * atlasType.getImgHeight())) {
        //TODO 抛出异常
        throw new ResourceFileException("图片处理异常");
      }
    }
    return canvas;
  }

}
