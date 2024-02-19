package com.example.GesiotneDispositiviAuth.service;


import com.example.GesiotneDispositiviAuth.exception.NotFoundException;
import com.example.GesiotneDispositiviAuth.model.Dipendente;
import com.example.GesiotneDispositiviAuth.model.Dispositivo;
import com.example.GesiotneDispositiviAuth.model.DispositivoRequest;
import com.example.GesiotneDispositiviAuth.repository.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private DipendenteService dipendenteService;

    public Page<Dispositivo> getAllDispositivi(Pageable pageable){
        return  dispositivoRepository.findAll(pageable);
    }

    public Dispositivo getDispositiviById(int id) throws NotFoundException {
        return dispositivoRepository.findById(id).
                orElseThrow(()->new NotFoundException("Dispositivo con id="+id+" non trovato"));
    }

    public Dispositivo saveDispositivo(DispositivoRequest dispositivoRequest) throws NotFoundException{
        Dispositivo dispositivo = new Dispositivo(dispositivoRequest.getTipo(), dispositivoRequest.getStatoDispositivo());
        return dispositivoRepository.save(dispositivo);

    }


    public Dispositivo updateDispositivo(int id, DispositivoRequest dispositivoRequest) throws NotFoundException{
        Dispositivo dispositivo = getDispositiviById(id);

        Dipendente dipendente = dipendenteService.getDipendenteById(dispositivoRequest.getIdDipendente());

        dispositivo.setStatoDispositivo(dispositivoRequest.getStatoDispositivo());
        dispositivo.setTipo(dispositivoRequest.getTipo());
        dispositivo.setDipendente(dipendente);

        return dispositivoRepository.save(dispositivo);
    }

    public void deleteDispositivo(int id) throws NotFoundException{
        Dispositivo dispositivo = getDispositiviById(id);
        dispositivoRepository.delete(dispositivo);
    }

    public Dispositivo addDipendente(int id, int dipendenteId){
        Dispositivo dispositivo = getDispositiviById(id);
        Dipendente dipendente = dipendenteService.getDipendenteById(dipendenteId);
        dispositivo.setDipendente(dipendente);
        return dispositivoRepository.save(dispositivo);
    }
}
