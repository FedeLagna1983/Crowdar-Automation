package crowdar.stepdefinitions;

import crowdar.factory.DriverFactory;
import crowdar.pages.CartPage;
import crowdar.pages.InventoryItemPage;
import crowdar.pages.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

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

        Assert.assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory page is not displayed correctly"
        );

        selectedProductNameFromInventory = inventoryPage.getProductOneName();

        inventoryPage.clickProductOneName();

        inventoryItemPage = new InventoryItemPage(DriverFactory.getDriver());

        Assert.assertTrue(
                inventoryItemPage.isInventoryItemPageDisplayed(),
                "Inventory Item page is not displayed correctly"
        );

        selectedProductNameFromItemPage = inventoryItemPage.getProductName();

        Assert.assertEquals(
                selectedProductNameFromItemPage,
                selectedProductNameFromInventory,
                "Product name in Inventory Item does not match the selected product from Inventory"
        );
    }

    @And("the user adds the product to the cart from Inventory Item page")
    public void theUserAddsTheProductToTheCartFromInventoryItemPage() {
        selectedProductDescriptionFromItemPage = inventoryItemPage.getProductDescription();
        selectedProductPriceFromItemPage = inventoryItemPage.getProductPrice();

        inventoryItemPage.clickAddToCart();

        Assert.assertTrue(
                inventoryItemPage.isRemoveButtonDisplayed(),
                "Remove button is not displayed after adding the product to the cart"
        );

        Assert.assertEquals(
                inventoryItemPage.getCartBadgeText(),
                "1",
                "Cart badge does not display the expected product quantity"
        );
    }

    @And("the user opens the cart page")
    public void theUserOpensTheCartPage() {
        inventoryItemPage.clickCartIcon();

        cartPage = new CartPage(DriverFactory.getDriver());

        Assert.assertTrue(
                cartPage.isCartPageDisplayed(),
                "Cart page is not displayed correctly"
        );
    }

    @Then("the selected product should be displayed correctly in the cart")
    public void theSelectedProductShouldBeDisplayedCorrectlyInTheCart() {
        Assert.assertEquals(
                cartPage.getProductName(),
                selectedProductNameFromItemPage,
                "Product name in Cart does not match the selected product"
        );

        Assert.assertEquals(
                cartPage.getProductDescription(),
                selectedProductDescriptionFromItemPage,
                "Product description in Cart does not match the selected product"
        );

        Assert.assertEquals(
                cartPage.getProductPrice(),
                selectedProductPriceFromItemPage,
                "Product price in Cart does not match the selected product"
        );

        Assert.assertEquals(
                cartPage.getProductQuantity(),
                "1",
                "Product quantity in Cart is not correct"
        );

        Assert.assertTrue(
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
        Assert.assertTrue(
                cartPage.isCartEmpty(),
                "Cart is not empty after removing the product"
        );
    }
}