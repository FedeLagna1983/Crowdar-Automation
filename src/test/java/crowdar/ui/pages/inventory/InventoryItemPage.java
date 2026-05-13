package crowdar.ui.pages.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryItemPage {

    private WebDriver driver;

    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    private By productDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By addToCartButton = By.id("add-to-cart");
    private By removeButton = By.id("remove");
    private By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public InventoryItemPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isInventoryItemPageDisplayed() {
        return driver.getCurrentUrl().contains("inventory-item.html")
                && driver.findElement(productName).isDisplayed();
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(removeButton).isDisplayed();
    }

    public String getCartBadgeText() {
        return driver.findElement(cartBadge).getText();
    }

    public void clickCartIcon() {
        driver.findElement(cartLink).click();
    }
}
