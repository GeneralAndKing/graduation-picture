package wiki.gak.graduation.service;

import java.util.List;
import javax.xml.bind.ValidationException;
import wiki.gak.graduation.model.dto.PosAction;
import wiki.gak.graduation.model.entity.Atlas;

/**
 * @author BugRui
 * @date 2020/5/9 14:18
 **/
public interface MasterService {

  /**
   * 相册负责人创建相册
   *
   * @return
   */
  Atlas createAtlas(Atlas atlas) throws ValidationException;

  /**
   * 禁用所属Id的位置
   *
   * @param atlasId
   * @param posActionList
   * @return
   */
  Atlas operatePosList(Long atlasId, List<PosAction> posActionList) throws ValidationException;
}
