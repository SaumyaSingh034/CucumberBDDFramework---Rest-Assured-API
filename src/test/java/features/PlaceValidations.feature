Feature: Validating Place APIs

  @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given user add Place payload with "<name>" "<language>" "<website>" "<accuracy>" "<phoneNumber>" and "<address>"
    When user make "POST" API call to "AddPlaceAPI"
    Then user validate the status code is 200
    And user validate response body "status" is "OK"
    And user validate response body "scope" is "APP"
    And user verifies the place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
    |name|language|website|accuracy|phoneNumber|address|
    |AUTOMATION-1|FRENCH-IN|https://data-copy.com|50|8877668899|FRANCE|
#    |AUTOMATION-2|SPANISH-IN|https://testing.com|100|8877668899|FRANCE|
#    |AUTOMATION-3|HINDI-IN|https://filter.com|90|8877668899|FRANCE|
#    |AUTOMATION-4|ENGLISH-IN|https://sameer.com|10|8877668899|FRANCE|
#    |AUTOMATION-5|JAPANESE-IN|https://dataset.com|40|8877668899|FRANCE|

  @DeletePlace
    Scenario: Verify delete Functionality
      Given user build the delete place payload
      When user make "POST" API call to "DeletePlaceAPI"
      Then user validate the status code is 200
      And user validate response body "status" is "OK"
