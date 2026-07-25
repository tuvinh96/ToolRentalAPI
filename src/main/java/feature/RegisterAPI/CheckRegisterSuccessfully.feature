Feature: register API

  @HappyCase
  Scenario Outline: check register new user successfully
    Given I have header
      | key             | value                                   |
      | x-api-key       | reqres_8fb2711c6c344d31a4a8353272e6f199 |
      | Accept-Encoding | gzip,deflate,br                         |
      | Content-Type    | application/json                        |
    Given I have url and method
      | url          | method |
      | api/register | POST   |
    Given I have request body
      | requestBody                         |
      | UserRegister\\User_RequestBody.json |
    When send post request
    Then Api responds status code with "<StatusCode>"
    Then Api responds Error message "<ErrorMessage>"

    Examples:
      | StatusCode |
      | 200        |
