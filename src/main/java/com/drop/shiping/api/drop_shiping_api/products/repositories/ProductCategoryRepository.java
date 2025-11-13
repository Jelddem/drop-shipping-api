package com.drop.shiping.api.drop_shiping_api.products.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.drop.shiping.api.drop_shiping_api.products.entities.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, String> {
    List<ProductCategory> findByCategoryNameIn(List<String> categories);
}
