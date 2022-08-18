package com.migi.migi_project.service.user;

import com.migi.migi_project.entity.Category;
import com.migi.migi_project.model.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<CategoryDTO> findAll();
}
