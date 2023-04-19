package com.nfeeds.data.auth.repository;

import com.nfeeds.data.auth.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * <p> Interface used by Spring Data Rest to create CRUD endpoints over the User table. </p>
 * <p> The base endpoints generated will have the form: </p>
 * <code>
 * <p><i> GET|POST : {basePath}/users </i></p>
 * <p><i> GET|POST|PUT|DELETE : {basePath}/users/{id} </i></p>
 * </code>
 * <p>(plus the /profile and OPTIONS endpoint to get the endpoints metadata and options) </p>
 * <p>
 * <p> Additional endpoint will be created based on the query functions of the interface: </p>
 * <code><p><i> GET : {basePath}/users/search/{specified_path|function_name}?{parameters} </i></p></code>
 * <p>
 * <p> The response of the endpoint will follow HATEOAS specifications. </p>
 */
public interface UserRepository extends CrudRepository<User, String> {}
