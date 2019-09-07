package com.livevox.phonebook.mapper;

import com.livevox.phonebook.dto.Contact;
import com.livevox.phonebook.repository.entity.ContactEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

  Contact toDto(ContactEntity entity);

  ContactEntity toEntity(Contact dto);
}
