package it.epicode.repository;

import it.epicode.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> , PagingAndSortingRepository<Evento, Integer> {
    List<Evento> findByDataBetween(LocalDateTime dataInizio, LocalDateTime dataFine);
}
