package wiki.gak.graduation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wiki.gak.graduation.model.entity.School;
import wiki.gak.graduation.service.UniversityService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/5/8 17:52
 **/
@RestController
@RequiredArgsConstructor
public class UniversityController {

  @NotNull
  final UniversityService universityService;

  /**
   * 获取指定省份下的学校信息
   *
   * @param province 省份
   * @return 学校列表
   */
  @GetMapping("/school/{province}")
  public List<School> findAllSchoolByProvince(@PathVariable String province) {
    return universityService.findAllSchoolByProvince(province);
  }

  /**
   * 获取所有省份信息
   *
   * @return 省份
   */
  @GetMapping("/province")
  public List<String> findAllProvince() {
    return universityService.findAllProvince();
  }
}
