package it.epicode.service;

import it.epicode.exceptions.NotFoundException;
import it.epicode.model.Utente;
import it.epicode.repository.UtenteRepository;
import it.epicode.request.UtenteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(UtenteRequest utenteRequest){
        Utente utente = new Utente();
        utente.setRuolo(utenteRequest.getRuolo());
        utente.setUsername(utenteRequest.getUsername());
        utente.setPassword(utenteRequest.getPassword());
        return utenteRepository.save(utente);
    }
    public Utente getByUsername(String username) throws NotFoundException {
        return utenteRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username non trovato"));
    }
    public Utente cercaUtentePerId(int id){
        return utenteRepository.findById(id).orElseThrow(()->new NotFoundException("Autore con id="+ id + " non trovato"));
    }
}
