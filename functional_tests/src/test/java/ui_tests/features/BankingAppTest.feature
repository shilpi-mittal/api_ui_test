Feature: Validate Banking app UI

  Background:
    Given I am on Home Page

  @acceptance_test
  Scenario: Validate navigation to Payees page using the navigation menu
    When I click on Menu
    And I click on Payees option
    Then I should see Payees Page loaded

  @acceptance_test
  Scenario Outline: Validate adding new payee in the Payees page
    When I navigate to Payees Page
    And I click on Add button
    And I enter payee name as "<name>"
    And I enter payee account number as "<account_number>"
    And I submit details
    Then I should see "Payee added" message as notification
    And payee "<name>" should be added to the list on Payees page

    Examples:
      | name   | account_number  |
      | payee1 | 111111111111662 |
#      | payee2 | 111111111112662 |

  @acceptance_test
  Scenario: Validate that payee name is a required field
    When I navigate to Payees Page
    And I click on Add button
    And I submit details
    Then I should see error as "A problem was found. Please correct the field highlighted below."
    And I should error icon on Payee name field
    When I enter payee name as "Payee3"
    Then error message disappears
    And error icon on payee name field disappears

  @acceptance_test
  Scenario Outline: Validate that payees can be sorted by name
    When I navigate to Payees Page
    And I click on Add button
    And I enter payee name as "<name>"
    And I enter payee account number as "<account_number>"
    And I submit details
    Then I should see "Payee added" message as notification
    And payee "<name>" should be added to the list on Payees page
    And the list should be sorted in ascending order
    When I click on Name column
    Then the list should be sorted in descending order

    Examples:
      | name   | account_number  |
      | payee1 | 111111111111662 |
