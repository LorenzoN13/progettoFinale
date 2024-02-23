package it.epicode.service;

import it.epicode.enums.Ruolo;
import it.epicode.exceptions.BookingConflictException;
import it.epicode.exceptions.EventFullException;
import it.epicode.exceptions.NotFoundException;
import it.epicode.exceptions.UnauthorizedException;
import it.epicode.model.Evento;
import it.epicode.model.Prenotazione;
import it.epicode.model.Utente;
import it.epicode.repository.EventoRepository;
import it.epicode.repository.PrenotazioneRepository;
import it.epicode.request.EventoRequest;
import it.epicode.request.PrenotazioneRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private UtenteService utenteService;


    public void creazionePrenotazione(PrenotazioneRequest prenotazioneRequest)throws NotFoundException, EventFullException{
        Utente utente = utenteService.cercaUtentePerId(prenotazioneRequest.getIdUtente());
        if (utente == null) {
            throw new NotFoundException("Utente non trovato con l'ID specificato.");
        }

        Evento evento = eventoService.cercaEventoPerId(prenotazioneRequest.getIdEvento());
        if (evento == null) {
            throw new NotFoundException("Evento non trovato con l'ID specificato.");
        }

        if (evento.getNumeroPostiDisponibili() <= 0) {
            throw new EventFullException("L'evento è pieno");
        }

        Optional<Prenotazione> prenotazioneEsistente = Optional.ofNullable(prenotazioneRepository.findByUtenteAndEvento(utente, evento));
        if (prenotazioneEsistente.isPresent()) {
            throw new BookingConflictException("Hai già prenotato per questo evento.");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);

        evento.setNumeroPostiDisponibili(evento.getNumeroPostiDisponibili() - 1);
        eventoRepository.save(evento);
        prenotazioneRepository.save(prenotazione);
    }

    public Page<Prenotazione> cercaTuttilePrenotazioniUtente(Pageable pageable){
        return prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione cercaPrenotazionetPerId(int id) throws NotFoundException{
        return prenotazioneRepository.findById(id).
                orElseThrow(()->new NotFoundException("Prenotazione con id="+id+" non trovato"));
    }

    public void cancellaPrenotazione(int Id) throws NotFoundException {
        Prenotazione prenotazione = prenotazioneRepository.findById(Id).orElseThrow(() -> new NotFoundException("Prenotazione non trovata."));
        prenotazioneRepository.delete(prenotazione);
    }

}
