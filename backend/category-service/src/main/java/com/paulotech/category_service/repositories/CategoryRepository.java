package com.paulotech.category_service.repositories;

import com.paulotech.category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findBySalaoId(Long salaoId);
}
