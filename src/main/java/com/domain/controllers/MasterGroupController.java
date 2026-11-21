package com.domain.controllers;

import java.util.Map;

import org.modelmapper.ModelMapper;
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

import com.domain.dto.MasterGroupData;
import com.domain.dto.ResponseData;
import com.domain.models.entities.MasterGroup;
import com.domain.services.MasterGroupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/master-groups")
public class MasterGroupController {

    @Autowired
    private MasterGroupService masterGroupService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<MasterGroup>> create(@Valid @RequestBody MasterGroupData masterGroupData, Errors errors) {
        ResponseData<MasterGroup> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterGroup masterGroup = modelMapper.map(masterGroupData, MasterGroup.class);
        if (masterGroup.getIsActive() == null) {
            masterGroup.setIsActive(true);
        }
        responseData.setStatus(true);
        responseData.setPayload(masterGroupService.save(masterGroup));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<MasterGroup> findAll() {
        return masterGroupService.findAll();
    }

    /**
     * Endpoint dinamis: sekali panggil berdasarkan groupId, otomatis mengembalikan
     * informasi MasterGroup beserta semua data terkait dari Category, Narrator, atau MasterData
     * sesuai field masing-masing.
     */
    @GetMapping("/{groupId}")
    public ResponseEntity<ResponseData<Map<String, Object>>> findByGroupId(@PathVariable("groupId") Long groupId) {
        ResponseData<Map<String, Object>> responseData = new ResponseData<>();
        Map<String, Object> result = masterGroupService.findDynamicDataByGroupId(groupId);
        if (result == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Master Group dengan ID " + groupId + " tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(result);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping
    public ResponseEntity<ResponseData<MasterGroup>> update(@Valid @RequestBody MasterGroupData masterGroupData, Errors errors) {
        ResponseData<MasterGroup> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        if (masterGroupData.getId() == null) {
            responseData.getMessages().add("Id Wajib Diisi");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterGroup masterGroup = modelMapper.map(masterGroupData, MasterGroup.class);
        if (masterGroup.getIsActive() == null) {
            masterGroup.setIsActive(true);
        }
        responseData.setStatus(true);
        responseData.setPayload(masterGroupService.save(masterGroup));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        masterGroupService.removeOne(id);
    }
}
