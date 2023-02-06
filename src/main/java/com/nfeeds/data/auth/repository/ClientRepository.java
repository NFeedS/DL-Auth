package com.nfeeds.data.auth.repository;

import com.nfeeds.data.auth.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
