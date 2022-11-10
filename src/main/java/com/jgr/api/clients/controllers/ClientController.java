package com.jgr.api.clients.controllers;

import com.jgr.api.clients.models.Client;
import com.jgr.api.clients.models.dto.ClientDTO;
import com.jgr.api.clients.services.ClientService;
import com.jgr.api.clients.services.interfaces.IClientService;
import com.jgr.api.clients.utils.ClientResponseSuccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private IClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Client> findById(@PathVariable("clientId") Long clientId) {
        return new ResponseEntity<>(service.findById(clientId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ClientDTO clientDTO, BindingResult result) {
        ClientResponseSuccess<Client> response = new ClientResponseSuccess<>();

        if (result.hasErrors()) {
            Map<String, Object> resp = new HashMap<>();
            // FORMA TRADICIONAL JAVA VERSION 8 JDK

            /*List<String> errors = new ArrayList<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.add("Field " + error.getField() + ": " + error.getDefaultMessage());
            }*/

            // FORMA JAVA VERSION 11+ PROGRAMACION FUNCIONAL

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field " + err.getField() + ": " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            resp.put("errors", errors);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        Client clientSuccess = service.create(clientDTO);
        response.setData(clientSuccess);
        response.setMessage("Client saved success");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<?> update(@Valid @RequestBody ClientDTO clientDTO, BindingResult result, @PathVariable("clientId") Long clientId) {
        ClientResponseSuccess<Client> response = new ClientResponseSuccess<>();
        if (result.hasErrors()) {
            Map<String, Object> resp = new HashMap<>();
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field " + err.getField() + ": " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            resp.put("errors", errors);
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        Client clientSuccess = service.update(clientDTO, clientId);
        response.setData(clientSuccess);
        response.setMessage("Client updated");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> delete(@PathVariable("clientId") Long clientId) {
        Map<String, Object> response = new HashMap<>();
        service.delete(clientId);
        response.put("message", "Client deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
