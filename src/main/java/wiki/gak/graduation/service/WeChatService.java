package wiki.gak.graduation.service;

/**
 * 小程序处理.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午10:56
 */
public interface WeChatService {

  /**
   * 授权码转化为 JWT
   *
   * @param code 授权码
   * @return JWT token
   */
  String codeToSession(String code);

}
