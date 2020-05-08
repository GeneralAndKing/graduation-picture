package wiki.gak.graduation.model.exception;

/**
 * 文件异常.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午1:59
 */
public class ResourceFileException extends RuntimeException {

  /**
   * 默认情况
   */
  public ResourceFileException() {
    super("文件上传异常");
  }

  /**
   * 自定义消息
   *
   * @param message 消息
   */
  public ResourceFileException(String message) {
    super(message);
  }

  /**
   * 自定义异常
   *
   * @param cause 异常
   */
  public ResourceFileException(Throwable cause) {
    super("文件上传异常", cause);
  }

  /**
   * 自定义消息与异常
   *
   * @param message 消息
   * @param cause   异常
   */
  public ResourceFileException(String message, Throwable cause) {
    super(message, cause);
  }
}
