package com.crossp.jpa.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.User;


@Service
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
}
