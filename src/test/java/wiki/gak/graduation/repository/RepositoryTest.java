package wiki.gak.graduation.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午4:59
 */
@SpringBootTest
public class RepositoryTest {

  @Autowired
  private AtlasRepository atlasRepository;

  @Autowired
  private PictureRepository pictureRepository;

  @Autowired
  private SchoolRepository schoolRepository;

  @Test
  @DisplayName("测试组件注入")
  void repositoryNotNull() {
    assertNotNull(atlasRepository);
    assertNotNull(pictureRepository);
    assertNotNull(schoolRepository);
  }
}
