package com.gmail.seanmc560.dimensiondataapp.services;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ServerService {

  void delete(Long id);

  ServerEntity update(Long id, String name, String description);

  ServerEntity save(String name, String description);

  Long count();

  boolean exists(Long id);

  Page<ServerEntity> findAllByNameContainingAndDescriptionContaining(String name, String description, PageRequest pageRequest);
}
