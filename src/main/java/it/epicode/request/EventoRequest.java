package it.epicode.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventoRequest {

    @NotBlank(message = "titolo obbligatorio")
    private String titolo;

    @NotBlank(message = "descrizione obbligatoria")
    private String descrizione;

    @NotBlank(message = "data obbligatoria")
    private LocalDateTime data;

    @NotBlank(message = "luogo obbligatorio")
    private String luogo;

    @NotBlank(message = "numeroPostiDisponibili obbligatori")
    private int numeroPostiDisponibili;

    private Integer idUtente;

    private Integer idEvento;
}
