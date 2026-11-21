package com.domain.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Category;
import com.domain.models.entities.MasterData;
import com.domain.models.entities.MasterGroup;
import com.domain.models.entities.Narrator;
import com.domain.models.repos.CategoryRepo;
import com.domain.models.repos.MasterDataRepo;
import com.domain.models.repos.MasterGroupRepo;
import com.domain.models.repos.NarratorRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MasterGroupService {

    @Autowired
    private MasterGroupRepo masterGroupRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private NarratorRepo narratorRepo;

    @Autowired
    private MasterDataRepo masterDataRepo;

    public MasterGroup save(MasterGroup masterGroup) {
        return masterGroupRepo.save(masterGroup);
    }

    public MasterGroup findOne(Long id) {
        Optional<MasterGroup> masterGroup = masterGroupRepo.findById(id);
        if (!masterGroup.isPresent()) {
            return null;
        }
        return masterGroup.get();
    }

    public Iterable<MasterGroup> findAll() {
        return masterGroupRepo.findAll();
    }

    public void removeOne(Long id) {
        masterGroupRepo.deleteById(id);
    }

    /**
     * Mengambil semua data dari tabel yang memiliki group_id sesuai parameter.
     * Karena tiap parameter bisa disimpan di tbl_hadith_categories, tbl_narrators,
     * atau tbl_master_data (dengan struktur field yang berbeda-beda),
     * method ini mengembalikan data secara dinamis sesuai group_id nya.
     */
    public Map<String, Object> findDynamicDataByGroupId(Long groupId) {
        MasterGroup group = findOne(groupId);
        if (group == null) {
            return null;
        }

        List<Category> categories = categoryRepo.findByGroupId(groupId);
        List<Narrator> narrators = narratorRepo.findByGroupId(groupId);
        List<MasterData> masterDataList = masterDataRepo.findByGroupId(groupId);

        List<Object> items = new ArrayList<>();
        items.addAll(categories);
        items.addAll(narrators);
        items.addAll(masterDataList);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("group", group);
        result.put("totalItems", items.size());
        result.put("items", items);
        return result;
    }
}
