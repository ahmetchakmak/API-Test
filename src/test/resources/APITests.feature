Feature: API Tests

  Scenario: GET request to /users
    Given  existing Server application "https://reqres.in/api"
    And   on GET request to "/users"
    Then  it returns expected users list

  Scenario: GET request to single user
    Given  existing Server application "https://reqres.in/api"
    And    on GET request to "/users/2"
    Then   it returns expected user data

  Scenario: GET request to non-existing user
    Given  existing Server application "https://reqres.in/api"
    And    on GET request to "/users/23"
    Then   it returns 404 status code