package com.nfeeds.data.auth.repository;

import com.nfeeds.data.auth.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, String> {

    @Query("select case when (count(u) > 0) then true else false end from Client u where u.clientId = :clientId")
    Boolean checkClientIdExistence(@Param("clientId") String clientId);
}
