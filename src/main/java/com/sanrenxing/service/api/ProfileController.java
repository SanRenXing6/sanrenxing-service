package com.sanrenxing.service.api;

import com.sanrenxing.service.model.data.Image;
import com.sanrenxing.service.model.data.Profile;
import com.sanrenxing.service.service.ImageService;
import com.sanrenxing.service.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/profiles")
@RestController
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final ImageService imageService;


    @PostMapping
    public void addProfie(@Valid @RequestBody @NonNull Profile profile) {
        profileService.addProfile(profile);
    }

    @PostMapping("/image")
    public UUID uploadProfileImage (@Valid @RequestParam("file") MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        return imageService.addImage(data);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<StreamingResponseBody> retrieveImage(@PathVariable("id") UUID id) {
        final Image image = imageService.getImage(id).orElseThrow();
        final byte[] imageData = image.getData();
        StreamingResponseBody responseBody = outputStream -> {
            outputStream.write(imageData);
            outputStream.flush();
        };
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(responseBody);
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
