package com.crossp.jpa.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.AppItem;
import com.crossp.jpa.domain.User;


@Service
public interface AppItemRepository extends CrudRepository<AppItem, Long> {

	public List<AppItem> findByUser(User user);
}
