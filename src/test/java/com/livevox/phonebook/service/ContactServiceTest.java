package com.livevox.phonebook.service;

import static com.livevox.phonebook.utils.ContactResource.EMAIL;
import static com.livevox.phonebook.utils.ContactResource.FIRST_NAME;
import static com.livevox.phonebook.utils.ContactResource.MIDDLE_NAME;
import static com.livevox.phonebook.utils.ContactResource.PHONE;
import static com.livevox.phonebook.utils.ContactResource.PREFIX;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import com.livevox.phonebook.dto.Contact;
import com.livevox.phonebook.dto.Search;
import com.livevox.phonebook.mapper.ContactMapper;
import com.livevox.phonebook.repository.ContactRepository;
import com.livevox.phonebook.repository.entity.ContactEntity;
import com.livevox.phonebook.utils.ContactResource;
import com.querydsl.core.types.Predicate;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ContactServiceTest {

  private ContactService service;

  @Mock
  private ContactRepository repository;

  private ContactMapper mapper = Mappers.getMapper(ContactMapper.class);

  @Before
  public void setUp() {
    initMocks(this);
    service = new ContactService(repository, mapper);
  }

  @Test
  public void testSaveContact() {
    // Given
    Contact contact = ContactResource.getContact();
    ContactEntity contactEntity = ContactResource.getContactEntity();
    contactEntity.setId(UUID.randomUUID().toString());

    // When
    when(repository.save(any())).thenReturn(contactEntity);
    Contact saved = service.save(contact);

    // Then
    assertThat("Has id", saved.getId(), notNullValue());
    assertThat("Same Id", saved.getId(), equalTo(contactEntity.getId()));
    assertThat("Same prefix", saved.getPrefix(), equalTo(PREFIX));
    assertThat("Same first name", saved.getFirstName(), equalTo(FIRST_NAME));
    assertThat("Same middle name", saved.getMiddleName(), equalTo(MIDDLE_NAME));
    assertThat("Same phone", saved.getPhone(), equalTo(PHONE));
    assertThat("Same mail", saved.getEmail(), equalTo(EMAIL));
    verify(repository, times(1)).save(any());
  }

  @Test
  public void testFindContacts() {
    // Given
    Search contactSearch = new Search();
    contactSearch.setFilterParam("XXXXXXXXX");
    Page<ContactEntity> pageEntity = mock(Page.class);

    // When
    when(repository.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(pageEntity);
    service.find(contactSearch);

    // Then
    ArgumentCaptor<Predicate> predicateCaptor = ArgumentCaptor.forClass(Predicate.class);
    verify(repository, times(1)).findAll(predicateCaptor.capture(), any(Pageable.class));
    assertThat("Predicate is not null", predicateCaptor.getValue(), notNullValue());
    assertThat("Predicate has filter param", predicateCaptor.getValue().toString(),
        containsString(contactSearch.getFilterParam()));
    assertThat("Predicate has prefix as criteria", predicateCaptor.getValue().toString(),
        containsString("prefix"));
    assertThat("Predicate has first name as criteria", predicateCaptor.getValue().toString(),
        containsString("firstName"));
    assertThat("Predicate has middle name as criteria", predicateCaptor.getValue().toString(),
        containsString("middleName"));
    assertThat("Predicate has last name as criteria", predicateCaptor.getValue().toString(),
        containsString("lastName"));
    assertThat("Predicate has phone as criteria", predicateCaptor.getValue().toString(),
        containsString("phone"));
    assertThat("Predicate has email as criteria", predicateCaptor.getValue().toString(),
        containsString("email"));
  }
}