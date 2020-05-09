package wiki.gak.graduation.model.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author BugRui
 * @date 2020/5/9 23:08
 **/
@Data
public class PosAction {

  Integer x;
  Integer y;
  /**
   * 操作type 0 删除 1 启用
   */
  Integer action;
}
