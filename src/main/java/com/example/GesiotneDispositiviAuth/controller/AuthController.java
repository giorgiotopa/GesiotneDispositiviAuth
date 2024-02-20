package com.example.GesiotneDispositiviAuth.controller;


import com.example.GesiotneDispositiviAuth.exception.BadRequestException;
import com.example.GesiotneDispositiviAuth.exception.LoginFaultException;
import com.example.GesiotneDispositiviAuth.model.LoginRequest;
import com.example.GesiotneDispositiviAuth.model.Utente;
import com.example.GesiotneDispositiviAuth.model.UtenteRequest;
import com.example.GesiotneDispositiviAuth.security.JwtTools;
import com.example.GesiotneDispositiviAuth.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getUtenteByUsername(loginRequest.getUsername());

        if(encoder.matches(loginRequest.getPassword(), utente.getPassword())){
            return jwtTools.createToken(utente);
        }
        else{
            throw new LoginFaultException("username/password errate");
        }

    }
}
