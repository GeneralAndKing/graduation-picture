package wiki.gak.graduation.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Component;
import wiki.gak.graduation.model.exception.PermissionException;
import wiki.gak.graduation.model.properties.JwtProperties;

/**
 * Jwt 工具类.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/5/9 上午12:17
 */
@Slf4j
@Component
public class JwtUtil {

  private final JwtProperties jwtProperties;
  private final RSAKey rsaJwk;

  public JwtUtil(JwtProperties jwtProperties) {
    this.jwtProperties = jwtProperties;
    // 自动生成 RSA Key，每次启动都有所不同
    try {
      rsaJwk = new RSAKeyGenerator(2048)
          .keyID("graduation-picture")
          .generate();
    } catch (JOSEException e) {
      throw new PermissionException("Jwt 生成异常", e);
    }
  }

  /**
   * JWT 生成
   *
   * @param subject 用户 open id
   * @param claims  其他扩展信息
   * @return 结果
   */
  public String generate(String subject, Map<String, Object> claims) {
    Date now = new Date();
    // 构建必要的参数
    Builder builder = new Builder()
        .subject(subject)
        .issuer(jwtProperties.getIssuer())
        .expirationTime(new Date(
            now.getTime() + Duration.ofMinutes(jwtProperties.getExpirationMinutes()).toMillis()))
        .notBeforeTime(now)
        .jwtID(String.valueOf(UUID.nameUUIDFromBytes(subject.getBytes())))
        .audience("graduation-picture")
        .issueTime(now);
    claims.forEach(builder::claim);
    JWTClaimsSet claimsSet = builder.build();
    try {
      JWSSigner signer = new RSASSASigner(rsaJwk);
      SignedJWT signedJwt = new SignedJWT(
          new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaJwk.getKeyID()).build(),
          claimsSet);
      signedJwt.sign(signer);
      // 生成 JWT
      return signedJwt.serialize();
    } catch (JOSEException e) {
      throw new PermissionException("Jwt 生成异常", e);
    }
  }

  /**
   * JWT token 解析
   *
   * @param jwtToken token
   * @return 结果
   */
  public JSONObject parse(String jwtToken) {
    try {
      SignedJWT signedJwt = SignedJWT.parse(jwtToken);
      JWSVerifier verifier = new RSASSAVerifier(rsaJwk.toPublicJWK());
      // 验证必要信息
      if (!signedJwt.verify(verifier)) {
        throw new PermissionException("Jwt 令牌验证失败");
      }
      return signedJwt.getJWTClaimsSet().toJSONObject();
    } catch (JOSEException | ParseException e) {
      throw new PermissionException("Jwt 解析异常", e);
    }
  }
}
