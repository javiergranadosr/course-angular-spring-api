package com.jgr.api.clients.mappers;

import com.jgr.api.clients.models.Client;
import com.jgr.api.clients.models.dto.ClientDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClientDTOToClient implements IMapper<ClientDTO, Client>{
    @Override
    public Client mapper(ClientDTO in) {
        Client client = new Client();
        client.setName(in.getName());
        client.setSurnames(in.getSurnames());
        client.setEmail(in.getEmail());
        client.setCreateAt(new Date());
        return client;
    }
}
