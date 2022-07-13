Feature: Validating Place API's
  @AddPlace @Regression
  Scenario Outline: Verify if Place is being successfully added using AddPlace API
    Given Add Place Payload with "<name>", "<language>" and "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "GetPlaceAPI"


  Examples:
    |name       | language  | address              |
    |AAhouse    | English   | World cross centre   |
    |BBhouse    | French    | World cross centre1  |
    |CChouse    | French    | World cross centre2  |
    |DDhouse    | French    | World cross centre3  |

  @DeletePlace @Regression
  Scenario: Verify if Delete Place functionality is working
    Given Delete Place Payload
    When User calls "DeletePlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"

