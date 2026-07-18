Feature: Check validation of single field

  Scenario Outline: Check validation of single field
    Given I have header
      | key             | value           |
      | Accept-Encoding | gzip,deflate,br |
      | Accept          | */*             |
    Given I have url and method
      | url                                            | method |
      | https://simple-tool-rental-api.glitch.me/tools | GET    |
    Given I have data as "<FieldName1>" and "<Value1>" and "<FieldName2>" and "<Value2>" and "<FieldName3>" and "<Value3>"
    When send request
    Then Api responds status code "<StatusCode>"
    Then Api responds Error message "<Errormessage>"

    Examples:
      | FieldName1 | Value1              | FieldName2 | Value2  | FieldName3 | Value3  | StatusCode | ErrorMessage                                                                                                                               |
      | category   | ladders             | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | plumbing            | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | power-tools         | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | trailers            | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | electric-generators | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | lawn-care           | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | missing             | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | null                | results    | 5       | available  | true    | 400        | Invalid value for query parameter \\'category\\'. Must be one of: ladders, plumbing, power-tools, trailers, electric-generators, lawn-care |
      | category   | ""                  | results    | 5       | available  | true    | 200        |                                                                                                                                            |
      | category   | hammer              | results    | 5       | available  | true    | 400        | Invalid value for query parameter 'category'. Must be one of: ladders, plumbing, power-tools, trailers, electric-generators, lawn-care     |
      | category   | ladder              | results    | 5       | available  | true    | 400        | Invalid value for query parameter 'category'. Must be one of: ladders, plumbing, power-tools, trailers, electric-generators, lawn-care     |
      | category   | ladders             | results    | missing | available  | true    | 200        |                                                                                                                                            |
      | category   | ladders             | results    | null    | available  | true    | 400        | Invalid value for query parameter 'results'. Must be interger number                                                                       |
      | category   | ladders             | results    | ""      | available  | true    | 200        |                                                                                                                                            |
      | category   | ladders             | results    | 0       | available  | true    | 400        | Invalid value for query parameter 'results'. Cannot be less than 1.                                                                        |
      | category   | ladders             | results    | 21      | available  | true    | 400        | Invalid value for query parameter 'results'. Cannot be greater than 20.                                                                    |
      | category   | ladders             | results    | abc     | available  | true    | 400        | Invalid value for query parameter 'results'. Must be interger number                                                                       |
      | category   | ladders             | results    | 5       | available  | missing | 200        |                                                                                                                                            |
      | category   | ladders             | results    | 5       | available  | null    | 400        | Invalid value for query parameter 'available'. Must be one of: true, false                                                                 |
      | category   | ladders             | results    | 5       | available  | ""      | 200        |                                                                                                                                            |
