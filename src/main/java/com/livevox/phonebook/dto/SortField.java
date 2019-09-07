package com.livevox.phonebook.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString
public class SortField {

  private Sort.Direction order;
  private String field;
}
