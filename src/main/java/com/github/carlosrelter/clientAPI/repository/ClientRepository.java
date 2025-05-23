package com.github.carlosrelter.clientAPI.repository;

import com.github.carlosrelter.clientAPI.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
