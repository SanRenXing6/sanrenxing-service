package com.sanrenxing.service.dao.skill;

import com.sanrenxing.service.model.data.SkillLabel;

import java.util.List;
import java.util.Optional;

public interface SkillDao {
    int addSkill(SkillLabel skill);

    List<SkillLabel> getAllSkills();

    Optional<SkillLabel> getSkill(String name);

    int deleteSkill(String name);
}
