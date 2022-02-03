 Feature: Toll Data Feature
   Scenario: User calls the post method to save the data
     When a user saves the new toll data for car with registration number KA45J7656 with a toll price of 65 in the toll NH-41_Toll74
     Then the data with registration number KA45J7656 get saves in the database

   Scenario: User requests for the toll data
     When the user invokes /get
     Then the user receives response code of 200
     And receives all the toll data





