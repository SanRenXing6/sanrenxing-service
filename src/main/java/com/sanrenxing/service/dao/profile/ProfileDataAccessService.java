package com.sanrenxing.service.dao.profile;

import com.sanrenxing.service.model.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProfileDataAccessService implements ProfileDao {
    private final JdbcTemplate jdbcTemplate;

    public ProfileDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addProfile(Profile profile) {
        return 0;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return null;
    }

    @Override
    public Optional<Profile> getProfile(UUID id) {
        return Optional.empty();
    }

    @Override
    public int deleteProfile(UUID id) {
        return 0;
    }

    @Override
    public int updateProfile(UUID id, Profile profile) {
        return 0;
    }
}
