Feature: register API

  Scenario Outline: check register new user successfully
    Given I have header
      | key       | value                                   |
      | x-api-key | reqres_8fb2711c6c344d31a4a8353272e6f199 |
    Given I have url and method
      | url                            | method |
      | https://reqres.in/api/register | POST   |
    Given I have request body
      | requestBody                         |
      | UserRegister\\User_RequestBody.json |
    When send request
    Then Api responds status code "<StatusCode>"
    Then Api responds body

    Examples:
      | StatusCode |
      | 200        |
