Feature:ToolsAPITesting 
Scenario: Check response when send request successfully
When send request with valid URL and method and params
Then Api responds status code 200 and list of tools