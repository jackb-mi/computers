package computers.common.pageObjects;

import org.openqa.selenium.By;

public class ComputerDatabaseSearchPage extends BasePage {

    public static final String COMPUTER_DATABASE_URL = "http://computer-database.herokuapp.com/computers";
    public static final String PARTIAL_PAGE_HEADING = "computers found";
    public static final String NO_COMPUTERS_FOUND_PAGE_HEADING = "No computers found";
    private static final By ADD_A_NEW_COMPUTER_BUTTON_IDENTIFIER = By.id("add");
    private static final By SUCCESS_MESSAGE_IDENTIFIER = By.cssSelector("#main > div.alert-message.warning");
    public static final By SEARCH_BOX_IDENTIFIER = By.id("searchbox");
    private static final By FILTER_BY_NAME_BUTTON_IDENTIFIER = By.id("searchsubmit");
    private static final By COMPUTER_NAME_OF_FIRST_RESULT_IDENTIFIER = By.cssSelector("#main > table > tbody > tr > td:nth-child(1) > a");
    private static final By INTRODUCED_DATE_OF_FIRST_RESULT_IDENTIFIER = By.cssSelector("#main > table > tbody > tr:nth-child(1) > td:nth-child(2)");
    private static final By DISCONTINUED_DATE_OF_FIRST_RESULT_IDENTIFIER = By.cssSelector("#main > table > tbody > tr:nth-child(1) > td:nth-child(3)");
    private static final By COMPANY_OF_FIRST_RESULT_IDENTIFIER = By.cssSelector("#main > table > tbody > tr:nth-child(1) > td:nth-child(4)");;

    public static String get_success_message() {
        return get_text(SUCCESS_MESSAGE_IDENTIFIER);
    }

    public static void search_for_computer(String string) {
        enter_text(SEARCH_BOX_IDENTIFIER, string);
        select_element(FILTER_BY_NAME_BUTTON_IDENTIFIER);
    }

    //TODO improve get row methods
    public static String get_computer_name_of_first_result() {
        return get_text(COMPUTER_NAME_OF_FIRST_RESULT_IDENTIFIER);
    }

    public static String get_introduced_date_of_first_result() {
        return get_text(INTRODUCED_DATE_OF_FIRST_RESULT_IDENTIFIER);
    }

    public static String get_discontinued_date_of_first_result() {
        return get_text(DISCONTINUED_DATE_OF_FIRST_RESULT_IDENTIFIER);
    }

    public static String get_company_of_first_result() {
        return get_text(COMPANY_OF_FIRST_RESULT_IDENTIFIER);
    }

    public static void select_add_a_new_computer_button() {
        select_element(ADD_A_NEW_COMPUTER_BUTTON_IDENTIFIER);
    }

    public static void select_first_result() {
        select_element(COMPUTER_NAME_OF_FIRST_RESULT_IDENTIFIER);
    }
}
