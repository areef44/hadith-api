package com.domain.models.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.MasterData;

public interface MasterDataRepo extends CrudRepository<MasterData, Long> {
    List<MasterData> findByGroupId(Long groupId);
}
