package auto1.com.qa_selenium_java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class searchFunctionalityTest {

    private WebDriver driver;
    private Properties properties = new Properties();

    @BeforeTest
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\QATest\\chromedriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);

        properties.load(new FileReader(new File("../test.properties")));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchReturnsCarsFilteredAndSortedPriceDesc() {

        WebDriverWait wait = new WebDriverWait(driver, Long.parseLong(properties.getProperty("explicitWait")));

        driver.get(properties.getProperty("aut_URL"));
        driver.findElement(By.cssSelector("div.root___1ZGR8:nth-child(3)")).click();
        WebElement mySelectElement = driver.findElement(By.name("yearRange.min"));
        Select dropdown = new Select(mySelectElement);
        dropdown.selectByVisibleText(properties.getProperty("regYearToFilter"));

        WebElement mySelectElement1 = driver.findElement(By.name("sort"));
        Select dropdown1 = new Select(mySelectElement1);
        dropdown1.selectByVisibleText("Höchster Preis");

        // Extracts the prices from the price elements and stores in a List
        WebElement oldHtml = driver.findElement(By.className("img___2IN-o"));
        wait.until(ExpectedConditions.invisibilityOf(oldHtml));
        List<WebElement> priceElements = driver.findElements(By.className("totalPrice___3yfNv"));
        List<String> prices = new ArrayList<>();
        for (WebElement price : priceElements) {
            prices.add(price.getText().split(" ")[0]);
        }

        // Extracts the registration years from the car specs elements and stores in a List
        List<WebElement> regDateElements = driver.findElements(By.className("specList___2i0rY"));
        List<String> regYears = new ArrayList<>();
        for (WebElement regYear : regDateElements) {
            regYears.add(regYear.getText().substring(5, 9));
        }

        /* Verifies all cars are sorted by price descending */
        Assert.assertEquals(prices, isSortedDesc(prices));

        /* Verifies all cars are filtered by first registration ( 2015+ ) */
        Assert.assertTrue(isFilteredByFirstReg(regYears));
    }

    /**method verifies that a list contains years 2015+
     *
     * @param regYearsToFilter the year to start filter from
     * @return a boolean value - true/false
     */
    private boolean isFilteredByFirstReg(List<String> regYearsToFilter) {
        for (int i = 0; i < regYearsToFilter.size(); i++) {
            if (Integer.valueOf(regYearsToFilter.get(i)) < Integer.parseInt(properties
                    .getProperty("regYearToFilter"))) {
                return false;
            }
        }
        return true;
    }

    /**function sorts a list in descending order.
     *
     * @param listToSort the list to be sorted
     * @return a sorted list
     */
    private List<String> isSortedDesc(List<String> listToSort) {
        List<String> sortedList = new ArrayList<>(listToSort);
        sortedList.sort(Collections.reverseOrder());
        return sortedList;
    }

}