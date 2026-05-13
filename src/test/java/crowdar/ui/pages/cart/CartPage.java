package crowdar.ui.pages.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    private By pageTitle = By.cssSelector("[data-test='title']");
    private By productName = By.cssSelector("[data-test='inventory-item-name']");
    private By productDescription = By.cssSelector("[data-test='inventory-item-desc']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By productQuantity = By.cssSelector("[data-test='item-quantity']");
    private By removeButton = By.id("remove-sauce-labs-backpack");
    private By cartItem = By.cssSelector("[data-test='inventory-item']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartPageDisplayed() {
        return driver.getCurrentUrl().contains("cart.html")
                && driver.findElement(pageTitle).isDisplayed()
                && driver.findElement(pageTitle).getText().equals("Your Cart");
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

    public String getProductQuantity() {
        return driver.findElement(productQuantity).getText();
    }

    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(removeButton).isDisplayed();
    }

    public void clickRemove() {
        driver.findElement(removeButton).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(cartItem).isEmpty();
    }
}
