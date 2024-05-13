package com.sanrenxing.service.api;

import com.sanrenxing.service.model.data.Profile;
import com.sanrenxing.service.model.data.SkillLabel;
import com.sanrenxing.service.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/skills")
@RestController
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<SkillLabel> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping(path="{name}")
    public SkillLabel getProfileById(@PathVariable("name") String name) {
        return skillService.getSkill(name).orElse(null);
    }
}
