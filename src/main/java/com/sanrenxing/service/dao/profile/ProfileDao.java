package com.sanrenxing.service.dao.profile;

import com.sanrenxing.service.model.data.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileDao {
    int addProfile(Profile profile);

    List<Profile> getAllProfiles();

    Optional<Profile> getProfile(UUID id);

    Optional<Profile> getProfileByUserId(UUID userId);

    int deleteProfile(UUID id);

    int updateProfile(UUID id, Profile profile);

    List<Profile> searchProfiles(String text);
}
