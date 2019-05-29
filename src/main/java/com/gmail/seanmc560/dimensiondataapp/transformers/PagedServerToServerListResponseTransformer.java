package com.gmail.seanmc560.dimensiondataapp.transformers;

import com.gmail.seanmc560.dimensiondataapp.persistence.ServerEntity;
import com.gmail.seanmc560.dimensiondataapp.responses.PageResponse;
import com.gmail.seanmc560.dimensiondataapp.responses.ServerListResponse;
import com.gmail.seanmc560.dimensiondataapp.responses.ServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PagedServerToServerListResponseTransformer implements Transformer<Page<ServerEntity>, ServerListResponse> {

  private final Transformer<ServerEntity, ServerResponse> serverToServerResponseTransformer;
  private final Transformer<Page, PageResponse> pageToPageResponseTransformer;

  @Override
  public ServerListResponse transform(final Page<ServerEntity> servers) {
    final List<ServerResponse> serverResponseResponseList = servers.getContent().stream()
      .map(serverToServerResponseTransformer::transform)
      .collect(Collectors.toList());

    final PageResponse pageResponse = pageToPageResponseTransformer.transform(servers);

    return ServerListResponse.builder()
      .servers(serverResponseResponseList)
      .page(pageResponse)
      .build();
  }
}
