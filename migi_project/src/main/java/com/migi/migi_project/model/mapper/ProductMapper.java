package com.migi.migi_project.model.mapper;

import com.migi.migi_project.entity.Product;
import com.migi.migi_project.model.dto.DescriptionDTO;
import com.migi.migi_project.model.dto.ProductDTO;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ProductMapper {
    public static ProductDTO toProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setCreateDate(product.getCreateDate());
        productDTO.setCategoryId(product.getCategoryByIdCategory().getId());

        // mobile side
        productDTO.setShortName(product.getName());
        productDTO.setMobileName(product.getName());
        productDTO.setMobileDescription(product.getDescription());
        productDTO.setDetails(Collections.singletonList(
                DescriptionDTO.builder()
                        .name("Product information")
                        .value(product.getDescription().replaceAll("<[^>]*>", ""))
                        .build()
        ));

        return productDTO;
    }

    public static Product toProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setCreateDate(productDTO.getCreateDate());
        return product;
    }
}
