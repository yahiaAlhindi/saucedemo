package test10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class StudentTableTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        try {
            System.out.println("🚀 Launching Chrome browser...");

            driver = new ChromeDriver();
            driver.manage().window().maximize();

            String url = "http://127.0.0.1:5500/index.html"; // Make sure Live Server is running
            driver.get(url);

            System.out.println("✅ Page loaded: " + driver.getTitle());

        } catch (Exception e) {
            System.out.println("❌ Failed to start test: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void printPassColumnValues() {
        System.out.println("\n🔍 Test 1: All 'Pass' column values:");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".pass-cell")));

            List<WebElement> passCells = driver.findElements(By.cssSelector(".pass-cell"));

            if (passCells.isEmpty()) {
                System.out.println("⚠️ No 'Pass' column data found!");
            } else {
                for (WebElement cell : passCells) {
                    System.out.println("Pass: " + cell.getText());
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error in printPassColumnValues: " + e.getMessage());
        }
    }

    @Test
    public void printRowsWherePassIsYes() {
        System.out.println("\n✅ Test 2: Rows where 'Pass' = Yes:");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#table-body tr")));

            List<WebElement> rows = driver.findElements(By.cssSelector("#table-body tr"));

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (!cells.isEmpty()) {
                    String passValue = cells.get(3).getText();
                    if (passValue.equalsIgnoreCase("Yes")) {
                        for (WebElement cell : cells) {
                            System.out.print(cell.getText() + " | ");
                        }
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error in printRowsWherePassIsYes: " + e.getMessage());
        }
    }

    @Test
    public void printPassValuesAndRowsWithYes() {
        System.out.println("\n🔍 Test: Print all 'Pass' values and rows where 'Pass' = Yes");

        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#table-body tr")));

            List<WebElement> rows = driver.findElements(By.cssSelector("#table-body tr"));

            System.out.println("\n📋 All 'Pass' Column Values:");
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (!cells.isEmpty()) {
                    String passValue = cells.get(3).getText();
                    System.out.println(passValue);
                }
            }

            System.out.println("\n✅ Rows where 'Pass' = Yes:");
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (!cells.isEmpty()) {
                    String passValue = cells.get(3).getText();
                    if (passValue.equalsIgnoreCase("Yes")) {
                        for (WebElement cell : cells) {
                            System.out.print(cell.getText() + " | ");
                        }
                        System.out.println();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("\n🛑 Closing browser...");
        if (driver != null) {
            driver.quit();
        }
    }
}
