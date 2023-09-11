package features.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;

import java.util.List;
import java.util.Map;

public class BookingSteps extends BasePage {

    //public static WebDriver driver;
    HomePage homePage = new HomePage();
    @Given("I am on the home page")
    public void i_am_on_the_home_page() {
        //driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage.navigateToHomePage();

    }

    @When("I browse through the page")
    public void i_browse_through_the_page() {
        homePage.goToRoomsCategory();
    }
    @Then("I have the option to book a room")
    public void i_have_the_option_to_book_a_room() {
       homePage.assertBookButtonDisplayed();
    }

    @Then("I proceed to book the room")
    public void i_proceed_to_book_the_room() { homePage.clickOpenBookingButton(); }

    @Then("I have the booking form")
    public void i_have_the_booking_form() { homePage.validateBookingFormIsOpened(); }

    @Then("Fill in the required information to book room")
    public void fill_in_the_required_information_to_book_room(DataTable bookingFormDetails) {

        List<Map<String, String>> roomBookForm = bookingFormDetails.asMaps(String.class, String.class);

        homePage.chooseDates(roomBookForm.get(0).get("bookingstartdate"),
                roomBookForm.get(0).get("bookingenddate"));

        homePage.fillRequiredPersonalInformation(roomBookForm.get(0).get("fname"),
                roomBookForm.get(0).get("lname"),
                roomBookForm.get(0).get("email"),
                roomBookForm.get(0).get("phone"));
    }

    @Then("I complete booking the room for chosen date")
    public void i_complete_booking_the_room_for_chosen_date() {
        homePage.completeBooking();
    }

    @Then("I verify the confirmation pop up display")
    public void i_verify_the_confirmation_pop_up_display() { homePage.validateConfirmationModalDisplay(); }

    @Then("Verify booking confirmation message and room booked dates")
    public void verify_booking_confirmation_message_and_room_booked_dates() { homePage.verifyBookingConfirmation(); }

}
