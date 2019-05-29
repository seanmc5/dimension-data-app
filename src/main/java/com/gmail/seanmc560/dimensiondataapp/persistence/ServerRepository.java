package com.gmail.seanmc560.dimensiondataapp.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ServerRepository extends PagingAndSortingRepository<ServerEntity, Long> {

  Page<ServerEntity> findAllByNameContainingAndDescriptionContaining(final String name, final String description, final Pageable pageable);
}
