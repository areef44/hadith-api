package com.domain.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Narrator;
import java.util.List;


public interface NarratorRepo extends CrudRepository<Narrator, Long> {
    Narrator findByName(String name);

    List<Narrator> findByFullNameContainsOrderByIdDesc(String fullName);

    List<Narrator> findByFullNameStartsWith(String fullName);

    List<Narrator> findByNameContainsOrFullNameContains(String name, String fullName);

    List<Narrator> findByGroupId(Long groupId);
}
