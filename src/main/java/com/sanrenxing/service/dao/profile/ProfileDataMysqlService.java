package com.sanrenxing.service.dao.profile;

import com.sanrenxing.service.util.JsonConverter;
import com.sanrenxing.service.model.data.Feedback;
import com.sanrenxing.service.model.data.Profile;
import com.sanrenxing.service.model.data.Skill;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("profileMySQL")
public class ProfileDataMysqlService implements ProfileDao {
    private final JdbcTemplate jdbcTemplate;

    public ProfileDataMysqlService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addProfile(Profile profile) {
        final UUID id = UUID.randomUUID();
        final String skillsJson = JsonConverter.serialize(profile.getSkills());
        final String feedbacksJson = JsonConverter.serialize(profile.getFeedbacks());
        final String sql = """
            INSERT INTO profiles(id, userId, description, imageId, rate, needs, skills, feedbacks)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                id.toString(),
                profile.getUserId().toString(),
                profile.getDescription(),
                profile.getImageId().toString(),
                profile.getRate(),
                profile.getNeeds(),
                skillsJson,
                feedbacksJson
        );
    }

    @Override
    public List<Profile> getAllProfiles() {
        final String sql = "SELECT * FROM profiles;";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            UUID userId = UUID.fromString(resultSet.getString("userId"));
            String description = resultSet.getString("description");
            UUID imageId = UUID.fromString(resultSet.getString("imageId"));
            int rate = resultSet.getInt("rate");
            String needs = resultSet.getString("needs");
            List<Skill> skills = JsonConverter.deserialize(resultSet.getString("skills"), Skill.class);
            List<Feedback> feedbacks = JsonConverter.deserialize(resultSet.getString("feedbacks"), Feedback.class);
            return new Profile(id, userId, description, imageId, rate, needs, skills, feedbacks);
        });
    }

    @Override
    public Optional<Profile> getProfile(UUID id) {
        final String sql = "SELECT * FROM profiles WHERE id = ?;";
        try {
            Profile profile = jdbcTemplate.queryForObject(sql, new Object[]{id.toString()}, (resultSet, i) -> {
                UUID userId = UUID.fromString(resultSet.getString("userId"));
                String description = resultSet.getString("description");
                UUID imageId = UUID.fromString(resultSet.getString("imageId"));
                int rate = resultSet.getInt("rate");
                String needs = resultSet.getString("needs");
                List<Skill> skills = JsonConverter.deserialize(resultSet.getString("skills"), Skill.class);
                List<Feedback> feedbacks = JsonConverter.deserialize(resultSet.getString("feedbacks"), Feedback.class);
                return new Profile(id, userId, description, imageId, rate, needs, skills, feedbacks);
            });
            return Optional.ofNullable(profile);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Profile> getProfileByUserId(UUID userId) {
        final String sql = "SELECT * FROM profiles WHERE userId = ?;";
        try {
            Profile profile = jdbcTemplate.queryForObject(sql, new Object[]{userId.toString()}, (resultSet, i) -> {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String description = resultSet.getString("description");
                UUID imageId = UUID.fromString(resultSet.getString("imageId"));
                int rate = resultSet.getInt("rate");
                String needs = resultSet.getString("needs");
                List<Skill> skills = JsonConverter.deserialize(resultSet.getString("skills"), Skill.class);
                List<Feedback> feedbacks = JsonConverter.deserialize(resultSet.getString("feedbacks"), Feedback.class);
                return new Profile(id, userId, description, imageId, rate, needs, skills, feedbacks);
            });
            return Optional.ofNullable(profile);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteProfile(UUID id) {
        final String sql = "DELETE FROM profiles WHERE id = ?;";
        return jdbcTemplate.update(sql, id.toString());
    }

    @Override
    public int updateProfile(UUID id, Profile profile) {
        final String skillsJson = JsonConverter.serialize(profile.getSkills());
        final String feedbacksJson = JsonConverter.serialize(profile.getFeedbacks());
        final String sql = """
                UPDATE profiles
                SET userId = ?, description = ?, imageId = ?, rate = ?, needs = ?, skills = ?, feedbacks = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql,
                profile.getUserId().toString(),
                profile.getDescription(),
                profile.getImageId().toString(),
                profile.getRate(),
                profile.getNeeds(),
                skillsJson,
                feedbacksJson,
                id.toString());
    }

    @Override
    public List<Profile> searchBySkill(String skillText) {
        final String sql = "SELECT * FROM profiles WHERE MATCH(skills_values) AGAINST (? IN NATURAL LANGUAGE MODE) ORDER BY rate DESC;";
        return jdbcTemplate.query(sql, new Object[]{skillText}, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            UUID userId = UUID.fromString(resultSet.getString("userId"));
            String description = resultSet.getString("description");
            UUID imageId = UUID.fromString(resultSet.getString("imageId"));
            int rate = resultSet.getInt("rate");
            String needs = resultSet.getString("needs");
            List<Skill> skills = JsonConverter.deserialize(resultSet.getString("skills"), Skill.class);
            List<Feedback> feedbacks = JsonConverter.deserialize(resultSet.getString("feedbacks"), Feedback.class);
            return new Profile(id, userId, description, imageId, rate, needs, skills, feedbacks);
        });
    }
}
