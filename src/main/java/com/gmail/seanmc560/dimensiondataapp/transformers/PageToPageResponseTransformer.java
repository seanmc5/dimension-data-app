package com.gmail.seanmc560.dimensiondataapp.transformers;

import com.gmail.seanmc560.dimensiondataapp.responses.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageToPageResponseTransformer implements Transformer<Page, PageResponse> {

  public PageResponse transform(final Page page) {
    return PageResponse.builder()
      .size(page.getSize())
      .totalElements(page.getNumberOfElements())
      .totalPages(page.getTotalPages())
      .number(page.getNumber())
      .build();
  }
}
