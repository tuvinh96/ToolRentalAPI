Feature: Update user information API

  Scenario Outline: check update user information successfully
    Given I have header
      | key       | value                                   |
      | x-api-key | reqres_8fb2711c6c344d31a4a8353272e6f199 |
    Given I have url and method below
      | url                           | method |
      | https://reqres.in/api/users/2 | PUT    |
    Given I have request body below
      | requestBody                      |
      | UserUpdate\\User_UpdateBody.json |
    When send request
    Then Api responds status code "<StatusCode>"
    Then Api responds body

    Examples:
      | StatusCode |
      | 200        |
