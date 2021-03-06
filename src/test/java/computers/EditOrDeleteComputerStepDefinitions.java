package computers;

import computers.common.dataObjects.Computer;
import computers.common.pageObjects.ComputerDatabaseSearchPage;
import computers.common.pageObjects.EditComputerPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static computers.BaseTestStepDefinitions.*;
import static junit.framework.TestCase.assertEquals;

public class EditOrDeleteComputerStepDefinitions {

    @Given("I want to edit or delete computer {string}")
    public void i_want_to_edit_or_delete_computer(String string) {
        BaseTestStepDefinitions.findAndSelectComputer(string);
    }

    @When("I delete the computer")
    public void i_delete_the_computer() {
        EditComputerPage.select_delete_this_computer();
    }

    @When("I edit the computer")
    public void i_edit_the_computer() {
        EditComputerPage.select_save_computer_button();
    }

    @When("I edit the computers:")
    public void i_edit_computers(io.cucumber.datatable.DataTable dataTable) {
        Computer computer = createComputerFromDataTable(dataTable);
        EditComputerPage.updateComputer(computer);
    }

    @Then("the computer {string} is not found in search")
    public void the_computer_is_not_found_in_search(String string) {
        ComputerDatabaseSearchPage.search_for_computer(string);
        assertEquals(ComputerDatabaseSearchPage.NO_COMPUTERS_FOUND_PAGE_HEADING, ComputerDatabaseSearchPage.get_page_header());
    }
}
