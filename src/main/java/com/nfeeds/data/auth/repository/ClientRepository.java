package com.nfeeds.data.auth.repository;

import com.nfeeds.data.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<User, String> {

}
