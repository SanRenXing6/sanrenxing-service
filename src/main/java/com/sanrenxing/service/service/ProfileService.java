package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.profile.ProfileDao;
import com.sanrenxing.service.model.data.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileService(@Qualifier("profileMySQL") ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public int addProfile(Profile profile){
        return profileDao.addProfile(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileDao.getAllProfiles();
    }

    public Optional<Profile> getProfile(UUID id) {
        return profileDao.getProfile(id);
    }

    public Optional<Profile> getProfileByUserId(UUID userId) {
        return profileDao.getProfileByUserId(userId);
    }

    public int deleteProfile(UUID id) {
        return profileDao.deleteProfile(id);
    }

    public int updateProfile(UUID id, Profile profile) {
        return profileDao.updateProfile(id, profile);
    }

    public List<Profile> searchBySkill(String text) {return profileDao.searchBySkill(text); }
}
