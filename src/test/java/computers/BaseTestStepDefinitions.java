package computers;

import computers.common.dataObjects.Computer;
import computers.common.pageObjects.BasePage;
import computers.common.pageObjects.ComputerDatabaseSearchPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

import static computers.common.helpers.DataFormatter.inputDateFormatToDisplayDateFormat;
import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class BaseTestStepDefinitions extends BasePage {

    private static boolean dataCleared = false;

    @Before
    public void beforeAll() {
        if(!dataCleared) {

            List<String> deleteList = Arrays.asList("TestAddComputer001",
                    "TestAddComputer002",
                    "TestAddComputer003",
                    "TestAddComputer004",
                    "TestAddComputer005",
                    "TestAddComputer006",
                    "TestDeleteComputer001",
                    "TestDeleteComputer002",
                    "TestEditComputer001",
                    "TestEditComputer002",
                    "TestEditComputer003",
                    "TestUpdatedComputer003");

            deleteComputers(deleteList);

            List<String> createList = Arrays.asList("TestDeleteComputer001",
                    "TestDeleteComputer002",
                    "TestEditComputer001",
                    "TestEditComputer002",
                    "TestEditComputer003");

            createComputers(createList);

            dataCleared = true;
        }
    }

    @Before()
    public void beforeTests() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
    }

    @After()
    public void afterTests() {
        driver.quit();
    }


    @Then("a success message {string} is displayed")
    public void a_success_message_is_displayed(String string) {
        waitUntilElementIsClickable(ComputerDatabaseSearchPage.SEARCH_BOX_IDENTIFIER);
        String successMessage = ComputerDatabaseSearchPage.get_success_message();
        assertTrue(successMessage.equals(string));
    }

    @Then("the computers are found with correct details:")
    public void the_computers_are_found_with_correct_details(io.cucumber.datatable.DataTable dataTable) {

        Computer computer = createComputerFromDataTable(dataTable);

        waitUntilElementIsClickable(ComputerDatabaseSearchPage.SEARCH_BOX_IDENTIFIER);

        ComputerDatabaseSearchPage.search_for_computer(computer.getName());
        assertEquals("One computer found", ComputerDatabaseSearchPage.get_page_header());
        assertEquals(computer.getName(), ComputerDatabaseSearchPage.get_computer_name_of_first_result());
        assertEquals(inputDateFormatToDisplayDateFormat(computer.getIntroducedDate()), ComputerDatabaseSearchPage.get_introduced_date_of_first_result());
        assertEquals(inputDateFormatToDisplayDateFormat(computer.getDiscontinuedDate()), ComputerDatabaseSearchPage.get_discontinued_date_of_first_result());

        String company;
        if (computer.getCompany() == null) {
            company = "-";
        }
        else company = computer.getCompany();

        assertEquals(company, ComputerDatabaseSearchPage.get_company_of_first_result());
    }

    private static void deleteComputers(List<String> computerNames) {
        for (String computerName : computerNames) {
            while (getComputerIdsContainingName(computerName) != null) {
                deleteComputer(getComputerIdsContainingName(computerName));
            }
        }
    }

    private static void createComputers(List<String> computerNames) {
        for (String computer : computerNames) {
            createComputer(computer);
        }
    }

    private static String getComputerIdsContainingName(String name) {
        RestAssured.baseURI = "http://computer-database.herokuapp.com";

        Response response = given().urlEncodingEnabled(true)
                .get("/computers?f=" + name)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return StringUtils.substringBetween(response.getBody().asString(), "a href=\"/computers/", "\">" + name + "</a>");
    }

    private static void deleteComputer(String computerId) {
        RestAssured.baseURI = "http://computer-database.herokuapp.com";
        given().urlEncodingEnabled(true)
                .post("/computers/" + computerId + "/delete");
    }

    private static void createComputer(String name) {
        RestAssured.baseURI = "http://computer-database.herokuapp.com";
        given().urlEncodingEnabled(true)
                .param("name", name)
                .param("introduced", "2020-01-01")
                .param("company", "2")
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post("/computers");
    }

    static void access_the_computer_database_search_page() {
        access_url(ComputerDatabaseSearchPage.COMPUTER_DATABASE_URL);
    }

    static void findAndSelectComputer(String name) {
        BaseTestStepDefinitions.access_the_computer_database_search_page();
        ComputerDatabaseSearchPage.search_for_computer(name);
        ComputerDatabaseSearchPage.select_first_result();
    }

    static Computer createComputerFromDataTable(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);

        return new Computer(rows.get(1).get(0),
                rows.get(1).get(1),
                rows.get(1).get(2),
                rows.get(1).get(3));
    };

    static void waitUntilElementIsClickable(By elementIdentifier) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(elementIdentifier));

    }
}
