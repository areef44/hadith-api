package com.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.models.entities.Hadith;
import com.domain.services.HadithService;

@RestController
@RequestMapping("/api/v1/hadith")
public class HadithController {

    @Autowired
    private HadithService hadithService;

    @PostMapping
    public Hadith create(@RequestBody Hadith hadith) {
        return hadithService.save(hadith);
    }

    @GetMapping
    public Iterable<Hadith> findAll() {
        return hadithService.findAll();
    }

    @GetMapping("/{id}")
    public Hadith findOne(@PathVariable("id") Long id) {
        return hadithService.findOne(id);
    }

    @PutMapping
    public Hadith update(@RequestBody Hadith hadith) {
        return hadithService.save(hadith);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        hadithService.removeOne(id);
    }
}
