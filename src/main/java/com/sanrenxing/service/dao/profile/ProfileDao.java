package com.sanrenxing.service.dao.profile;

import com.sanrenxing.service.model.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileDao {
    int addProfile(Profile profile);

    List<Profile> getAllProfiles();

    Optional<Profile> getProfile(UUID id);

    int deleteProfile(UUID id);

    int updateProfile(UUID id, Profile profile);
}
