package com.gmail.seanmc560.dimensiondataapp.services;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import com.gmail.seanmc560.dimensiondataapp.persistence.ServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

  private final ServerRepository serverRepo;

  @Override
  public void delete(final Long id) {
    serverRepo.deleteById(id);
  }

  @Override
  public ServerEntity update(final Long id, final String name, final String description) {
    final ServerEntity serverEntity = serverRepo.findById(id).orElseThrow(IllegalArgumentException::new);
    serverEntity.setName(name);
    serverEntity.setDescription(description);

    return serverRepo.save(serverEntity);
  }

  @Override
  public ServerEntity save(final String name, final String description) {
    return serverRepo.save(
      ServerEntity.builder()
        .name(name)
        .description(description)
        .build()
    );
  }

  @Override
  public Long count() {
    return serverRepo.count();
  }

  @Override
  public boolean exists(final Long id) {
    return serverRepo.existsById(id);
  }

  @Override
  public Page<ServerEntity> findAllByNameContainingAndDescriptionContaining(final String name, final String description, final PageRequest pageRequest) {
    return serverRepo.findAllByNameContainingAndDescriptionContaining(name, description, pageRequest);
  }
}
