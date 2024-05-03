
package com.sanrenxing.service.api;

import com.sanrenxing.service.model.data.Image;
import com.sanrenxing.service.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("api/v1/images")
@AllArgsConstructor
@RestController
public class ImageController {

    private final ImageService imageService;

    @PostMapping()
    public UUID uploadImage (@Valid @RequestParam("file") MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        return imageService.addImage(data);
    }

    @GetMapping(path="{id}")
    public ResponseEntity<StreamingResponseBody> retrieveImage(@PathVariable("id") UUID id) {
        final Image image = imageService.getImage(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found"));

        StreamingResponseBody responseBody = outputStream -> {
            try {
                outputStream.write(image.getData());
                outputStream.flush();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error writing image data", e);
            }
        };

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(responseBody);
    }

}