@API
Feature: Store Endpoint:
  https://petstore.swagger.io/#/store
  Automation of the store endpoint for petstore
  Contains GET inventory, POST order, GET order, DELETE order

  Scenario: Successfully get Inventory request
    Given the Admin performs a get inventory request
    Then confirm result code is 200

  Scenario: Fail to get Inventory request
    Given the Admin performs a get inventory request and it fails
    Then confirm result code is 404

   Scenario: Successfully place an order at a store
     Given the Admin sends a request to place an order
     Then confirm result code is 200

  Scenario: Failure to place an order at a store due to missing petId
    Given the Admin sends a request to place an order and it fails
    Then confirm result code is 200

  Scenario: Successfully get an order by orderId
    Given the Admin sends a request to place an order
    When the Admin sends a request to get an order by orderId
    Then confirm result code is 200

   ## this also validates the scenario of
   # "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions"
  ## as per the swagger api contract
  Scenario: Try to get orderId of unplaced order
    Given the Admin gets order of invalid orderId
    When confirm result code is 404
    Then confirm message returned is "Order not found"

  Scenario: Successfully delete an order by orderId
    Given the Admin sends a request to place an order
    When the Admin sends a request to get an order by orderId
    Then the Admin sends a request to delete an order by orderId
    And confirm result code is 200

  Scenario: Attempt to delete an order that does not exist
    Given the Admin sends a request to place an order
    When the Admin sends a request to get an order by orderId
    Then the Admin attempts to delete an non-existing orderId
    And confirm result code is 404
    And confirm message returned is "Order Not Found"
