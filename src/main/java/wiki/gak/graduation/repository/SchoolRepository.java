package wiki.gak.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import wiki.gak.graduation.model.entity.School;

/**
 * 学校资源操作.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:54
 */
@Repository
public interface SchoolRepository extends JpaRepository<School, Long>,
    JpaSpecificationExecutor<School> {

}
