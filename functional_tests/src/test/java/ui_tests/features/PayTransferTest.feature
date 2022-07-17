Feature: Validate Pay Transfer flow

  Background:
    Given I am on Home Page

  Scenario Outline: Validate Payment
    And I check balances for
      | sender_name   | receiver_name   |
      | <sender_name> | <receiver_name> |
    When I navigate to Payments page
    And I transfer "<amount>"
    Then I should see "Transfer successful" message as notification on home page
    And balances should be updated for both accounts

    Examples:
      | sender_name | receiver_name | amount |
      | Everyday    | Bills         | 500    |
