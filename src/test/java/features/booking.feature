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
      | test  | test   | test@test.com  |12345678909  | 05               | 07            |
    Then I complete booking the room for chosen date
    Then I verify the confirmation pop up display
    Then I close the browser


  @mybooking2
  Scenario Outline: Book the room and verify confirmation
    Given I am on the home page
    When I browse through the page
    Then I have the option to book a room
    Then I proceed to book the room
    Then I have the booking form
    Then Fill in the required information to book room
      | fname | lname  | email    | phone      | bookingstartdate | bookingenddate |
      | <fname> | <lname> | <email>    | <phone>    | <bookingstartdate> | <bookingenddate> |
    Then I complete booking the room for chosen date
    Then I verify the confirmation pop up display
    Then I close the browser
    Examples:
      | fname | lname  | email          | phone       | bookingstartdate | bookingenddate |
      | test  | test   | test@test.com  |12345678909  | 01               | 03            |
      | test11  | test11   | test11@test.com  |2222222222222  |    04            | 06        |