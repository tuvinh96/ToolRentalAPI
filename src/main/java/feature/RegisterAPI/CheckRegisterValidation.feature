Feature: register API

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
    Given I have data as "<UserName>" and "<Value1>" and "<Password>" and "<Value2>"
    When send post request
    Then Api responds status code with "<StatusCode>"
    Then Api responds Error message "<ErrorMessage>"

    Examples:
      | UserName | Value1             | Password | Value2  | StatusCode | ErrorMessage                                  |
      | email    | eve.holt@reqres.in | password | pistol  | 200        |                                               |
      | email    | eve.holt@reqres    | password | pistol  | 400        | Note: Only defined users succeed registration |
      | email    | eve.holtreqres.in  | password | pistol  | 400        | Note: Only defined users succeed registration |
      | email    | \\@reqres.in       | password | pistol  | 400        | Note: Only defined users succeed registration |
      | email    | missing            | password | pistol  | 400        | Missing email or username                     |
      | email    | null               | password | pistol  | 400        | Missing email or username                     |
      | email    | \\"\\"             | password | pistol  | 400        | Missing email or username                     |
      | email    | eve.holt@reqres.in | password | 000000  | 200        | Note: Only defined users succeed registration |
      | email    | eve.holt@reqres.in | password | abc     | 200        | Note: Only defined users succeed registration |
      | email    | eve.holt@reqres.in | password | missing | 400        | Missing password                              |
      | email    | eve.holt@reqres.in | password | null    | 400        | Missing password                              |
      | email    | eve.holt@reqres.in | password | \\"\\"  | 400        | Missing password                              |
