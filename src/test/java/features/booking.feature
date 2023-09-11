Feature: Book a room

@book
Scenario: Option to book a room is available on the page
Given I am on the home page
When I browse through the page
Then I have the option to book a room


  @mybooking
  Scenario: Book the room and verify confirmation
    Given I am on the home page
    When I browse through the page
    Then I have the option to book a room
    Then I proceed to book the room
    Then I have the booking form
    Then Fill in the required information to book room
      | fname | lname  | email          | phone       | bookingstartdate | bookingenddate |
      | test  | test   | test@test.com  |12345678909  | 04               | 05             |
    Then I complete booking the room for chosen date
    Then I verify the confirmation pop up display
    Then Verify booking confirmation message and room booked dates