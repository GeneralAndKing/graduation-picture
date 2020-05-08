package wiki.gak.graduation.service;

import wiki.gak.graduation.model.entity.School;

import java.util.List;

/**
 * @author BugRui
 * @date 2020/5/8 17:56
 **/
public interface UniversityService {
    /**
     * 根据省份获取当前省的所有高校
     *
     * @param province
     * @return
     */
    List<School> findAllSchoolByProvince(String province);

    /**
     * 获取所有省份
     *
     * @return
     */
    List<String> findAllProvince();
}
