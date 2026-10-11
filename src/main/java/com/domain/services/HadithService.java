package com.domain.services;

import jakarta.transaction.Transactional;

import com.domain.models.entities.Hadith;
import com.domain.models.repos.HadithRepo;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HadithService {

    @Autowired
    private HadithRepo hadithRepo;

    public Hadith save(Hadith hadith) {
        return hadithRepo.save(hadith);
    }

    public Hadith findOne(Long id) {
        Optional<Hadith> hadith = hadithRepo.findById(id);
        if(!hadith.isPresent()){
            return null;
        }
        return hadith.get();
    }

    public Iterable<Hadith> findAll() {
        return hadithRepo.findAll();
    }

    public void removeOne(Long id) {
        hadithRepo.deleteById(id);
    }

    public List<Hadith> findByArabicText(String arabicText) {
        return hadithRepo.findByArabicText(arabicText);
    }
}
