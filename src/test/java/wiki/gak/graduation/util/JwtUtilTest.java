package wiki.gak.graduation.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashMap;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 上午12:43
 */
@SpringBootTest
class JwtUtilTest {

  @Autowired
  private JwtUtil jwtUtil;

  @Test
  @DisplayName("测试 claim 为空的情况")
  public void jwtEmptyTest() {
    String token = jwtUtil.generate("admin", new HashMap<>());
    JSONObject result = jwtUtil.parse(token);
    assertEquals("admin", result.getAsString("sub"));
    assertEquals("graduation-picture", result.getAsString("aud"));
    assertEquals("http://ayue.wiki", result.getAsString("iss"));
    assertTrue(new Date().before(new Date(result.getAsNumber("exp").longValue() * 1000)));
  }

  @Test
  @DisplayName("测试 claim 不为空的情况")
  void jwtClaimTest() {
    HashMap<String, Object> claim = new HashMap<>(1);
    claim.put("session_key", "123456");
    String token = jwtUtil.generate("admin", claim);
    JSONObject result = jwtUtil.parse(token);
    assertEquals("123456", result.getAsString("session_key"));
  }
}