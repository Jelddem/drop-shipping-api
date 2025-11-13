package com.drop.shiping.api.drop_shiping_api.images.services;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import com.drop.shiping.api.drop_shiping_api.images.entities.Image;

public interface ImageService {
    Image uploadImage(MultipartFile multipartFile) throws IOException;

    void deleteImage(Image image) throws IOException;
}
