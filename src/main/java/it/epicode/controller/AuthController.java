package it.epicode.controller;

import it.epicode.exceptions.BadRequestException;
import it.epicode.exceptions.NotFoundException;
import it.epicode.model.Utente;
import it.epicode.request.LoginRequest;
import it.epicode.request.UtenteRequest;
import it.epicode.security.JwtTools;
import it.epicode.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JwtTools jwtTools;

    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteRequest utenteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw  new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return utenteService.save(utenteRequest);

    }
    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) throws NotFoundException {
        if(bindingResult.hasErrors()){
            throw  new BadRequestException(bindingResult.getAllErrors().toString());
        }

        Utente utente = utenteService.getByUsername(loginRequest.getUsername());

        return jwtTools.createToken(utente);
    }
}
