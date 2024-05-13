package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.skill.SkillDao;
import com.sanrenxing.service.model.data.SkillLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    private final SkillDao skillDao;

    @Autowired
    public SkillService(@Qualifier("skillPostgreSQL") SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    public int addSkill(SkillLabel skillLabel){
        return skillDao.addSkill(skillLabel);
    }

    public List<SkillLabel> getAllSkills() {
        return skillDao.getAllSkills();
    }

    public Optional<SkillLabel> getSkill(String name) {
        return skillDao.getSkill(name);
    }

    public int deleteSkill (String name) {
        return skillDao.deleteSkill(name);
    }
}
