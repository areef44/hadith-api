package com.domain.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.domain.models.entities.Hadith;
import com.domain.models.entities.Narrator;

public interface HadithRepo extends CrudRepository<Hadith, Long> {
    // List<Hadith> findByArabicText(String arabicText);

    @Query("SELECT h FROM Hadith h WHERE h.arabicText = :arabicText")
    public Hadith findHadithByArabicText(@Param("arabicText") String arabicText);

    @Query("SELECT h FROM Hadith h WHERE h.arabicText LIKE :arabicText")
    public List<Hadith> findHadithByArabicTextLike(@Param("arabicText") String arabicText);

    @Query("SELECT h FROM Hadith h WHERE h.category.id = :categoryId")
    public List<Hadith> findProductByCategory(@Param("categoryId") Long categoryId);

    @Query("SELECT h FROM Hadith h WHERE :narrator MEMBER OF h.narrators")
    public List<Hadith> findHadithByNarrator(@Param("narrator") Narrator narrator);
}
