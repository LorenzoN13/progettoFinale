package it.epicode.request;

import it.epicode.model.Evento;
import it.epicode.model.Utente;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrenotazioneRequest {

    @NotNull(message = "Non può essere null")
    private Utente utente;

    @NotNull(message = "Non può essere null")
    private Evento evento;

    private Integer idUtente;
    private Integer idEvento;
}
