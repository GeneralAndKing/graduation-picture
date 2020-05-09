package wiki.gak.graduation.service.impl;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wiki.gak.graduation.model.dto.PosAction;
import wiki.gak.graduation.model.entity.Atlas;
import wiki.gak.graduation.repository.AtlasRepository;
import wiki.gak.graduation.service.MasterService;

/**
 * @author BugRui
 * @date 2020/5/9 14:18
 **/
@Service
@RequiredArgsConstructor
public class MasterServiceImpl implements MasterService {

  @NotNull
  final AtlasRepository atlasRepository;


  void validateAtlas(Atlas atlas) throws ValidationException {
    if (StringUtils.isBlank(atlas.getName())) {
      throw new ValidationException("相册名字不能为空");
    }
    if (atlas.getName().length() >= 20) {
      throw new ValidationException("非法的相册名字");
    }
    if (ObjectUtils.isEmpty(atlas.getType())) {
      throw new ValidationException("非法的相册类型");
    }
    List<List<Integer>> rule = atlas.getRule();
    Integer totalPerson = 0;
    for (List<Integer> axisX : rule) {
      for (Integer flag : axisX) {
        if (flag == 1) {
          totalPerson += 1;
        } else if (flag != 0) {
          throw new ValidationException("非法的相册规则");
        }
      }
    }
    if (!totalPerson.equals(atlas.getNumber())) {
      throw new ValidationException("非法的相册人数");
    }
  }

  String generateCode(Atlas atlas) {
    //不足的前面补零为6位 暂时的
    return String.format("%06d", atlas.getId());
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Atlas createAtlas(Atlas atlas) throws ValidationException {
    validateAtlas(atlas);
    String weChat = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    atlas.setCode(null)
        .setId(null)
        .setWeChat(weChat);
    Atlas saveAtlas = atlasRepository.save(atlas);
    String code = generateCode(saveAtlas);
    saveAtlas.setCode(code);
    return atlasRepository.save(saveAtlas);
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
  public Atlas operatePosList(Long atlasId, List<PosAction> posActionList)
      throws ValidationException {
    Atlas atlas = atlasRepository.findById(atlasId)
        .orElseThrow(() -> new ValidationException("atlas Id 错误"));
    List<List<Integer>> rule = atlas.getRule();
    for (PosAction posAction : posActionList) {
      Integer x = posAction.getX();
      Integer y = posAction.getY();
      if (posAction.getAction() < 0 && posAction.getAction() > 1) {
        throw new ValidationException("错误的坐标操作");
      }
      if (x > rule.size()) {
        throw new ValidationException("坐标错误");
      }
      List<Integer> axisX = rule.get(posAction.getX());
      if (y > axisX.size()) {
        throw new ValidationException("坐标错误");
      }
      axisX.set(y, posAction.getAction());
    }
    return atlasRepository.save(atlas);
  }


}
