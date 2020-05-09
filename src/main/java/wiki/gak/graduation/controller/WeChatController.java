package wiki.gak.graduation.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.alibaba.fastjson.JSONObject;
import javax.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.gak.graduation.service.WeChatService;

/**
 * 微信处理.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午10:48
 */
@RestController
@RequestMapping("/wechat")
@RequiredArgsConstructor
public class WeChatController {

  private final WeChatService weChatService;

  /**
   * 小程序登录
   *
   * @param jsonObject 参数
   * @return 结果
   */
  @PostMapping("/code")
  public HttpEntity<JSONObject> codeToSession(@RequestBody JSONObject jsonObject) {
    String code = jsonObject.getString("code");
    if (StringUtils.isEmpty(code)) {
      throw new ValidationException("缺少必要参数");
    }
    final JSONObject result = new JSONObject();
    result.put("accessToken", weChatService.codeToSession(code));
    return ok().body(result);
  }

  /**
   * 测试受保护的资源
   */
  @GetMapping("security")
  @PreAuthorize("isAuthenticated()")
  public HttpEntity<String> securityMessage() {
    return ok().body("security");
  }
}
