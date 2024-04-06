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

@Repository("profilePostgreSQL")
public class ProfileDataAccessService implements ProfileDao {
    private final JdbcTemplate jdbcTemplate;

    public ProfileDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addProfile(Profile profile) {
        final UUID id = UUID.randomUUID();
        final String skillsJson = JsonConverter.serialize(profile.getSkills());
        final String feedbacksJson = JsonConverter.serialize(profile.getFeedbacks());
        final String sql = """
            INSERT INTO profiles(id, userId, description, rate, needs, skills, feedbacks)
            VALUES(?, ?, ?, ?, ?, CAST(? AS json), CAST(? AS json))
        """;
        return jdbcTemplate.update(sql,
                id,
                profile.getUserId(),
                profile.getDescription(),
                profile.getRate(),
                profile.getNeeds(),
                skillsJson,
                feedbacksJson
        );

    }

    @Override
    public List<Profile> getAllProfiles() {
        final String sql = "SELECT * FROM profiles;";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    UUID userId = UUID.fromString(resultSet.getString("userId"));
                    String description = resultSet.getString("description");
                    int rate = resultSet.getInt("rate");
                    String needs = resultSet.getString("needs");
                    List<Skill> skills = JsonConverter.deserialize(resultSet.getString("skills"), Skill.class);
                    List<Feedback> feedbacks = JsonConverter.deserialize(resultSet.getString("feedbacks"), Feedback.class);
                   return new Profile(id, userId, description, rate, needs, skills, feedbacks);
                });
    }

    @Override
    public Optional<Profile> getProfile(UUID id) {
        final String sql = "SELECT * FROM profiles WHERE id = ?;";
        try{
            Profile profile = jdbcTemplate.queryForObject(sql,
                    new Object[]{id},
                    (resultSet, i) -> {
                        UUID userId = UUID.fromString(resultSet.getString("userId"));
                        String description = resultSet.getString("description");
                        int rate = resultSet.getInt("rate");
                        String needs = resultSet.getString("needs");
                        List<Skill> skills = JsonConverter.deserialize(resultSet.getString("skills"), Skill.class);
                        List<Feedback> feedbacks = JsonConverter.deserialize(resultSet.getString("feedbacks"),Feedback.class);
                        return new Profile(id, userId, description, rate, needs, skills, feedbacks);
                    });
            return Optional.ofNullable(profile);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }

    }

    @Override
    public int deleteProfile(UUID id) {
        final String sql = "DELETE FROM profiles WHERE id = ?;";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int updateProfile(UUID id, Profile profile) {
        // TODO: Conditional updates based on non-null values
        final String skillsJson = JsonConverter.serialize(profile.getSkills());
        final String feedbacksJson = JsonConverter.serialize(profile.getFeedbacks());
        final String sql = """ 
                UPDATE profiles
                SET userId = ?, description = ?, rate = ?, needs = ?, skills = CAST(? AS json), feedbacks = CAST(? AS json)
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql,
                profile.getUserId(),
                profile.getDescription(),
                profile.getRate(),
                profile.getNeeds(),
                skillsJson,
                feedbacksJson,
                id);
    }

}
