package com.migi.migi_project.model.response;

import com.migi.migi_project.entity.Category;
import com.migi.migi_project.model.dto.CategoryDTO;
import com.migi.migi_project.model.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductPage {
    private int page;
    private int totalPage;
    private int totalProduct;
    private List<ProductDTO> productDTOList;
    private List<CategoryDTO> categoryDTOList;
}
