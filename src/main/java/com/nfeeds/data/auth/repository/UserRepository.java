package com.nfeeds.data.auth.repository;

import com.nfeeds.data.auth.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface used by Spring Data Rest to create CRUD endpoints over the User table.
 * The base endpoints generated will have the form:
 * GET|POST : {basePath}/users
 * GET|POST|PUT|DELETE : {basePath}/users/{id}
 * (plus the /profile and OPTIONS endpoint to get the endpoints metadata and options)
 *
 * Additional endpoint will be created based on the query functions of the interface:
 * GET : {basePath}/users/search/{specified_path|function_name}?{parameters}
 *
 * The response of the endpoint will follow HATEOAS specifications.
 */
public interface UserRepository extends CrudRepository<User, String> {}
