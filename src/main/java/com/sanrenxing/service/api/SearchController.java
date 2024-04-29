package com.sanrenxing.service.api;

import com.sanrenxing.service.model.data.Profile;
import com.sanrenxing.service.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/search")
@RestController
public class SearchController {

    private final ProfileService profileService;

    @Autowired
    public SearchController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping(path="{text}")
    public List<Profile> searchBySkill(@PathVariable("text") String text) { return profileService.searchBySkill(text); }
}
