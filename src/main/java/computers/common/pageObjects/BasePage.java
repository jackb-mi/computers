package computers.common.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    protected static WebDriver driver;
    // TODO work out where button should be and name
    private static final By PAGE_HEADER_IDENTIFIER = By.cssSelector("#main > h1");
    private static final By CANCEL_BUTTON_IDENTIFIER = By.cssSelector("#main > form > div > a");


    protected static void access_url(String url) {
        driver.get(url);
    }

    static void select_element(By elementIdentifier) {
        driver.findElement(elementIdentifier).click();
    }

    static String get_text(By elementIdentifier) {
        return driver.findElement(elementIdentifier).getText();
    }

    static void enter_text(By elementIdentifier, String textToEnter) {
        driver.findElement(elementIdentifier).sendKeys(textToEnter);
    }

    static void clear_text(By elementIdentifier) {
        driver.findElement(elementIdentifier).clear();
    }

    static void select_from_dropdown(By dropdownIdentifier, String value) {
        Select dropDownValue = new Select(driver.findElement(dropdownIdentifier));
        dropDownValue.selectByVisibleText(value);
    }

    public static String get_page_title() {
        return driver.getTitle();
    }

    public static String get_page_header() {
        return get_text(PAGE_HEADER_IDENTIFIER);
    }

    public static void select_cancel_button() {
        select_element(CANCEL_BUTTON_IDENTIFIER);
    }
}
