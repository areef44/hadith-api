package com.domain.models.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_narrators")   // BUKAN tbl_hadith_narrators
public class Narrator implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nama Wajib Diisi")
    @Column(length = 150, nullable = false)
    private String name;

    @Column(name = "arabic_name", length = 150)
    private String arabicName;

    @Column(name = "full_name", length = 255)
    private String fullName;

     @Column(length = 100)
    private String kunyah;

     @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    @Column(name = "birth_place", length = 100)
    private String birthPlace;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @NotNull(message = "Group Wajib Diisi")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private MasterGroup group;

    // sisi inverse (tidak membuat kolom/tabel baru)
    @ManyToMany(mappedBy = "narrators", fetch = FetchType.LAZY)
    private Set<Hadith> hadiths = new HashSet<>();

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getKunyah() {
        return kunyah;
    }

    public void setKunyah(String kunyah) {
        this.kunyah = kunyah;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public MasterGroup getGroup() {
        return group;
    }

    public void setGroup(MasterGroup group) {
        this.group = group;
    }

    public Set<Hadith> getHadiths() {
        return hadiths;
    }

    public void setHadiths(Set<Hadith> hadiths) {
        this.hadiths = hadiths;
    }

    
}