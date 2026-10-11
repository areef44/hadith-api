package com.domain.models.repos;

import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Hadith;
import java.util.List;


public interface HadithRepo extends CrudRepository<Hadith, Long> {
    List<Hadith> findByArabicText(String arabicText);
}
