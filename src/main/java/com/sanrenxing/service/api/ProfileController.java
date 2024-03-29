package com.sanrenxing.service.api;


import com.sanrenxing.service.model.Profile;
import com.sanrenxing.service.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/profiles")
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping
    public void addProfie(@Valid @RequestBody @NonNull Profile profile) {
        profileService.addProfile(profile);
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping(path="{id}")
    public Profile getProfileById(@PathVariable("id") UUID id) {
        return profileService.getProfile(id).orElse(null);
    }

    @DeleteMapping(path="{id}")
    public void deleteProfile(@PathVariable("id") UUID id) {
        profileService.deleteProfile(id);
    }

    @PutMapping(path="{id}")
    public void updateProfile(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Profile profile) {
        profileService.updateProfile(id, profile);
    }

}
