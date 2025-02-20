package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    // same setup as HomePageFunctionalTest
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    // checks if product page elements are present and displayed
    @Test
    void testCreateProductPage(ChromeDriver driver) {
        // open create product page
        driver.get(baseUrl + "/product/create");

        // verify page title
        String pageTitle = driver.findElement(By.tagName("h3")).getText();
        assertEquals("Create New Product", pageTitle);

        // verify form elements exist
        assertTrue(driver.findElement(By.id("nameInput")).isDisplayed());
        assertTrue(driver.findElement(By.id("quantityInput")).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector("button[type='submit']")).isDisplayed());
    }

    // checks for creating a single product
    @Test
    void testCreateProductSuccess(ChromeDriver driver) {

        driver.get(baseUrl + "/product/create");

        // find input elements
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // fill form
        nameInput.sendKeys("Test Product");
        quantityInput.sendKeys("100");
        submitButton.click();

        // verify redirect to product list page
        assertEquals(baseUrl + "/product/list", driver.getCurrentUrl());

        // checks if product exist in product list
        assertTrue(driver.getPageSource().contains("Test Product"));
    }

    // tests empty name input
    @Test
    void testCreateProductEmptyName(ChromeDriver driver) {

        driver.get(baseUrl + "/product/create");


        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // submit without name
        quantityInput.sendKeys("100");
        submitButton.click();

        // verify we stay on create product page
        assertTrue(driver.getCurrentUrl().contains("/product/create"));
    }

    // tests negative input
    @Test
    void testCreateProductInvalidQuantity(ChromeDriver driver) {

        driver.get(baseUrl + "/product/create");

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Test Product");
        // use -1 for example
        quantityInput.sendKeys("-1");
        submitButton.click();

        // verify we stay on create product page
        assertTrue(driver.getCurrentUrl().contains("/product/create"));
    }

    @Test
    void testCreateMultipleProducts(ChromeDriver driver) {
        // create first product
        driver.get(baseUrl + "/product/create");
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Product 1");
        quantityInput.sendKeys("10");
        submitButton.click();

        // create second product
        driver.get(baseUrl + "/product/create");
        nameInput = driver.findElement(By.id("nameInput"));
        quantityInput = driver.findElement(By.id("quantityInput"));
        submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        nameInput.sendKeys("Product 2");
        quantityInput.sendKeys("20");
        submitButton.click();

        // check if both products appear in product list page
        String pageContent = driver.getPageSource();
        assertTrue(pageContent.contains("Product 1"));
        assertTrue(pageContent.contains("Product 2"));
    }
}