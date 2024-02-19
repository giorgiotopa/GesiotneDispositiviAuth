package com.example.GesiotneDispositiviAuth.repository;


import com.example.GesiotneDispositiviAuth.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Integer>, PagingAndSortingRepository<Dipendente, Integer> {
}
