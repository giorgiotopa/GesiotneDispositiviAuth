package com.example.GesiotneDispositiviAuth.controller;

import com.cloudinary.Cloudinary;
import com.example.GesiotneDispositiviAuth.exception.BadRequestException;
import com.example.GesiotneDispositiviAuth.model.Dipendente;
import com.example.GesiotneDispositiviAuth.model.DipendenteRequest;
import com.example.GesiotneDispositiviAuth.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
public class DipendenteController {
    @Autowired
    private DipendenteService dipendeteService;
    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/dipendenti")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Dipendente> getAll(Pageable pageable){
        return dipendeteService.getAllDipendenti(pageable);
    }

    @GetMapping("/dipendenti/{id}")
    public Dipendente getDipendenteById(@PathVariable int id){
        return dipendeteService.getDipendenteById(id);
    }

    @PostMapping("/dipendenti")
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendeteService.saveDipendente(dipendenteRequest);
    }

    @PutMapping("/dipendenti/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteRequest dipendenteRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        return dipendeteService.updateDipendente(id, dipendenteRequest);
    }

    @DeleteMapping("/dipendenti/{id}")
    public void deleteDipendente(@PathVariable int id){
            dipendeteService.deleteDipendente(id);
    }

    @PatchMapping("/dipendenti/{id}/upload")
    public Dipendente uploadImgProfilo(@PathVariable int id, @RequestParam("upload") MultipartFile file) throws IOException {
        return dipendeteService.uploadImgProfilo(id,
                (String) cloudinary.uploader().upload(file.getBytes(), new HashMap()).get("url"));

    }


}
