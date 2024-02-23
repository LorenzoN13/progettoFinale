package it.epicode.repository;

import it.epicode.model.Evento;
import it.epicode.model.Prenotazione;
import it.epicode.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer>, PagingAndSortingRepository<Prenotazione, Integer> {
    Prenotazione findByUtenteAndEvento(Utente utente, Evento evento);

}
