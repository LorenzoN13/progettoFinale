package it.epicode.request;

import it.epicode.enums.Ruolo;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtenteRequest {
    @NotBlank(message = "username obbligatorio")
    private String username;

    @NotBlank(message = "password obbligatoria")
    private String password;

    @NotBlank(message = "ruolo obbligatorio")
    private Ruolo ruolo;
}
