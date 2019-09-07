package com.livevox.phonebook.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@ToString(callSuper = true, of = {
    "firstName",
    "phone",
    "email"})
@EqualsAndHashCode(callSuper = true)
public class ContactEntity extends BaseEntity {

  private String prefix;
  private String firstName;
  private String middleName;
  private String lastName;
  private String phone;
  private String email;

}
