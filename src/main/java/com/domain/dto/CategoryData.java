package com.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryData {

    private Long id;

    @NotEmpty(message = "Nama kategori wajib diisi")
    private String name;

    @NotEmpty(message = "Deskripsi kategori wajib diisi")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

   
    
}
