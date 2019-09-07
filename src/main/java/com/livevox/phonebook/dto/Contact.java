package com.livevox.phonebook.dto;

import java.time.Instant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = {
    "firstName",
    "phone",
    "email"})
@EqualsAndHashCode(of = {"id"})
public class Contact {

  private String id;
  private String prefix;
  private String firstName;
  private String middleName;
  private String lastName;
  private String phone;
  private String email;

  private Integer version;
  private Instant lastUpdatedOn;
  private String lastUpdatedBy;

}
