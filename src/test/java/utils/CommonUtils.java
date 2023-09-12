package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.DriversUtils.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static utils.DriversUtils.getDriver;

public class CommonUtils {

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,250)");
    }

    public static void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void clickOperation(WebElement element){
        element.click();
    }

    public static String getDateInDisplayFormat(String dateString){
        DateTimeFormatter format
                = DateTimeFormatter.ofPattern("ddMMMM yyyy");
        LocalDate date = LocalDate.parse(dateString, format);
        return date.toString();

    }
}
