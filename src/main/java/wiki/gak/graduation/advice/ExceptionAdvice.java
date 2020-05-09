package wiki.gak.graduation.advice;

import com.alibaba.fastjson.JSONObject;
import javax.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wiki.gak.graduation.model.exception.PermissionException;

/**
 * 统一异常处理.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 上午10:19
 */
@RestControllerAdvice
public class ExceptionAdvice {

  /**
   * 401 异常.
   *
   * @param permissionException 异常
   * @return 结果
   */
  @ExceptionHandler(PermissionException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public JSONObject permissionException(PermissionException permissionException) {
    return wrapException(permissionException);
  }

  /**
   * 403 异常.
   *
   * @param accessDeniedException 异常
   * @return 结果
   */
  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public JSONObject permissionException(AccessDeniedException accessDeniedException) {
    return wrapException(accessDeniedException);
  }

  /**
   * 400 异常.
   *
   * @param validationException 验证异常
   * @return 结果
   */
  @ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JSONObject validationException(ValidationException validationException) {
    return wrapException(validationException);
  }

  /**
   * 500 其他异常.
   *
   * @param exception 异常
   * @return 结果
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public JSONObject otherException(Exception exception) {
    return wrapException(exception);
  }

  /**
   * 异常封装
   *
   * @param exception 异常
   * @return 结果
   */
  private JSONObject wrapException(Exception exception) {
    JSONObject result = new JSONObject();
    result.put("error", exception.getLocalizedMessage());
    return result;
  }

}
