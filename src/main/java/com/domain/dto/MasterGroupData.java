package com.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public class MasterGroupData {

    private Long id;

    @NotEmpty(message = "Kode group wajib diisi")
    private String code;

    @NotEmpty(message = "Nama group wajib diisi")
    private String name;

    private String description;

    private Boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
