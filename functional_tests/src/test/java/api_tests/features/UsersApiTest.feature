Feature: Validate Users API

  Background:
    Given I have access

  @acceptance_test
  Scenario: Validate Get all users API
    When I call get Users API
    Then I should see "200 OK" message
    And I should see 10 users in response

  @acceptance_test
  Scenario Outline: Validate Get user by ID API response
    When I call get User API with ID <user_id>
    Then I should see "200 OK" message
    And I should I see user ID as <user_id> in response
    And I should I see user name as "<user_name>" in response

    Examples:
      | user_id | user_name                |
      | 8       | Nicholas Runolfsdottir V |
      | 1       | Leanne Graham            |

  @acceptance_test
  Scenario Outline: Validate Post user API
    When I post Users API with details as following:
      | name   | username   | email   | street   | suite   | city   | zipcode   | lat   | lng   | phone   | website   | companyName   | catchPhrase   | bs   |
      | <name> | <username> | <email> | <street> | <suite> | <city> | <zipcode> | <lat> | <lng> | <phone> | <website> | <companyName> | <catchPhrase> | <bs> |
    Then I should see "201 Created" message
    And I should see user details same as what was posted

    Examples:
      | name           | username | email             | street      | suite    | city        | zipcode    | lat      | lng     | phone                 | website       | companyName     | catchPhrase   | bs                |
      | Leanne Graham  | Leanne   | Sincere@april.biz | Kulas Light | Apt. 556 | Gwenborough | 92998-3874 | -37.3159 | 81.1496 | 1-770-736-8031 x56442 | hildegard.org | Romaguera-Crona | client-server | harness e-markets |