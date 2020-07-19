package computers.common.pageObjects;

import computers.common.dataObjects.Computer;
import org.openqa.selenium.By;

public class EditComputerPage extends BasePage {

    public static final String PAGE_HEADING = "Edit computer";
    private static final By NAME_INPUT_IDENTIFIER = By.id("name");
    private static final By INTRODUCED_DATE_INPUT_IDENTIFIER = By.id("introduced");
    private static final By DISCONTINUED_DATE_INPUT_IDENTIFIER = By.id("discontinued");
    private static final By COMPANY_DROPDOWN_IDENTIFIER = By.id("company");
    private static final By DELETE_THIS_COMPUTER_BUTTON_IDENTIFIER = By.cssSelector("#main > form.topRight > input");
    private static final By SAVE_THIS_COMPUTER_BUTTON_IDENTIFIER = By.cssSelector("#main > form:nth-child(2) > div > input");

    public static void updateComputer(Computer computer) {
        if (computer.getName() != null) update_computers_name(computer.getName());
        if (computer.getIntroducedDate() != null) update_computers_introduced_date(computer.getIntroducedDate());
        if (computer.getDiscontinuedDate() != null) update_computers_discontinued_date(computer.getDiscontinuedDate());
        if (computer.getCompany() != null) update_computers_company(computer.getCompany());
        select_save_computer_button();
    }

    private static void update_computers_name(String name) {
        clear_text(NAME_INPUT_IDENTIFIER);
        enter_text(NAME_INPUT_IDENTIFIER, name);
    }

    private static void update_computers_introduced_date(String introducedDate) {
        clear_text(INTRODUCED_DATE_INPUT_IDENTIFIER);
        enter_text(INTRODUCED_DATE_INPUT_IDENTIFIER, introducedDate);
    }

    private static void update_computers_discontinued_date(String discontinuedDate) {
        clear_text(DISCONTINUED_DATE_INPUT_IDENTIFIER);
        enter_text(DISCONTINUED_DATE_INPUT_IDENTIFIER, discontinuedDate);
    }

    private static void update_computers_company(String company) {
        select_from_dropdown(COMPANY_DROPDOWN_IDENTIFIER, company);
    }

    public static void select_save_computer_button() {
        select_element(SAVE_THIS_COMPUTER_BUTTON_IDENTIFIER);
    }

    public static void select_delete_this_computer() {
        select_element(DELETE_THIS_COMPUTER_BUTTON_IDENTIFIER);
    }
}
