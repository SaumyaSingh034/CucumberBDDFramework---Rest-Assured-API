Feature: Validating Place APIs

  Scenario: Verify if Place is being successfully added using AddPlaceAPI
    Given user add Place payload
    When user make POST API call to "AddPlaceAPI"
    Then user validate the status code is 200
    And user validate response body "status" is "OK"
    And user validate response body "scope" is "APP"

