package com.project.auction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.auction.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean deleteByCategoryId(Long categoryId);
}
