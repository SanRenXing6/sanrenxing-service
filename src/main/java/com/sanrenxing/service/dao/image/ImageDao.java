package com.sanrenxing.service.dao.image;

import com.sanrenxing.service.model.data.Image;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface ImageDao {
    UUID uploadImage(byte[] data);

    Optional<Image> fetchImage(UUID id);

    int deleteImage(UUID id);

}
