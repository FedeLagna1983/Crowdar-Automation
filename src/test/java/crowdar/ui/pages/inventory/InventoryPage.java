package crowdar.ui.pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.cssSelector("[data-test='title']");
    private By productOneNameLink = By.id("item_4_title_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isInventoryPageDisplayed() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));

        return driver.getCurrentUrl().contains("inventory.html")
                && title.isDisplayed()
                && title.getText().equals("Products");
    }

    public String getProductOneName() {
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(productOneNameLink));
        return product.getText();
    }

    public void clickProductOneName() {
        WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(productOneNameLink));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'center'});",
                product
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                product
        );
    }
}
