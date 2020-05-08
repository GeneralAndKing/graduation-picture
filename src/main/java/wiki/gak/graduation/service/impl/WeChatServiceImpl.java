package wiki.gak.graduation.service.impl;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import wiki.gak.graduation.model.exception.PermissionException;
import wiki.gak.graduation.model.properties.WeChatProperties;
import wiki.gak.graduation.service.WeChatService;
import wiki.gak.graduation.util.JwtUtil;

/**
 * 小程序处理.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午10:56
 */
@Service
@RequiredArgsConstructor
public class WeChatServiceImpl implements WeChatService {

  private final RestTemplate weChatTemplate;
  private final WeChatProperties weChatProperties;
  private final JwtUtil jwtUtil;
  private final static String ERR_CODE = "errcode";

  @Override
  @SuppressWarnings("SpellCheckingInspection")
  public String codeToSession(String code) {
    final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("/sns/jscode2session")
        .queryParam("appid", weChatProperties.getAppId())
        .queryParam("secret", weChatProperties.getSecret())
        .queryParam("js_code", code)
        .queryParam("grant_type", "authorization_code");
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<?> entity = new HttpEntity<>(headers);
    // 请求登录接口
    JSONObject body = weChatTemplate
        .exchange(builder.toUriString(), HttpMethod.GET, entity, JSONObject.class).getBody();
    // 失败处理
    if (body == null) {
      throw new PermissionException("请求异常");
    }
    if (body.getInteger(ERR_CODE) != null && body.getInteger(ERR_CODE) != 0) {
      throw new PermissionException(String.format("验证失败: %s", body.getString("errmsg")));
    }
    // 生成 JWT
    Map<String, Object> claim = new HashMap<>(1);
    claim.put("session_key", body.getString("session_key"));
    // TODO: 需要将 session_key 与 openid 进行保存在 redis/session 之中，以确认用户登录状态。
    return jwtUtil.generate(body.getString("openid"), claim);
  }
}
