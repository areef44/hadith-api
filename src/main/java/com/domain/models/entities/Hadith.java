package com.domain.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tbl_hadith")
public class Hadith implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_id", nullable = false)
    private Long sourceId;

    @Column(name = "chapter_id", nullable = true)
    private Long chapterId;

    @Column(name = "category_id", nullable = true)
    private Long categoryId;

    @Column(name = "status_id", nullable = false)
    private Long hadithStatusId;

    @Column(name = "verification_status_id", nullable = false)
    private Long verificationStatusId;

    @Column(name = "grade_id", nullable = false)
    private Long gradeId;

    @Column(name = "input_by", nullable = false)
    private Long inputBy;

    @Column(name = "no_hadith", nullable = false)
    private Long noHadith;

    @Column(name = "arabic_text", columnDefinition = "TEXT", nullable = false)
    private String arabicText;

    @Column(name = "translation", columnDefinition = "TEXT", nullable = false)
    private String translation;

    @Column(name = "asbabul_wurud", columnDefinition = "TEXT", nullable = true)
    private String asbabulWurud;

    @Column(name = "asbabul_wurud_reference", columnDefinition = "TEXT", nullable = true)
    private String asbabulWurudReference;

    @Column(name = "published_at")
    private String publishedAt;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at", nullable = true)
    private String updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private String deletedAt;

    public Hadith() {
    }

    public Hadith(Long id, Long sourceId, Long chapterId, Long categoryId, Long hadithStatusId,
            Long verificationStatusId, Long gradeId, Long inputBy, Long noHadith, String arabicText, String translation,
            String asbabulWurud, String asbabulWurudReference, String publishedAt, String createdAt, String updatedAt,
            String deletedAt) {
        this.id = id;
        this.sourceId = sourceId;
        this.chapterId = chapterId;
        this.categoryId = categoryId;
        this.hadithStatusId = hadithStatusId;
        this.verificationStatusId = verificationStatusId;
        this.gradeId = gradeId;
        this.inputBy = inputBy;
        this.noHadith = noHadith;
        this.arabicText = arabicText;
        this.translation = translation;
        this.asbabulWurud = asbabulWurud;
        this.asbabulWurudReference = asbabulWurudReference;
        this.publishedAt = publishedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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

    public Long getInputBy() {
        return inputBy;
    }

    public void setInputBy(Long inputBy) {
        this.inputBy = inputBy;
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
