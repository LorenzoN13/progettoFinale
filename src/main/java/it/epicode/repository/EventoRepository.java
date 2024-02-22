package it.epicode.repository;

import it.epicode.model.Evento;
import it.epicode.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> , PagingAndSortingRepository<Evento, Integer> {
    List<Evento> findByDateBetween(LocalDateTime dataInizio, LocalDateTime dataFine);
}
