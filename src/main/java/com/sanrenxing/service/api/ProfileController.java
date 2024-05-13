package com.sanrenxing.service.api;

import com.sanrenxing.service.model.data.Profile;
import com.sanrenxing.service.model.data.SkillLabel;
import com.sanrenxing.service.service.ProfileService;
import com.sanrenxing.service.service.SkillService;
import com.sanrenxing.service.service.UserService;
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
    private final UserService userService;
    private final SkillService skillService;

    @Autowired
    public ProfileController(ProfileService profileService,
                             UserService userService,
                             SkillService skillService) {
        this.profileService = profileService;
        this.userService = userService;
        this.skillService = skillService;
    }

    @PostMapping
    public void addProfie(@Valid @RequestBody @NonNull Profile profile) {
        profileService.addProfile(profile);
        userService.updateUserProfile(profile.getUserId(), true);
        profile.getSkills().forEach((skill) -> {
            if(skillService.getSkill(skill.getName()).isEmpty()){
                skillService.addSkill(new SkillLabel(skill.getName(), null));
            }
        });
    }

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @GetMapping(path="{id}")
    public Profile getProfileById(@PathVariable("id") UUID id) {
        return profileService.getProfile(id).orElse(null);
    }

    @GetMapping(path="/user/{userId}")
    public Profile getProfileByUserId(@PathVariable("userId") UUID userId) {
        return profileService.getProfileByUserId(userId).orElse(null);
    }


    @DeleteMapping(path="{id}")
    public void deleteProfile(@PathVariable("id") UUID id) {
        final UUID userId = profileService.getProfile(id).get().getUserId();
        profileService.deleteProfile(id);
        userService.updateUserProfile(userId, false);
    }

    @PutMapping(path="{id}")
    public void updateProfile(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Profile profile) {
        profileService.updateProfile(id, profile);
    }

}
