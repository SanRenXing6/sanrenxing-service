package com.sanrenxing.service.dao.skill;

import com.sanrenxing.service.model.data.SkillLabel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("skillPostgreSQL")
public class SkillDataAccessService implements SkillDao {

    private final JdbcTemplate jdbcTemplate;

    public SkillDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int addSkill(SkillLabel skill) {
        final String sql = """
                INSERT INTO skills(name, category) VALUES(?, ?);
                """;
        return jdbcTemplate.update(sql, skill.getName(), skill.getCategory());
    }

    @Override
    public List<SkillLabel> getAllSkills() {
        final String sql = "SELECT * FROM skills;";
        return jdbcTemplate.query(sql, (resultSet, i)-> {
            String name = resultSet.getString("name");
            String category = resultSet.getString("category");
            return new SkillLabel(name, category);
        });
    }

    @Override
    public Optional<SkillLabel> getSkill(String name) {
        final String sql = "SELECT * FROM skills WHERE name=?;";
        try {
            SkillLabel skill = jdbcTemplate.queryForObject(sql,
                    new Object[]{name},
                    (resultSet, i) -> {
                        String category = resultSet.getString("category");
                        return new SkillLabel(name, category);
                    });
            return Optional.ofNullable(skill);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public int deleteSkill(String name) {
        final String sql = "DELETE FROM skills WHERE name = ?;";
        return jdbcTemplate.update(sql,name);
    }
}
