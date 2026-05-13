package crowdar.ui.stepdefinitions;

import crowdar.core.driver.DriverFactory;
import crowdar.ui.pages.cart.CartPage;
import crowdar.ui.pages.inventory.InventoryItemPage;
import crowdar.ui.pages.inventory.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CartSteps {

    private InventoryPage inventoryPage;
    private InventoryItemPage inventoryItemPage;
    private CartPage cartPage;

    private String selectedProductNameFromInventory;
    private String selectedProductNameFromItemPage;
    private String selectedProductDescriptionFromItemPage;
    private String selectedProductPriceFromItemPage;

    @And("the user opens the first product detail from Inventory")
    public void theUserOpensTheFirstProductDetailFromInventory() {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());

        Assertions.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed correctly"
        );

        selectedProductNameFromInventory = inventoryPage.getProductOneName();

        inventoryPage.clickProductOneName();

        inventoryItemPage = new InventoryItemPage(DriverFactory.getDriver());

        Assertions.assertTrue(
                inventoryItemPage.isInventoryItemPageDisplayed(),
                "Inventory Item page is not displayed correctly"
        );

        selectedProductNameFromItemPage = inventoryItemPage.getProductName();

        Assertions.assertEquals(
                selectedProductNameFromInventory,
                selectedProductNameFromItemPage,
                "Product name in Inventory Item does not match the selected product from Inventory"
        );
    }

    @And("the user adds the product to the cart from Inventory Item page")
    public void theUserAddsTheProductToTheCartFromInventoryItemPage() {
        selectedProductDescriptionFromItemPage = inventoryItemPage.getProductDescription();
        selectedProductPriceFromItemPage = inventoryItemPage.getProductPrice();

        inventoryItemPage.clickAddToCart();

        Assertions.assertTrue(
                inventoryItemPage.isRemoveButtonDisplayed(),
                "Remove button is not displayed after adding the product to the cart"
        );

        Assertions.assertEquals(
                "1",
                inventoryItemPage.getCartBadgeText(),
                "Cart badge does not display the expected product quantity"
        );
    }

    @And("the user opens the cart page")
    public void theUserOpensTheCartPage() {
        inventoryItemPage.clickCartIcon();

        cartPage = new CartPage(DriverFactory.getDriver());

        Assertions.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page is not displayed correctly"
        );
    }

    @Then("the selected product should be displayed correctly in the cart")
    public void theSelectedProductShouldBeDisplayedCorrectlyInTheCart() {
        Assertions.assertEquals(
                selectedProductNameFromItemPage,
                cartPage.getProductName(),
                "Product name in Cart does not match the selected product"
        );

        Assertions.assertEquals(
                selectedProductDescriptionFromItemPage,
                cartPage.getProductDescription(),
                "Product description in Cart does not match the selected product"
        );

        Assertions.assertEquals(
                selectedProductPriceFromItemPage,
                cartPage.getProductPrice(),
                "Product price in Cart does not match the selected product"
        );

        Assertions.assertEquals(
                "1",
                cartPage.getProductQuantity(),
                "Product quantity in Cart is not correct"
        );

        Assertions.assertTrue(
                cartPage.isRemoveButtonDisplayed(),
                "Remove button is not displayed in Cart"
        );
    }

    @When("the user removes the product from the cart")
    public void theUserRemovesTheProductFromTheCart() {
        cartPage.clickRemove();
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        Assertions.assertTrue(
                cartPage.isCartEmpty(),
                "Cart is not empty after removing the product"
        );
    }
}
