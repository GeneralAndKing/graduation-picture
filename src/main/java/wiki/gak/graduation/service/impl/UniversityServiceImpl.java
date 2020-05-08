package wiki.gak.graduation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wiki.gak.graduation.model.entity.School;
import wiki.gak.graduation.repository.SchoolRepository;
import wiki.gak.graduation.service.UniversityService;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * @author BugRui
 * @date 2020/5/8 18:04
 **/
@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

  private static final List<String> PROVINCE_LIST =
      Arrays.asList("安徽省", "北京市", "福建省", "甘肃省", "广东省",
          "广西壮族自治区", "贵州省", "海南省", "河北省", "河南省",
          "黑龙江省", "湖北省", "湖南省", "吉林省", "江苏省", "江西省",
          "辽宁省", "内蒙古自治区", "宁夏回族自治区", "青海省", "山东省",
          "山西省", "陕西省", "上海市", "四川省", "天津市", "西藏自治区",
          "新疆维吾尔自治区", "云南省", "浙江省", "重庆市");

  @NotNull
  final SchoolRepository schoolRepository;

  @Override
  public List<School> findAllSchoolByProvince(String province) {
    return schoolRepository.findAllByProvince(province);
  }

  @Override
  public List<String> findAllProvince() {
    return PROVINCE_LIST;
  }
}
