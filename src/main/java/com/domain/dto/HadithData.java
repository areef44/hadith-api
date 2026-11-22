package com.domain.dto;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class HadithData {

    private Long id;

    @NotNull(message = "Source ID Wajib Diisi")
    private Long sourceId;

    @NotNull(message = "Chapter ID Wajib Diisi")
    private Long chapterId;

    @NotNull(message = "Input By Wajib Diisi")
    private Long inputBy;

    @NotNull(message = "Category ID Wajib Diisi")
    private Long categoryId;

    @NotNull(message = "Hadith Status ID Wajib Diisi")
    private Long hadithStatusId;

    @NotNull(message = "Verification Status ID Wajib Diisi")
    private Long verificationStatusId;

    @NotNull(message = "Grade ID Wajib Diisi")
    private Long gradeId;

    private Set<Long> narratorIds;

    @NotNull(message = "No Hadith Wajib Diisi")
    private Long noHadith;

    @NotEmpty(message = "Arabic Text Wajib Diisi")
    private String arabicText;

    @NotEmpty(message = "Translation Wajib Diisi")
    private String translation;

    private String asbabulWurud;

    private String asbabulWurudReference;

    private String publishedAt;

    private String createdAt;

    private String updatedAt;

    private String deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getInputBy() {
        return inputBy;
    }

    public void setInputBy(Long inputBy) {
        this.inputBy = inputBy;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getHadithStatusId() {
        return hadithStatusId;
    }

    public void setHadithStatusId(Long hadithStatusId) {
        this.hadithStatusId = hadithStatusId;
    }

    public Long getVerificationStatusId() {
        return verificationStatusId;
    }

    public void setVerificationStatusId(Long verificationStatusId) {
        this.verificationStatusId = verificationStatusId;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Set<Long> getNarratorIds() {
        return narratorIds;
    }

    public void setNarratorIds(Set<Long> narratorIds) {
        this.narratorIds = narratorIds;
    }

    public Long getNoHadith() {
        return noHadith;
    }

    public void setNoHadith(Long noHadith) {
        this.noHadith = noHadith;
    }

    public String getArabicText() {
        return arabicText;
    }

    public void setArabicText(String arabicText) {
        this.arabicText = arabicText;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getAsbabulWurud() {
        return asbabulWurud;
    }

    public void setAsbabulWurud(String asbabulWurud) {
        this.asbabulWurud = asbabulWurud;
    }

    public String getAsbabulWurudReference() {
        return asbabulWurudReference;
    }

    public void setAsbabulWurudReference(String asbabulWurudReference) {
        this.asbabulWurudReference = asbabulWurudReference;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }
}
