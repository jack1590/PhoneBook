package com.livevox.phonebook.utils;

import com.livevox.phonebook.dto.Contact;
import com.livevox.phonebook.repository.entity.ContactEntity;

public class ContactResource {

  public final static String PREFIX = "MR";
  public final static String FIRST_NAME = "Juan";
  public final static String MIDDLE_NAME = "Carlos";
  public final static String LAST_NAME = "Joya";
  public final static String EMAIL = "jj@jj.com";
  public final static String PHONE = "300 777 7777";

  public static Contact getContact() {
    Contact contact = new Contact();
    contact.setPrefix(PREFIX);
    contact.setFirstName(FIRST_NAME);
    contact.setMiddleName(MIDDLE_NAME);
    contact.setLastName(LAST_NAME);
    contact.setEmail(EMAIL);
    contact.setPhone(PHONE);

    return contact;
  }

  public static ContactEntity getContactEntity() {
    ContactEntity entity = new ContactEntity();
    entity.setPrefix(PREFIX);
    entity.setFirstName(FIRST_NAME);
    entity.setMiddleName(MIDDLE_NAME);
    entity.setLastName(LAST_NAME);
    entity.setEmail(EMAIL);
    entity.setPhone(PHONE);

    return entity;
  }

}
