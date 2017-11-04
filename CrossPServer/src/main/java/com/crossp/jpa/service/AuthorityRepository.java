package com.crossp.jpa.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.Authority;


@Service
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

}
