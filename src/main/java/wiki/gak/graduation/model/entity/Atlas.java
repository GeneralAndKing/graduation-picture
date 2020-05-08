package wiki.gak.graduation.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import wiki.gak.graduation.model.constant.AtlasType;

/**
 * 图册.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:46
 */
@Data
@Table
@Entity
public class Atlas {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 图册名称.
   */
  private String name;

  /**
   * 班级人数.
   */
  private Integer number;

  /**
   * 图册类型.
   */
  @Enumerated
  private AtlasType type;

  /**
   * 图册代码.
   */
  private String code;

  /**
   * 是否已经生成.
   */
  private Boolean generate;

  /**
   * 主管人微信.
   */
  private String weChat;

  /**
   * 排列规则.
   */
  private String rule;

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