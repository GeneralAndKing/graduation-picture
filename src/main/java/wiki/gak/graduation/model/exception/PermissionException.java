package wiki.gak.graduation.model.exception;

/**
 * 权限异常，对应 401 错误。
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 上午12:27
 */
public class PermissionException extends RuntimeException {

  /**
   * 默认情况
   */
  public PermissionException() {
    super("权限分配异常");
  }

  /**
   * 自定义异常
   *
   * @param message 异常信息
   */
  public PermissionException(String message) {
    super(message);
  }

  /**
   * 自定义消息与异常
   *
   * @param message 消息
   * @param cause   异常
   */
  public PermissionException(String message, Throwable cause) {
    super(message, cause);
  }
}
