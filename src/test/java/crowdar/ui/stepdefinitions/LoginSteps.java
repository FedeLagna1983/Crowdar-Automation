package crowdar.ui.stepdefinitions;

import crowdar.core.config.ConfigReader;
import crowdar.core.driver.DriverFactory;
import crowdar.ui.pages.inventory.InventoryPage;
import crowdar.ui.pages.login.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("the user opens the SauceDemo login page")
    public void theUserOpensTheSauceDemoLoginPage() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
        loginPage = new LoginPage(DriverFactory.getDriver());
        Assertions.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed correctly");
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the Inventory page should be displayed")
    public void theInventoryPageShouldBeDisplayed() {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        Assertions.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page is not displayed correctly");
    }

    @Then("the login error message should be displayed")
    public void theLoginErrorMessageShouldBeDisplayed() {
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed(), "Login error message is not displayed");
        Assertions.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message text is not correct"
        );
    }

    @Then("the login error message should fail intentionally")
    public void theLoginErrorMessageShouldFailIntentionally() {

        Assertions.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Login error message is not displayed"
        );

        Assertions.assertTrue(
                loginPage.getErrorMessage().contains("Login successful"),
                "Intentional failure: this assertion is expected to fail in order to validate screenshots and reports"
        );
    }
}
