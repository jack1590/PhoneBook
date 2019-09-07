Feature: Get contacts

  Background:
    Given following contacts
      | prefix | firstName | middleName | lastName | phone      | email                      |
      | MR     | Ross      |            | Geller   | 4121230001 | ross.geller@signature.com  |
    When contacts already exists in the system

  Scenario: Get Contacts
    When user gets contacts by searchFilter 'ross'
    Then the system returns following contacts
      | prefix | firstName | middleName | lastName | phone      | email                      |
      | MR     | Ross      |            | Geller   | 4121230001 | ross.geller@signature.com  |
