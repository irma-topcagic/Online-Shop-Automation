## Online Shop – Selenium Tests

UI tests for the [SauceDemo](https://www.saucedemo.com) online shop, written with Selenium WebDriver, Java and TestNG.

This was my first test automation project. Later I rebuilt it with Playwright and JavaScript and extended it with the full checkout flow: [Online-Shop-Playwright](https://github.com/irma-topcagic/Online-Shop-Playwright).

### What is tested

- **Login** – successful login and error messages for a wrong password, empty username, empty password and a locked out user
- **Products** – page title, adding one or more products to the cart, cart badge, removing products and an empty cart
- **Sorting** – by name (A–Z, Z–A) and by price (low to high, high to low)
- **Cart** – opening the cart page from the products page

### How the project is organized

Every page has its own class in the `pages` package (Page Object Model), with locators and methods for actions on that page. Methods that open another page return that page's object (page chaining), for example `openCart()` returns `CartPage`.

`BaseTest` opens the browser before each test and closes it after, so every test runs independently.

Login tests go through the login form, while product tests log in by setting the session cookie directly, which makes them faster and keeps them focused on the products page.

Sorting tests compare the list shown on the page with a sorted copy of it. Prices are converted to numbers first, so they are sorted correctly.

The locked out user test uses `SoftAssert` to check both the error message and that the user stays on the login page.

### Project structure

    src/test/java
      base/     BaseTest
      pages/    LoginPage, ProductsPage, CartPage
      tests/    LoginTest, ProductsTest

### How to run

Requirements: Java 21, Maven and Google Chrome.

    mvn test

Tests can also be run from IntelliJ IDEA by right-clicking a test class and choosing **Run**.

## Tools

Java, Selenium WebDriver, TestNG, Maven
