package it.epicode.controller;

import it.epicode.exceptions.BadRequestException;
import it.epicode.exceptions.NotFoundException;
import it.epicode.request.EventoRequest;
import it.epicode.responses.DefaultResponse;
import it.epicode.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/{id}")
    public ResponseEntity<DefaultResponse> getById(@PathVariable int id) throws NotFoundException {
        return DefaultResponse.customMessage("Trovato",eventoService.cercaEventoPerId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse> crazioneEvento(@RequestBody @Validated EventoRequest eventoRequest, BindingResult bR){
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());

        return DefaultResponse.customMessage("Creato",eventoService.salvaEvento(eventoRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultResponse> update(@PathVariable int id,@RequestBody @Validated EventoRequest eventoRequest, BindingResult bR) throws NotFoundException {
        if(bR.hasErrors()) throw new BadRequestException(bR.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList().toString());
        return DefaultResponse.customMessage("Aggiornato",eventoService.aggiornaEvento(id,eventoRequest),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DefaultResponse> delete(@PathVariable int id) throws NotFoundException {
        eventoService.rimuoviEvento(id);
        return DefaultResponse.noObject("Dipendente con id="+id+" eliminato",HttpStatus.OK);
    }


}
