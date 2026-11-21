package com.domain.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.NarratorData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.Narrator;
import com.domain.services.NarratorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/narrators")
public class NarratorController {

    @Autowired
    private NarratorService narratorService;

    @Autowired
    private ModelMapper modelMapper;

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

        Narrator narrator = modelMapper.map(narratorData, Narrator.class);
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

        Narrator narrator = modelMapper.map(narratorData, Narrator.class);
        responseData.setStatus(true);
        responseData.setPayload(narratorService.save(narrator));
        return ResponseEntity.ok(responseData);
    }
}
