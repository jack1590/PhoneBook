package com.livevox.phonebook;

import static com.livevox.phonebook.utils.TestContext.CONTEXT;

import com.livevox.phonebook.utils.TestContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.web.server.LocalServerPort;

public abstract class IntegrationTest {

  @LocalServerPort
  private int port;

  protected String baseUrl() {
    return "http://localhost:" + port;
  }

  protected TestContext getTestContext() {
    return CONTEXT;
  }

  protected void executePost(String apiPath) {
    final RequestSpecification request = CONTEXT.getRequest();
    final Object payload = CONTEXT.getPayload();

    setPayload(request, payload);

    Response response = request.accept(ContentType.JSON)
        .post(apiPath);

    logResponse(response);

    CONTEXT.setResponse(response);
  }

  private void logResponse(Response response) {
    response.then()
        .log()
        .all();
  }

  private void setPayload(RequestSpecification request, Object payload) {
    if (null != payload) {
      request.contentType(ContentType.JSON)
          .body(payload);
    }
  }
}
