package com.domain.models.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.MasterGroup;

public interface MasterGroupRepo extends CrudRepository<MasterGroup, Long> {
    Optional<MasterGroup> findByCode(String code);
}
