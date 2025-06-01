package com.project.auction.service;

import com.project.auction.entity.Category;
import com.project.auction.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository catRepo;

    private final AccountService accountService;

    ///  Must be
    public Category createNewCategory(String name, String description) {
        return catRepo.save(new Category(null, name, description, new java.util.HashSet<>()));
    }

    ///  Must be
    public boolean deleteCategoryId(Long categoryId){
        return catRepo.deleteByCategoryId(categoryId);
    }

    ///  Must be
    public Category findCategoryId(Long categoryId) {
        return ServiceUtils.findOrThrow(catRepo.findById(categoryId), "Category");
    }
}
