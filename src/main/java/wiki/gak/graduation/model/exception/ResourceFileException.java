package wiki.gak.graduation.model.exception;

/**
 * 文件上传异常.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午1:59
 */
public class ResourceFileException extends RuntimeException {

  public ResourceFileException() {
    super("文件上传异常");
  }

  public ResourceFileException(String message) {
    super(message);
  }

  public ResourceFileException(Throwable cause) {
    super("文件上传异常", cause);
  }

  public ResourceFileException(String message, Throwable cause) {
    super(message, cause);
  }
}
