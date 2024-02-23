package it.epicode.service;

import it.epicode.enums.Ruolo;
import it.epicode.exceptions.NotFoundException;
import it.epicode.exceptions.UnauthorizedException;
import it.epicode.model.Evento;
import it.epicode.model.Utente;
import it.epicode.repository.EventoRepository;
import it.epicode.request.EventoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UtenteService utenteService;

    public Evento salvaEvento(EventoRequest eventoRequest) throws UnauthorizedException{
        Utente utente = utenteService.cercaUtentePerId(eventoRequest.getIdUtente());

        if (!utente.getRuolo().equals(Ruolo.ORGANIZZATORE)){
            throw new UnauthorizedException("Solo gli organizzatori possono creare eventi.");
        }

        Evento evento = new Evento();
        evento.setTitolo(eventoRequest.getTitolo());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setLuogo(eventoRequest.getLuogo());
        evento.setData(eventoRequest.getData());
        evento.setNumeroPostiDisponibili(eventoRequest.getNumeroPostiDisponibili());

        return eventoRepository.save(evento);
    }

    public Evento cercaEventoPerId(int id) throws NotFoundException{

        return eventoRepository.findById(id).orElseThrow(()->new NotFoundException("Evento con id="+id+" non trovato"));
    }

    public Evento aggiornaEvento(int id, EventoRequest eventoRequest) throws UnauthorizedException {
        Evento evento = cercaEventoPerId(id);

        Utente utente = utenteService.cercaUtentePerId(eventoRequest.getIdUtente());

        if (!utente.getRuolo().equals(Ruolo.ORGANIZZATORE)){
            throw new UnauthorizedException("Solo gli organizzatori possono modificare gli eventi.");
        }

        evento.setTitolo(eventoRequest.getTitolo());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setLuogo(eventoRequest.getLuogo());
        evento.setData(eventoRequest.getData());
        evento.setNumeroPostiDisponibili(eventoRequest.getNumeroPostiDisponibili());

        return eventoRepository.save(evento);

    }

    public void rimuoviEvento(int id) throws UnauthorizedException, NotFoundException {
        Evento evento = cercaEventoPerId(id);

        eventoRepository.delete(evento);
    }

}

