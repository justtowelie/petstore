@API
Feature: Store Endpoint:
  https://petstore.swagger.io/#/store
  Automation of the store endpoint for petstore
  Contains GET inventory, POST order, GET order, DELETE order


  Scenario: Successfully get Inventory request
    Given the Admin performs a get inventory request