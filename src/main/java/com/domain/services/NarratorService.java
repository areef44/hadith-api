package com.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.entities.Narrator;
import com.domain.models.repos.NarratorRepo;

import jakarta.transaction.Transactional;

@Service 
@Transactional 
public class NarratorService {
    @Autowired 
    private NarratorRepo narratorRepo;

    public Narrator save(Narrator narrator){
        return narratorRepo.save(narrator);
    }

    public Narrator findOne(Long id){
        Optional<Narrator> narrator = narratorRepo.findById(id);
        if(!narrator.isPresent()){
            return null;
        }
        return narrator.get();
    }

    public Iterable<Narrator> findAll(){
        return narratorRepo.findAll();
    }

    public void removeOne(Long id){
        narratorRepo.deleteById(id);
    }

    public Narrator findByName(String name){
        return narratorRepo.findByName(name);
    }

    public List<Narrator> findByFullName(String fullName){
        return narratorRepo.findByFullNameContainsOrderByIdDesc(fullName);
    }

    public List<Narrator> findByFullNameStartsWith(String prefix){
        return narratorRepo.findByFullNameStartsWith(prefix);
    } 

    public List<Narrator> findByNameOrFullName(String name, String fullName){
        return narratorRepo.findByNameContainsOrFullNameContains(name, fullName);
    }
}
