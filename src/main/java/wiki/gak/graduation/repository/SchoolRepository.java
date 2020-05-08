package wiki.gak.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import wiki.gak.graduation.model.entity.School;

import java.util.List;

/**
 * 学校资源操作.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:54
 */
@Repository
public interface SchoolRepository extends JpaRepository<School, Long>,
    JpaSpecificationExecutor<School> {

  /**
   * 根据省份获取所有的学校
   *
   * @param province 省份
   * @return 学校
   */
  List<School> findAllByProvince(String province);

}
