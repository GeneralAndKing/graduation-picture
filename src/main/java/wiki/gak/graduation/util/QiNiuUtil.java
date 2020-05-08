package wiki.gak.graduation.util;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import wiki.gak.graduation.model.exception.ResourceFileException;
import wiki.gak.graduation.model.properties.QiNiuProperties;

/**
 * 七牛云工具类.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/8 下午1:32
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class QiNiuUtil {

  private final QiNiuProperties qiNiuProperties;

  /**
   * 上传凭证
   *
   * @return 上传凭证
   */
  private Auth createAuth() {
    return Auth.create(qiNiuProperties.getAccessKey(),
        qiNiuProperties.getSecretKey());
  }


  /**
   * 创建简单 七牛云 配置信息
   *
   * @return 配置信息
   */
  private Configuration configurationQiNiu() {
    final val area = qiNiuProperties.getArea();
    Configuration config;
    switch (area) {
      case HUA_DONG:
        config = new Configuration(Region.huadong());
        break;
      case HUA_BEI:
        config = new Configuration(Region.huabei());
        break;
      case HUA_NAN:
        config = new Configuration(Region.huanan());
        break;
      case BEI_MEI:
        config = new Configuration(Region.beimei());
        break;
      case XIN_JIA_PO:
        config = new Configuration(Region.xinjiapo());
        break;
      default:
        config = new Configuration(Region.autoRegion());
    }
    return config;
  }


  /**
   * 简单文件上传，小文件上传
   *
   * @param file      文件
   * @param pathChild 子路径
   * @param fileName  文件名称
   * @return 默认的接口回复对象
   * @throws ResourceFileException 文件上传异常
   */
  public DefaultPutRet upload(MultipartFile file, String pathChild, String fileName) {
    String name = qiNiuProperties.getDirName() + "/" + pathChild + fileName;
    UploadManager uploadManager = new UploadManager(configurationQiNiu());
    String token = createAuth().uploadToken(qiNiuProperties.getBucket(), name);
    try {
      Response response = uploadManager.put(file.getBytes(), name, token);
      return JSON.parseObject(response.bodyString(), DefaultPutRet.class);
    } catch (IOException e) {
      throw new ResourceFileException(e);
    }
  }

  /**
   * 简单文件上传，小文件上传
   *
   * @param file     文件
   * @param fileName 文件名称
   * @return 默认的接口回复对象
   * @throws ResourceFileException 文件上传异常
   */
  public DefaultPutRet upload(MultipartFile file, String fileName) {
    return this.upload(file, "", fileName);
  }

  /**
   * 删除指定文件
   *
   * @param fileName 需要删除的文件名称
   */
  public void delete(String fileName) {
    BucketManager bucketManager = new BucketManager(createAuth(), configurationQiNiu());
    try {
      bucketManager
          .delete(qiNiuProperties.getBucket(), qiNiuProperties.getDirName() + "/" + fileName);
    } catch (QiniuException e) {
      throw new ResourceFileException("文件删除异常", e);
    }
  }

}
