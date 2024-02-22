package it.epicode.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Evento evento;
}
