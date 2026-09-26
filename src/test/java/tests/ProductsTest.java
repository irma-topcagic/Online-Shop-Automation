package tests;

import base.BaseTest;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsTest extends BaseTest {
    private ProductsPage productsPage;
    @BeforeMethod
    public void loginWithCookie() {
        driver.manage().addCookie(new Cookie("session-username", "standard_user"));
        driver.get("https://www.saucedemo.com/inventory.html");
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void validHeader(){
        String header=productsPage.getHeaderText();
        Assert.assertEquals(header,"Products");
    }

    @Test
    public void addSingleProductToCart(){
        productsPage.addProductToCart("Sauce Labs Backpack");
        int numberOfItems=productsPage.getCartBadgeCount();
        Assert.assertEquals(numberOfItems,1);
    }

    @Test
    public void addProductsToCart(){
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.addProductToCart("Sauce Labs Onesie");
        Assert.assertEquals(productsPage.getCartBadgeCount(),3);
    }

    @Test
    public void emptyCartHasNoBadge(){
        Assert.assertFalse(productsPage.isCartBadgeDisplayed());
    }

    @Test
    public void removeProductFromCart(){
        productsPage.addProductToCart("Sauce Labs Backpack");
        productsPage.removeProductFromCart("Sauce Labs Backpack");
        Assert.assertFalse(productsPage.isCartBadgeDisplayed());
    }

    @Test
    public void sortByNameAToZ(){
        productsPage.sort("az");
        List<String> actualNames = productsPage.getProductNames();
        List<String>expectedNames=new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        Assert.assertEquals(actualNames,expectedNames);
    }

    @Test
    public void sortByNameZToA(){
        productsPage.sort("za");
        List<String> actualNames = productsPage.getProductNames();
        List<String>expectedNames=new ArrayList<>(actualNames);
        expectedNames.sort(Collections.reverseOrder());

        Assert.assertEquals(actualNames,expectedNames);
    }

    @Test
    public void sortByPriceLowToHigh() {
        productsPage.sort("lohi");

        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices);
    }

    @Test
    public void sortByPriceHighToLow() {
        productsPage.sort("hilo");

        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        expectedPrices.sort(Collections.reverseOrder());

        Assert.assertEquals(actualPrices, expectedPrices);
    }

    @Test
    public void openCartPage() {
        CartPage cartPage = productsPage.openCart();
        Assert.assertEquals(cartPage.getHeaderText(), "Your Cart");
    }
}
