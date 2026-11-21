package com.domain.controllers;

import java.util.Map;

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

import com.domain.dto.MasterDataRequest;
import com.domain.dto.ResponseData;
import com.domain.models.entities.MasterData;
import com.domain.models.entities.MasterGroup;
import com.domain.services.MasterDataService;
import com.domain.services.MasterGroupService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/master-data")
public class MasterDataController {

    @Autowired
    private MasterDataService masterDataService;

    @Autowired
    private MasterGroupService masterGroupService;

    @PostMapping
    public ResponseEntity<ResponseData<MasterData>> create(@Valid @RequestBody MasterDataRequest request, Errors errors) {
        ResponseData<MasterData> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterGroup group = masterGroupService.findOne(request.getGroupId());
        if (group == null) {
            responseData.getMessages().add("Master Group dengan ID " + request.getGroupId() + " tidak ditemukan");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterData masterData = new MasterData();
        masterData.setGroup(group);
        masterData.setCode(request.getCode());
        masterData.setLabel(request.getLabel());
        masterData.setDescription(request.getDescription());
        masterData.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        masterData.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);

        responseData.setStatus(true);
        responseData.setPayload(masterDataService.save(masterData));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<MasterData> findAll() {
        return masterDataService.findAll();
    }

    @GetMapping("/{id}")
    public MasterData findOne(@PathVariable("id") Long id) {
        return masterDataService.findOne(id);
    }

    /**
     * Memanggil semua data dinamis berdasarkan groupId (baik dari Category, Narrator, maupun MasterData).
     */
    @GetMapping("/group/{groupId}")
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
    public ResponseEntity<ResponseData<MasterData>> update(@Valid @RequestBody MasterDataRequest request, Errors errors) {
        ResponseData<MasterData> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        if (request.getId() == null) {
            responseData.getMessages().add("Id Wajib Diisi");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterGroup group = masterGroupService.findOne(request.getGroupId());
        if (group == null) {
            responseData.getMessages().add("Master Group dengan ID " + request.getGroupId() + " tidak ditemukan");
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        MasterData masterData = new MasterData();
        masterData.setId(request.getId());
        masterData.setGroup(group);
        masterData.setCode(request.getCode());
        masterData.setLabel(request.getLabel());
        masterData.setDescription(request.getDescription());
        masterData.setSortOrder(request.getSortOrder() != null ? request.getSortOrder() : 0);
        masterData.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);

        responseData.setStatus(true);
        responseData.setPayload(masterDataService.save(masterData));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        masterDataService.removeOne(id);
    }
}
