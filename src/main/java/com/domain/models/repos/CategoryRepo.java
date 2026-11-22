package com.domain.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {
    List<Category> findByGroupId(Long groupId);
}
