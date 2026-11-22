package com.domain.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.NarratorData;
import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.MasterGroup;
import com.domain.models.entities.Narrator;
import com.domain.services.MasterGroupService;
import com.domain.services.NarratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/narrators")
public class NarratorController {

    @Autowired
    private NarratorService narratorService;

    @Autowired
    private MasterGroupService masterGroupService;

    @PostMapping
    public ResponseEntity<ResponseData<Narrator>> create(@Valid @RequestBody NarratorData narratorData, Errors errors) {
        ResponseData<Narrator> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterGroup group = masterGroupService.findOne(narratorData.getGroupId());
        if (group == null) {
            responseData.getMessages().add("Master Group dengan ID " + narratorData.getGroupId() + " tidak ditemukan");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Narrator narrator = mapToEntity(narratorData, group);
        responseData.setStatus(true);
        responseData.setPayload(narratorService.save(narrator));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Narrator> findAll() {
        return narratorService.findAll();
    }

    @GetMapping("/{id}")
    public Narrator findOne(@PathVariable("id") Long id) {
        return narratorService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Narrator>> update(@Valid @RequestBody NarratorData narratorData, Errors errors) {
        ResponseData<Narrator> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        if (narratorData.getId() == null) {
            responseData.getMessages().add("Id Wajib Diisi");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterGroup group = masterGroupService.findOne(narratorData.getGroupId());
        if (group == null) {
            responseData.getMessages().add("Master Group dengan ID " + narratorData.getGroupId() + " tidak ditemukan");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Narrator narrator = mapToEntity(narratorData, group);
        narrator.setId(narratorData.getId());
        responseData.setStatus(true);
        responseData.setPayload(narratorService.save(narrator));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        narratorService.removeOne(id);
    }

    private Narrator mapToEntity(NarratorData dto, MasterGroup group) {
        Narrator narrator = new Narrator();
        narrator.setName(dto.getName());
        narrator.setArabicName(dto.getArabicName());
        narrator.setFullName(dto.getFullName());
        narrator.setKunyah(dto.getKunyah());
        narrator.setBirthYear(dto.getBirthYear());
        narrator.setDeathYear(dto.getDeathYear());
        narrator.setBirthPlace(dto.getBirthPlace());
        narrator.setBiography(dto.getBiography());
        narrator.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        narrator.setGroup(group);
        return narrator;
    }

    @PostMapping("/search/by-name")
    public Narrator findByName(@RequestBody SearchData searchData) {
        return narratorService.findByName(searchData.getSearchKey());
    }

    @PostMapping("/search/fullname")
    public List<Narrator> findByFullName(@RequestBody SearchData searchData) {
        return narratorService.findByFullName(searchData.getSearchKey());
    }

    @PostMapping("/search/fullname-start-with")
    public List<Narrator> findByFullNameStartWith(@RequestBody SearchData searchData) {
        return narratorService.findByFullNameStartsWith(searchData.getSearchKey());
    }

    @PostMapping("/search/nameorfullname")
    public List<Narrator> findByNameOrFullName(@RequestBody SearchData searchData) {
        return narratorService.findByNameOrFullName(searchData.getSearchKey(), searchData.getOtherSearchKey());
    }
}
