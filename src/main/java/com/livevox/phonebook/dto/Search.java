package com.livevox.phonebook.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString
public class Search {

  private String filterParam;
  private Integer page = 0;
  private Integer size = 50;
  private SortField sort;

  public Pageable createPageRequest() {
    if (null == sort) {
      return PageRequest.of(page, size);
    } else {
      return PageRequest.of(page, size, new Sort(sort.getOrder(), sort.getField()));
    }
  }

}
