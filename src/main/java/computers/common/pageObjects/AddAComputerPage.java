package computers.common.pageObjects;

import computers.common.dataObjects.Computer;
import org.openqa.selenium.By;

public class AddAComputerPage extends BasePage {

    public static final String PAGE_HEADING = "Add a computer";
    public static final By NAME_INPUT_IDENTIFIER = By.id("name");
    private static final By INTRODUCED_DATE_INPUT_IDENTIFIER = By.id("introduced");
    private static final By DISCONTINUED_DATE_INPUT_IDENTIFIER = By.id("discontinued");
    private static final By COMPANY_DROPDOWN_IDENTIFIER = By.id("company");
    private static final By CREATE_THIS_COMPUTER_BUTTON_IDENTIFIER = By.cssSelector("#main > form > div > input");

    public static void add_computer(Computer computer) {
        if (computer.getName() != null) enter_computer_name(computer.getName());
        if (computer.getIntroducedDate() != null) enter_introduced_date(computer.getIntroducedDate());
        if (computer.getDiscontinuedDate() != null) enter_discontinued_date(computer.getDiscontinuedDate());
        if (computer.getCompany() != null) select_company(computer.getCompany());
        select_create_this_computer_button();
    }

    private static void enter_computer_name(String name) {
        enter_text(NAME_INPUT_IDENTIFIER, name);
    }

    private static void enter_introduced_date(String introducedDate) {
        enter_text(INTRODUCED_DATE_INPUT_IDENTIFIER, introducedDate);
    }

    private static void enter_discontinued_date(String discontinuedDate) {
        enter_text(DISCONTINUED_DATE_INPUT_IDENTIFIER, discontinuedDate);
    }

    private static void select_company(String company) {
        select_from_dropdown(COMPANY_DROPDOWN_IDENTIFIER, company);
    }

    private static void select_create_this_computer_button() {
        select_element(CREATE_THIS_COMPUTER_BUTTON_IDENTIFIER);
    }
}
