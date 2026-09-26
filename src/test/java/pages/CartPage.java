package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;
    private By header= By.cssSelector("[data-test='title']");

    public CartPage(WebDriver driver){
        this.driver=driver;
    }

    public String getHeaderText(){
        return driver.findElement(header).getText();
    }
}
