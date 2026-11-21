package com.domain.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_hadith")
public class Hadith implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "Source ID Wajib Diisi")
    @Column(name = "source_id", nullable = false)
    private Long sourceId;

    @NotNull(message = "Chapter ID Wajib Diisi")
    @Column(name = "chapter_id", nullable = false)
    private Long chapterId;

    @NotNull(message = "Input By Wajib Diisi")
    @Column(name = "input_by", nullable = false)
    private Long inputBy;

    @NotNull(message = "Category Wajib Diisi")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @NotNull(message = "Hadith Status ID Wajib Diisi")
    @Column(name = "status_id", nullable = false)
    private Long hadithStatusId;

    @NotNull(message = "Verification Status ID Wajib Diisi")
    @Column(name = "verification_status_id", nullable = false)
    private Long verificationStatusId;

    @NotNull(message = "Grade ID Wajib Diisi")
    @Column(name = "grade_id", nullable = false)
    private Long gradeId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "tbl_hadith_narrators",
        joinColumns = @JoinColumn(name = "hadith_id"),
        inverseJoinColumns = @JoinColumn(name = "narrator_id")
    )
    private Set<Narrator> narrators = new HashSet<>();


    @NotNull(message = "No Hadith Wajib Diisi")
    @Column(name = "no_hadith", nullable = false)
    private Long noHadith;

    @NotEmpty(message = "Arabic Text Wajib Diisi")
    @Column(name = "arabic_text", columnDefinition = "TEXT", nullable = false)
    private String arabicText;

    @NotEmpty(message = "Translation Wajib Diisi")
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

     // ===== helper relasi perawi =====
    public void addNarrator(Narrator narrator) {
        this.narrators.add(narrator);
        narrator.getHadiths().add(this);
    }

    public void removeNarrator(Narrator narrator) {
        this.narrators.remove(narrator);
        narrator.getHadiths().remove(this);
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

    public Category getCategory() {
        return category;
    }

    public Set<Narrator> getNarrators() {
        return narrators;
    }

    public void setNarrators(Set<Narrator> narrators) {
        this.narrators = narrators;
    }

    public void setCategory(Category category) {
        this.category = category;
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

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hadith other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
