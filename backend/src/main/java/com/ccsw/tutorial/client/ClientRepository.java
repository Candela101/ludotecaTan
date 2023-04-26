package com.ccsw.tutorial.client;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ccsw.tutorial.client.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByName(String name);
}
