package com.nfeeds.data.auth.repository;

import com.nfeeds.data.auth.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends CrudRepository<Client, String> {

}
