package com.example.GesiotneDispositiviAuth.controller;


import com.example.GesiotneDispositiviAuth.exception.BadRequestException;
import com.example.GesiotneDispositiviAuth.model.Dispositivo;
import com.example.GesiotneDispositiviAuth.model.DispositivoRequest;
import com.example.GesiotneDispositiviAuth.service.DipendenteService;
import com.example.GesiotneDispositiviAuth.service.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService;
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping("/dispositivi")
    public Page<Dispositivo> getAll(Pageable pageable){

        return dispositivoService.getAllDispositivi(pageable);
    }
    @GetMapping("/dispositivi/{id}")
    public Dispositivo getDispositivoById(@PathVariable int id){
        return dispositivoService.getDispositiviById(id);

    }
    @PostMapping("/dispositivi")
    public Dispositivo saveDispositivo(@RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dispositivoService.saveDispositivo(dispositivoRequest);
    }
    @PutMapping("/dispositivi/{id}")
    public Dispositivo updateDispositivo(@PathVariable int id, @RequestBody @Validated DispositivoRequest dispositivoRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return dispositivoService.updateDispositivo(id, dispositivoRequest);
    }

    @DeleteMapping("/dispositivi/{id}")
    public void deleteDispositivo(@PathVariable int id){
        dispositivoService.deleteDispositivo(id);
    }

    @PatchMapping("/dispositivi/{id}/upload")
    public Dispositivo addDipendente(@PathVariable int id, @RequestParam("dipendeteId") int dipendenteId) throws IOException {

        return dispositivoService.addDipendente(id, dipendenteId);

    }
}

