package com.jgr.api.clients.services.interfaces;

import com.jgr.api.clients.exceptions.ClientDataAccessException;
import com.jgr.api.clients.models.Client;
import com.jgr.api.clients.models.dto.ClientDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface IClientService {
    List<Client> findAll();
    Client findById(Long clientId);
    Client create(ClientDTO clientDTO);
    Client update(ClientDTO clientDTO, Long clientId);
    void delete(Long clientId);
}
