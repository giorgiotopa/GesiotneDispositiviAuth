package com.example.GesiotneDispositiviAuth.service;


import com.example.GesiotneDispositiviAuth.exception.NotFoundException;
import com.example.GesiotneDispositiviAuth.model.Role;
import com.example.GesiotneDispositiviAuth.model.Utente;
import com.example.GesiotneDispositiviAuth.model.UtenteRequest;
import com.example.GesiotneDispositiviAuth.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder encoder;

    public Utente save(UtenteRequest utenteRequest){

        Utente utente = new Utente();
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setUsername(utenteRequest.getUsername());
        utente.setPassword(encoder.encode(utenteRequest.getPassword()));
        utente.setRole(Role.USER);

        return utenteRepository.save(utente);
    }

    public Utente getUtenteById(int id){
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException("Utente non trovato"));
    }

    public Utente getUtenteByUsername(String username){
        return utenteRepository.findByUsername(username).orElseThrow(()->new NotFoundException("Username non trovato"));
    }
    public List<Utente> getAllUtenti(){
        return utenteRepository.findAll();
    }

    public Utente updateUtente(String username, UtenteRequest utenteRequest){
        Utente utente =getUtenteByUsername(username);
        utente.setNome(utenteRequest.getNome());
        utente.setCognome(utenteRequest.getCognome());
        utente.setUsername(utenteRequest.getUsername());
        utente.setPassword(utenteRequest.getPassword());

        return utenteRepository.save(utente);
    }

    public Utente updateRoleUtente(String username,String role){
        Utente utente =getUtenteByUsername(username);
        utente.setRole(Role.valueOf(role));
        return utenteRepository.save(utente);
    }

    public void deleteUtente(String username){
        utenteRepository.deleteByUsername(username).orElseThrow(()->new NotFoundException("Utente non trovato"));
    }
}
