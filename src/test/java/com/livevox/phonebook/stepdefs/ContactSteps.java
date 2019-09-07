package com.livevox.phonebook.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import com.livevox.phonebook.IntegrationTest;
import com.livevox.phonebook.dto.Contact;
import com.livevox.phonebook.dto.Search;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.List;
import org.json.JSONObject;

public class ContactSteps extends IntegrationTest {

  @Given("^user wants to create a contact|following contacts$")
  public void user_wants_to_create_a_contact(List<Contact> contacts) throws Throwable {
    getTestContext().setPayload(contacts.get(0));
  }

  @When("^user saves a new contact|contacts already exists in the system$")
  public void user_saves_a_new_contact() throws Throwable {
    final String postContactUrl = baseUrl() + "/v1/contacts";
    executePost(postContactUrl);
  }

  @When("user gets contacts by searchFilter {string}")
  public void user_gets_contacts_by_searchFilter(String filterParam) throws Throwable {
    Search contactSearch = new Search();
    contactSearch.setFilterParam(filterParam);
    getTestContext().setPayload(contactSearch);

    final String postContactUrl = baseUrl() + "/v1/contacts/search";
    executePost(postContactUrl);
  }

  @Then("^the save is SUCCESSFUL$")
  public void the_save_is_successful() throws Throwable {
    final Response response = getTestContext().getResponse();
    assertThat(response.getStatusCode()).isEqualTo(201);
  }

  @Then("^the system returns following contacts$")
  public void the_system_returns_following_contacts(DataTable expectedContacts) throws Throwable {
    final Response response = getTestContext().getResponse();
    assertThat(response.getStatusCode()).isEqualTo(200);

    String data = new JSONObject(response.asString()).get("content").toString();
    final Search payload = getTestContext().getPayload(Search.class);

    assertThat(data.contains(payload.getFilterParam()));
  }
}
