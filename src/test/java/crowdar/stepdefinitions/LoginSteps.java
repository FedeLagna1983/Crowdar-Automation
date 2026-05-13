package crowdar.stepdefinitions;

import crowdar.factory.DriverFactory;
import crowdar.pages.InventoryPage;
import crowdar.pages.LoginPage;
import crowdar.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("the user opens the SauceDemo login page")
    public void theUserOpensTheSauceDemoLoginPage() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("baseUrl"));
        loginPage = new LoginPage(DriverFactory.getDriver());
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Login page is not displayed correctly");
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the Inventory page should be displayed")
    public void theInventoryPageShouldBeDisplayed() {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page is not displayed correctly");
    }

    @Then("the login error message should be displayed")
    public void theLoginErrorMessageShouldBeDisplayed() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Login error message is not displayed");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Error message text is not correct"
        );
    }

    @Then("the login error message should fail intentionally")
    public void theLoginErrorMessageShouldFailIntentionally() {

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Login error message is not displayed"
        );

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Login successful"),
                "Intentional failure: this assertion is expected to fail in order to validate screenshots and reports"
        );
    }
}