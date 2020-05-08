package wiki.gak.graduation.util;

import static org.junit.jupiter.api.Assertions.*;

import com.qiniu.storage.model.DefaultPutRet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午2:03
 */
@SpringBootTest
class QiNiuUtilTest {

  @Autowired
  private QiNiuUtil qiNiuUtil;

  @Test
  @DisplayName("简单文件上传测试")
  void uploadTest() {
    MockMultipartFile mockFile = new MockMultipartFile(
        "file", "test.txt",
        ",multipart/form-data", "hello upload".getBytes());
    DefaultPutRet result1 = qiNiuUtil.upload(mockFile, "test/", "test1.txt");
    assertNotNull(result1);
    DefaultPutRet result2 = qiNiuUtil.upload(mockFile, "test2.txt");
    assertNotNull(result2);
  }

  @AfterEach
  void tearDown() {
    // 清除测试文件
    qiNiuUtil.delete("test/test1.txt");
    qiNiuUtil.delete("test2.txt");
  }
}