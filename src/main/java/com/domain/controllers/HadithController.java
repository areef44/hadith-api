package com.domain.controllers;

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

import com.domain.dto.ResponseData;
import com.domain.models.entities.Hadith;
import com.domain.services.HadithService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/hadith")
public class HadithController {

    @Autowired
    private HadithService hadithService;

    @PostMapping
    public ResponseEntity<ResponseData<Hadith>> create(@Valid @RequestBody Hadith hadith, Errors errors) {

        ResponseData<Hadith> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
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
    public ResponseEntity<ResponseData<Hadith>> update(@Valid @RequestBody Hadith hadith, Errors errors) {

        ResponseData<Hadith> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(hadithService.save(hadith));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        hadithService.removeOne(id);
    }
}
