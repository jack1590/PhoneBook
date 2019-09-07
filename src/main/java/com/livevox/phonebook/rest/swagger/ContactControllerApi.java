package com.livevox.phonebook.rest.swagger;

import com.livevox.phonebook.dto.Contact;
import com.livevox.phonebook.dto.Search;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "Contact REST endpoints")
public interface ContactControllerApi {

  @ApiOperation(value = "Save a contact", notes = "Creates contacts",
      response = Contact.class)
  @ApiResponses(value = {@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK"),
      @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found"),
      @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal Error"),
      @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request")})
  ResponseEntity<Contact> create(@RequestBody Contact contact) throws URISyntaxException;

  @ApiOperation(value = "Find contacts by criteria", response = Page.class)
  @ApiResponses(value = {@ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK"),
      @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Not Found"),
      @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal Error"),
      @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Bad request")})
  ResponseEntity<Page<Contact>> getContacts(@RequestBody Search contactSearch);

}
