package com.livevox.phonebook.configuration;

import static java.util.Locale.ENGLISH;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livevox.phonebook.dto.Contact;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.datatable.DataTableType;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class TypeRegistryConfig implements TypeRegistryConfigurer {

  private static ObjectMapper mapper;

  @Autowired
  public void init(ObjectMapper objectMapper) {
    TypeRegistryConfig.mapper = objectMapper;
  }

  @Override
  public Locale locale() {
    return ENGLISH;
  }

  @Override
  public void configureTypeRegistry(TypeRegistry typeRegistry) {
    typeRegistry.defineDataTableType(contactDataTableType());
  }

  private DataTableType contactDataTableType() {
    return new DataTableType(Contact.class,
        (Map<String, String> row) -> mapper.convertValue(row, Contact.class));
  }
}
