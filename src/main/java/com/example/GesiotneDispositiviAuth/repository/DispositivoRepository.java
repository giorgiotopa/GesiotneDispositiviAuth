package com.example.GesiotneDispositiviAuth.repository;


import com.example.GesiotneDispositiviAuth.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer>, PagingAndSortingRepository<Dispositivo, Integer> {
}
