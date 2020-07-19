package computers;

import computers.common.dataObjects.Computer;
import computers.common.pageObjects.AddAComputerPage;
import computers.common.pageObjects.BasePage;
import computers.common.pageObjects.ComputerDatabaseSearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static computers.BaseTestStepDefinitions.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class AddComputerStepDefinitions {

    @Given("I want to add a computer")
    public void i_want_to_add_a_computer() {
        BaseTestStepDefinitions.access_the_computer_database_search_page();
        ComputerDatabaseSearchPage.select_add_a_new_computer_button();
        waitUntilElementIsClickable(AddAComputerPage.NAME_INPUT_IDENTIFIER);
    }

    @When("I add computer {string}")
    public void i_add_computer(String string) {
        Computer computer = new Computer(string, null, null, null);
        waitUntilElementIsClickable(AddAComputerPage.NAME_INPUT_IDENTIFIER);
        AddAComputerPage.add_computer(computer);
    }

    @When("I add computers:")
    public void i_add_computers(io.cucumber.datatable.DataTable dataTable) {
        Computer computer = createComputerFromDataTable(dataTable);
        waitUntilElementIsClickable(AddAComputerPage.NAME_INPUT_IDENTIFIER);
        AddAComputerPage.add_computer(computer);
    }

    @When("I cancel the add computer flow")
    public void i_cancel_the_add_computer_flow() {
        AddAComputerPage.select_cancel_button();
    }

    //TODO make method generic for different pages
    @Then("I am on the Add a computer page")
    public void i_am_on_the_add_a_computer_page() {
        assertEquals(AddAComputerPage.PAGE_HEADING, BasePage.get_page_header());
    }

    //TODO make method generic for different pages
    @Then("I am on the Computer database search page")
    public void i_am_on_the_computer_database_search_page() {
        assertTrue(BasePage.get_page_header().contains(ComputerDatabaseSearchPage.PARTIAL_PAGE_HEADING));
    }

}
