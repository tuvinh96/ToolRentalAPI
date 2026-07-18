Feature: ToolsAPITesting

  Scenario Outline: Check response when send request successfully
    Given I have header
      | key             | value            |
      | Accept-Encoding | gzip,deflate,br  |
      | Accept          | application/json |
    Given I have url and method
      | url                                                                                                     | method |
      | https://simple-tool-rental-api.glitch.me/tools?category=@category&results=@results&available=@available | GET    |
    Given I have "<category>" and "<result>" of tools and "<available>" status
    When send request
    Then Api responds status code "<StatusCode>"
    Then Api responds list of tools correctly

    Examples:
      | category | result | available | StatusCode |
      | trailers | 5      | true      | 200        |
      | ladders  | 3      | true      | 200        |
