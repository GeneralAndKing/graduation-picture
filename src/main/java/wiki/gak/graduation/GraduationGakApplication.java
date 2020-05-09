package wiki.gak.graduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Graduation Application
 *
 * @author echo
 */
@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class GraduationGakApplication {

  public static void main(String[] args) {
    SpringApplication.run(GraduationGakApplication.class, args);
  }

}
