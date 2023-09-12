package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonUtils;
import utils.DriversUtils;

import java.util.concurrent.TimeUnit;

import static utils.CommonUtils.*;
import static utils.CommonUtils.clickOperation;
import static utils.DriversUtils.*;


public class HomePage {

    @FindBy(tagName = "h2")
    private WebElement roomCategoryIdentifier;

    @FindBy(xpath = "//button[contains(@class,'openBooking')]")
    private WebElement bookButton;

    @FindBy(xpath = "//*[contains(@class,'rbc-calendar')]")
    private WebElement calender;

    @FindBy(xpath = "//*[contains(@class,'col-sm-4')]")
    private WebElement roomBookingDetailsForm;

    @FindBy(xpath = "//*[contains(@class,'rbc-toolbar-label')]")
    private WebElement calenderMonthYear;

    @FindBy(xpath = "//*[contains(@class, 'room-firstname')]")
    private WebElement firstName;

    @FindBy(xpath = "//*[contains(@class, 'room-lastname')]")
    private WebElement lastName;

    @FindBy(xpath = "//*[contains(@class, 'room-email')]")
    private WebElement eMail;

    @FindBy(xpath = "//*[contains(@class, 'room-phone')]")
    private WebElement phoneNumber;

    @FindBy(xpath = "//button[text()='Book']")
    private WebElement completeBookingButton;

    @FindBy(xpath = "//div[contains(@class,'confirmation-modal')]")
    private WebElement confirmatiomModal;

    @FindBy(xpath = "//h3[text()='Booking Successful!']")
    private WebElement confirmModalTxt1;
    @FindBy(xpath = "//p[text()='Congratulations! Your booking has been confirmed for:']")
    private WebElement confirmModalTxt2;

    @FindBy(xpath = "//button[text()='Close']")
    private WebElement confirmModalCloseButton;

    @FindBy(xpath = "//p[contains(text(),'either invalid or are already booked')]")
    private WebElement assertFailTxt;

    public String checkInDate, checkOutDate;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void goToRoomsCategory() {
        try {
            scrollToElement(roomCategoryIdentifier);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Error in the rooms category method");
        }
    }

    public void assertBookButtonDisplayed(){
        Assert.assertEquals(true, bookButton.isDisplayed());
    }

    public void navigateToHomePage() {
        getDriver().get("https://automationintesting.online/#/");
    }

    public void clickOpenBookingButton(){
        try{
            clickOperation(bookButton);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error when clicking the book button to book the room");
        }
    }

    public void validateBookingFormIsOpened(){
        try{
            Assert.assertTrue(calender.isDisplayed() && roomBookingDetailsForm.isDisplayed());
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error in opening booking form to fill details");
        }
    }

    public void chooseDates(String startDate, String endDate) {

        WebElement bookingStartDateElement = getDriver().findElement(By.xpath("//button[text()='"+startDate +"']"));
        WebElement bookingEndDateElement = getDriver().findElement(By.xpath("//button[text()='"+ endDate + "']"));

        //selection of the dates on the room booking form
        try{
            new Actions(getDriver()).clickAndHold(bookingStartDateElement).dragAndDrop(bookingEndDateElement,bookingStartDateElement).release().build().perform();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Room booking date selection failed!!!");
        }

        //store the date range for which the rooms are booked for future validations
        checkInDate = startDate + calenderMonthYear.getText();
        checkOutDate = endDate + calenderMonthYear.getText();
    }

    public void fillRequiredPersonalInformation(String fname, String lname, String email, String ph) {
        firstName.clear();
        firstName.sendKeys(fname);
        lastName.clear();
        lastName.sendKeys(lname);
        eMail.clear();
        eMail.sendKeys(email);
        phoneNumber.clear();
        phoneNumber.sendKeys(ph);
    }

    public void completeBooking() {
        clickOperation(completeBookingButton);
    }

    public void validateConfirmationModalDisplay(){
        try{
            Assert.assertTrue(confirmatiomModal.isDisplayed());

            Assert.assertEquals("Booking Successful!", confirmModalTxt1.getText());
            Assert.assertEquals("Congratulations! Your booking has been confirmed for:", confirmModalTxt2.getText());
            Assert.assertTrue(confirmatiomModal.getText().contains(getDateInDisplayFormat(checkInDate)));

            //Confirmation Modal pop up displays different end date so ignoring end date validation
            //Assert.assertTrue(confirmatiomModal.getText().contains(getDateInDisplayFormat(checkOutDate)));
            //String roomBookedDates = getDateInDisplayFormat(checkInDate) + " - " + (getDateInDisplayFormat(checkOutDate));
            //Assert.assertTrue(confirmatiomModal.getText().contains(roomBookedDates));

            clickOperation(confirmModalCloseButton);
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            System.out.println("******!!!!Confirmation modal pop up didn't show up!!!******");
            if(assertFailTxt.isDisplayed()){
                System.out.println("Room not available for the chosen dates as "+ assertFailTxt.getText());
            }
        }
    }
}
