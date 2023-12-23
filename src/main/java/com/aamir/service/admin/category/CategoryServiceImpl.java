package com.aamir.service.admin.category;

import com.aamir.dtos.category.CategoryDTO;
import com.aamir.entity.Category;
import com.aamir.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .name(categoryDTO.name())
                .description(categoryDTO.name())
                .build();

        Category savedCategory = categoryRepository.save(category);

        return CategoryDTO.builder()
                .id(savedCategory.getId())
                .name(savedCategory.getName())
                .description(savedCategory.getDescription())
                .build();
    }
}
