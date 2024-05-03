package com.sanrenxing.service.service;

import com.sanrenxing.service.dao.image.ImageDao;
import com.sanrenxing.service.model.data.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageDao imageDao;

    @Autowired
    public ImageService(@Qualifier("imagePostgreSQL") ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public UUID addImage(byte[] image){
        return imageDao.uploadImage(image);
    }

    public Optional<Image> getImage(UUID id) {
        return imageDao.fetchImage(id);
    }

    public int deleteImage(UUID id) {
        return imageDao.deleteImage(id);
    }
}
