package com.livevox.phonebook.rest;

import com.livevox.phonebook.dto.Contact;
import com.livevox.phonebook.dto.Search;
import com.livevox.phonebook.rest.swagger.ContactControllerApi;
import com.livevox.phonebook.service.ContactService;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/contacts")
public class ContactController implements ContactControllerApi {

  private final ContactService contactService;

  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @Override
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Contact> create(@RequestBody Contact contact) throws URISyntaxException {
    Contact saved = contactService.save(contact);

    return ResponseEntity
        .created(new URI(saved.getId()))
        .body(saved);
  }

  @Override
  @PostMapping(path = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Page<Contact>> getContacts(@RequestBody Search contactSearch) {
    Page<Contact> contacts = contactService.find(contactSearch);
    return ResponseEntity.ok().body(contacts);
  }
}
