Feature: User Registration

  Scenario: Valid user registration
    Given User is on the Advantage Online Shopping registration page
    When User enters valid registration details
    And User submits the registration form
    Then User should be redirected to the login page
    And A success message should be displayed

  Scenario: Invalid user registration (missing email)
    Given User is on the Advantage Online Shopping registration page
    When User enters invalid registration details missing email
    And User submits the registration form
    Then User should see an error message indicating that email is required

  Scenario: Invalid user registration (invalid email format)
    Given User is on the Advantage Online Shopping registration page
    When User enters invalid registration details invalid email
    And User submits the registration form
    Then User should see an error message indicating that email is invalid

  Scenario: Invalid user registration (password mismatch)
    Given User is on the Advantage Online Shopping registration page
   When User enters invalid registration details password mismatch
    And User submits the registration form
    Then User should see an error message indicating that passwords do not match
