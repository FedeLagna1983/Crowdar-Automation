package crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By errorCloseButton = By.cssSelector("[data-test='error-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(usernameInput).isDisplayed()
                && driver.findElement(passwordInput).isDisplayed()
                && driver.findElement(loginButton).isDisplayed();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void closeErrorMessage() {
        driver.findElement(errorCloseButton).click();
    }
}