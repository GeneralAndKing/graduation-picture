package wiki.gak.graduation.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 图片.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:41
 */
@Data
@Table
@Entity
public class Picture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 图片名称.
   */
  private String name;

  /**
   * 用户姓名.
   */
  private String userName;

  /**
   * 微信id.
   */
  private String weChat;

  /**
   * 所处坐标 x.
   */
  private Integer x;

  /**
   * 所处坐标 y.
   */
  private Integer y;

  /**
   * 创建时间
   */
  @CreatedDate
  private LocalDateTime createTime;

  /**
   * 修改时间.
   */
  @LastModifiedDate
  private LocalDateTime modifyTime;

}
