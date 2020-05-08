package wiki.gak.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import wiki.gak.graduation.model.entity.Picture;

/**
 * 图片资源操作.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:55
 */
@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>,
    JpaSpecificationExecutor<Picture> {

}
