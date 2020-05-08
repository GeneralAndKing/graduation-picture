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

    @GetMapping("/school/{province}")
    public List<School> findAllSchoolByProvince(@PathVariable String province) {
        return universityService.findAllSchoolByProvince(province);
    }

    @GetMapping("/province")
    public List<String> findAllProvince() {
        return universityService.findAllProvince();
    }
}
