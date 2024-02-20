package com.example.GesiotneDispositiviAuth.controller;

import com.example.GesiotneDispositiviAuth.exception.BadRequestException;
import com.example.GesiotneDispositiviAuth.model.Utente;
import com.example.GesiotneDispositiviAuth.model.UtenteRequest;
import com.example.GesiotneDispositiviAuth.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtenteController {
    @Autowired
    private UtenteService utenteService;
    @GetMapping("/utenti")
    public List<Utente> getAll(){
        return utenteService.getAllUtenti();
    }

    @GetMapping("/utenti/{username}")
    public Utente getUtenteByUsername(@PathVariable String username){
        return utenteService.getUtenteByUsername(username);
    }

    @PutMapping("/utenti/{username}")
    public Utente updateUtente(@PathVariable String username, @RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.updateUtente(username, utenteRequest);

    }
    @PostMapping("/utenti")
    public Utente save(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);
    }
    @DeleteMapping("/utenti/{username}")
    public void deleteUtente(@PathVariable String username){

        utenteService.deleteUtente(username);
    }
    @PatchMapping("/utenti/{username}")
    public Utente changeRole(@PathVariable String username, @RequestBody String role){
        return utenteService.updateRoleUtente(username, role);

    }
}
