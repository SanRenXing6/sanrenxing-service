package com.sanrenxing.service.dao.image;

import com.sanrenxing.service.model.data.Image;

import java.util.Optional;
import java.util.UUID;

public interface ImageDao {
    int uploadImage(byte[] data);

    Optional<Image> fetchImage(UUID id);

}
