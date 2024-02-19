package com.example.GesiotneDispositiviAuth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DipendenteRequest {

    @NotNull(message = "Username obbligatorio")
    @NotEmpty(message = "Username obbligatorio")
    private String username;

    @NotNull(message = "Nome obbligatorio")
    @NotEmpty(message = "Nome obbligatorio")
    private String nome;

    @NotNull(message = "Cognome obbligatorio")
    @NotEmpty(message = "Cognome obbligatorio")
    private String cognome;

    @Email(message = "Email obbligatoria")
    private String email;
}
