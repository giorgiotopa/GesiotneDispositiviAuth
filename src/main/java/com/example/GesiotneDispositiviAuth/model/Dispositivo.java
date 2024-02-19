package com.example.GesiotneDispositiviAuth.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String tipo;

    @Column(name = "stato_dispositivo")
    @Enumerated(EnumType.STRING)
    private StatoDispositivo statoDispositivo;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Dipendente dipendente;



    public Dispositivo(String tipo, StatoDispositivo statoDispositivo) {
        this.tipo = tipo;
        this.statoDispositivo = statoDispositivo;
    }

    public Dispositivo() {
    }
}
