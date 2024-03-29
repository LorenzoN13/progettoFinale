package it.epicode.controller;


import it.epicode.exceptions.NotFoundException;
import it.epicode.model.Prenotazione;
import it.epicode.request.EventoRequest;
import it.epicode.request.PrenotazioneRequest;
import it.epicode.responses.DefaultResponse;
import it.epicode.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public ResponseEntity<DefaultResponse> getAll(Pageable pageable){
        return DefaultResponse.noMessage(prenotazioneService.cercaTuttilePrenotazioniUtente(pageable), HttpStatus.OK);
    }

    @PostMapping("/creazionePrenotazione")
    public ResponseEntity<DefaultResponse> creazionePrenototazione(@RequestBody @Validated PrenotazioneRequest prenotazioneRequest) throws NotFoundException {
        prenotazioneService.creazionePrenotazione(prenotazioneRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(DefaultResponse.customMessage("Creato", "Prenotazione effettuata con successo",HttpStatus.CREATED)).getBody();
    }
    @GetMapping("/prenotazioni/{id}")
    public ResponseEntity<DefaultResponse> cercaPrenotazionePerId(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.customMessage("Trovato",prenotazioneService.cercaPrenotazionetPerId(id),HttpStatus.OK);
    }

    @DeleteMapping("/prenotazioni/{id}")
    public ResponseEntity<DefaultResponse> eliminaPrenotazione(@PathVariable int id) throws NotFoundException {
        prenotazioneService.cancellaPrenotazione(id);
        return DefaultResponse.noObject("Prenotazione con id="+id+" eliminata",HttpStatus.OK);
    }
}
