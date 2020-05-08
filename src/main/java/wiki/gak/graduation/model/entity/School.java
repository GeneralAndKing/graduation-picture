package wiki.gak.graduation.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 学校.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:38
 */
@Data
@Table
@Entity
public class School {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 名称.
   */
  private String name;

  /**
   * 所属省份.
   */
  private String province;

}
