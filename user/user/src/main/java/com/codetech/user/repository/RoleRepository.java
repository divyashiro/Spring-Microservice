package com.codetech.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codetech.user.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

}
