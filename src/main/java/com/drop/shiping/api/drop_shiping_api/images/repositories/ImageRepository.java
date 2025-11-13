package com.drop.shiping.api.drop_shiping_api.images.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.drop.shiping.api.drop_shiping_api.images.entities.Image;

public interface ImageRepository extends JpaRepository<Image, String>{
}
