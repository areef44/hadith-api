package com.domain.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.domain.dto.HadithData;
import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.Category;
import com.domain.models.entities.Hadith;
import com.domain.models.entities.Narrator;
import com.domain.services.CategoryService;
import com.domain.services.HadithService;
import com.domain.services.NarratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/hadith")
public class HadithController {

    @Autowired
    private HadithService hadithService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NarratorService narratorService;

    @PostMapping
    public ResponseEntity<ResponseData<Hadith>> create(@Valid @RequestBody HadithData hadithData, Errors errors) {

        ResponseData<Hadith> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = categoryService.findOne(hadithData.getCategoryId());
        if (category == null) {
            responseData.getMessages().add("Category dengan ID " + hadithData.getCategoryId() + " tidak ditemukan");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Set<Narrator> narrators = new HashSet<>();
        if (hadithData.getNarratorIds() != null) {
            for (Long narratorId : hadithData.getNarratorIds()) {
                Narrator narrator = narratorService.findOne(narratorId);
                if (narrator == null) {
                    responseData.getMessages().add("Narrator dengan ID " + narratorId + " tidak ditemukan");
                    responseData.setStatus(false);
                    responseData.setPayload(null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
                narrators.add(narrator);
            }
        }

        Hadith hadith = mapToEntity(hadithData, category, narrators);
        responseData.setStatus(true);
        responseData.setPayload(hadithService.save(hadith));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Hadith> findAll() {
        return hadithService.findAll();
    }

    @GetMapping("/{id}")
    public Hadith findOne(@PathVariable("id") Long id) {
        return hadithService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Hadith>> update(@Valid @RequestBody HadithData hadithData, Errors errors) {

        ResponseData<Hadith> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        if (hadithData.getId() == null) {
            responseData.getMessages().add("Id Wajib Diisi");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = categoryService.findOne(hadithData.getCategoryId());
        if (category == null) {
            responseData.getMessages().add("Category dengan ID " + hadithData.getCategoryId() + " tidak ditemukan");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Set<Narrator> narrators = new HashSet<>();
        if (hadithData.getNarratorIds() != null) {
            for (Long narratorId : hadithData.getNarratorIds()) {
                Narrator narrator = narratorService.findOne(narratorId);
                if (narrator == null) {
                    responseData.getMessages().add("Narrator dengan ID " + narratorId + " tidak ditemukan");
                    responseData.setStatus(false);
                    responseData.setPayload(null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
                }
                narrators.add(narrator);
            }
        }

        Hadith hadith = mapToEntity(hadithData, category, narrators);
        hadith.setId(hadithData.getId());
        responseData.setStatus(true);
        responseData.setPayload(hadithService.save(hadith));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        hadithService.removeOne(id);
    }

    private Hadith mapToEntity(HadithData dto, Category category, Set<Narrator> narrators) {
        Hadith hadith = new Hadith();
        hadith.setSourceId(dto.getSourceId());
        hadith.setChapterId(dto.getChapterId());
        hadith.setInputBy(dto.getInputBy());
        hadith.setCategory(category);
        hadith.setHadithStatusId(dto.getHadithStatusId());
        hadith.setVerificationStatusId(dto.getVerificationStatusId());
        hadith.setGradeId(dto.getGradeId());
        hadith.setNarrators(narrators);
        hadith.setNoHadith(dto.getNoHadith());
        hadith.setArabicText(dto.getArabicText());
        hadith.setTranslation(dto.getTranslation());
        hadith.setAsbabulWurud(dto.getAsbabulWurud());
        hadith.setAsbabulWurudReference(dto.getAsbabulWurudReference());
        hadith.setPublishedAt(dto.getPublishedAt());
        hadith.setCreatedAt(dto.getCreatedAt());
        hadith.setUpdatedAt(dto.getUpdatedAt());
        hadith.setDeletedAt(dto.getDeletedAt());
        return hadith;
    }

    @PostMapping("/search/arabic-text") 
    public Hadith getHadithByName(@RequestBody SearchData searchData){
        return hadithService.findByArabicText(searchData.getSearchKey());
    }

    @PostMapping("/search/arabic-text-like") 
        public List<Hadith> getHadithByNameLike(@RequestBody SearchData searchData){
        return hadithService.findByArabicTextLike(searchData.getSearchKey());
    }

    @GetMapping("/search/category/{categoryId}")
    public List<Hadith> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return hadithService.findByCategory(categoryId);
    }

    @GetMapping("/search/narrator/{narratorId}")
    public List<Hadith> getHadithByNarrator(@PathVariable("narratorId") Long narratorId){
        return hadithService.findByNarrator(narratorId);
    }
}
