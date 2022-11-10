package com.jgr.api.clients.repositories;

import com.jgr.api.clients.models.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
}
