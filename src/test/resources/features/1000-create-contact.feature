Feature: Create Contact

  Scenario: Create Contact
    Given user wants to create a contact
      | prefix | firstName | middleName | lastName | phone      | email                      |
      | MR     | Ross      |            | Geller   | 4121230001 | ross.geller@signature.com  |
    When user saves a new contact
    Then the save is SUCCESSFUL
