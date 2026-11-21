package com.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.MasterData;
import com.domain.models.repos.MasterDataRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MasterDataService {

    @Autowired
    private MasterDataRepo masterDataRepo;

    public MasterData save(MasterData masterData) {
        return masterDataRepo.save(masterData);
    }

    public MasterData findOne(Long id) {
        Optional<MasterData> masterData = masterDataRepo.findById(id);
        if (!masterData.isPresent()) {
            return null;
        }
        return masterData.get();
    }

    public Iterable<MasterData> findAll() {
        return masterDataRepo.findAll();
    }

    public List<MasterData> findByGroupId(Long groupId) {
        return masterDataRepo.findByGroupId(groupId);
    }

    public void removeOne(Long id) {
        masterDataRepo.deleteById(id);
    }
}
