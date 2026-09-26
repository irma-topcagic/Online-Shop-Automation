package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private By header = By.cssSelector("[data-test='title']");
    private By sortDropDown=By.cssSelector("[data-test='product-sort-container']");
    private By productNames = By.cssSelector("[data-test='inventory-item-name']");
    private By productPrices=By.cssSelector("[data-test='inventory-item-price']");
    private By cartIcon=By.cssSelector("[data-test='shopping-cart-link']");
    private By cartBadge=By.cssSelector("[data-test='shopping-cart-badge']");

    public ProductsPage(WebDriver driver){
        this.driver=driver;
    }

    public void sort(String value){
        Select select= new Select(driver.findElement(sortDropDown));
        select.selectByValue(value);
    }

    public String getHeaderText() {
        return driver.findElement(header).getText();
    }

    public List<String> getProductNames(){
        List<WebElement> elements=driver.findElements(productNames);
        List<String> names=new ArrayList<>();
        for(WebElement element:elements){
            names.add(element.getText());
        }
        return names;
    }

    public List<Double> getProductPrices(){
        List<WebElement> elements=driver.findElements(productPrices);
        List<Double> prices=new ArrayList<>();
        for (WebElement element : elements) {
            prices.add(Double.parseDouble(element.getText().replace("$", "")));
        }

        return prices;
    }

    public void addProductToCart(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(driver.findElement(cartBadge).getText());
    }

    public boolean isCartBadgeDisplayed() {
        return !driver.findElements(cartBadge).isEmpty();
    }

    public CartPage openCart() {
        driver.findElement(cartIcon).click();
        return new CartPage(driver);
    }

    public void removeProductFromCart(String productName){
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();

    }
}
