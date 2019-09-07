package com.livevox.phonebook.service;

import com.livevox.phonebook.dto.Contact;
import com.livevox.phonebook.dto.Search;
import com.livevox.phonebook.logging.Logged;
import com.livevox.phonebook.mapper.ContactMapper;
import com.livevox.phonebook.repository.ContactRepository;
import com.livevox.phonebook.repository.entity.ContactEntity;
import com.livevox.phonebook.repository.entity.QContactEntity;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactService {

  private final QContactEntity CONTACT = QContactEntity.contactEntity;

  private final ContactRepository repository;
  private final ContactMapper mapper;

  public ContactService(ContactRepository repository, ContactMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Logged
  @Transactional
  public Contact save(Contact contact) {
    ContactEntity entity = mapper.toEntity(contact);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  @Logged
  @Transactional(readOnly = true)
  public Page<Contact> find(Search contactSearch) {
    Pageable pageable = contactSearch.createPageRequest();
    BooleanBuilder builder = new BooleanBuilder(CONTACT.isNotNull());

    builder.and(CONTACT.prefix.containsIgnoreCase(contactSearch.getFilterParam()));
    builder.or(CONTACT.firstName.containsIgnoreCase(contactSearch.getFilterParam()));
    builder.or(CONTACT.lastName.containsIgnoreCase(contactSearch.getFilterParam()));
    builder.or(CONTACT.middleName.containsIgnoreCase(contactSearch.getFilterParam()));
    builder.or(CONTACT.email.containsIgnoreCase(contactSearch.getFilterParam()));
    builder.or(CONTACT.phone.containsIgnoreCase(contactSearch.getFilterParam()));

    Page<ContactEntity> contacts = repository.findAll(builder.getValue(), pageable);
    return contacts.map(mapper::toDto);
  }
}
