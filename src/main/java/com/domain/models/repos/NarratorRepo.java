package com.domain.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Narrator;

public interface NarratorRepo extends CrudRepository<Narrator, Long> {
    List<Narrator> findByGroupId(Long groupId);
}
