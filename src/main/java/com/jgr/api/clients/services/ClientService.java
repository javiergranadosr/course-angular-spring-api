package com.jgr.api.clients.services;

import com.jgr.api.clients.exceptions.ClientDataAccessException;
import com.jgr.api.clients.exceptions.ClientException;
import com.jgr.api.clients.mappers.ClientDTOToClient;
import com.jgr.api.clients.models.Client;
import com.jgr.api.clients.models.dto.ClientDTO;
import com.jgr.api.clients.repositories.ClientRepository;
import com.jgr.api.clients.services.interfaces.IClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository repository;
    @Autowired
    ClientDTOToClient mapper;

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        log.info("Init findAll()");
        return (List<Client>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long clientId) {
        log.info("Init findById");
        return repository.findById(clientId).orElseThrow(() -> new ClientException(HttpStatus.NOT_FOUND, "User not found."));
    }

    @Override
    @Transactional
    public Client create(ClientDTO clientDTO) {
        log.info("Init create()");
        try {
            Client client = mapper.mapper(clientDTO);
            return  repository.save(client);
        }catch (DataAccessException e) {
            log.info("Catch create()");
            log.info(e.getMessage());
            throw new ClientDataAccessException("Error in create client.");
        }
    }

    @Override
    public Client update(ClientDTO clientDTO, Long clientId) {
        log.info("Init update()");
        Client client = findById(clientId);
        try {
            client.setName(clientDTO.getName());
            client.setSurnames(clientDTO.getSurnames());
            client.setEmail(clientDTO.getEmail());
            return repository.save(client);
        }catch (DataAccessException e) {
            log.info("Catch update()");
            log.info(e.getMessage());
            throw new ClientDataAccessException("Error in update client.");
        }
    }

    @Override
    @Transactional
    public void delete(Long clientId) {
        log.info("Init delete()");
        Optional<Client> resultClient = repository.findById(clientId);
        if (resultClient.isEmpty()) {
            throw new ClientException(HttpStatus.NOT_FOUND, "User not found by delete.");
        }
        repository.deleteById(resultClient.get().getId());
    }
}
