import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    @Test
    public void googlePageSearch() {
        WebDriver driver = getDriver("chrome");

        driver.manage().window().maximize();
        // Dimension windowSize = new Dimension(700, 500);
        // driver.manage().window().setSize(windowSize);

        driver.get("https://www.google.com");

        // JavascriptExecutor executor = (JavascriptExecutor) driver; // or ((JavascriptExecutor)driver)
        // executor.executeScript("alert('Hello')");
        // executor.executeScript("window.open('https://www.google.com',blank,height=500,width=700)");

        WebElement agreeButton =
                driver.findElement(By.xpath("//div[text()='Zaakceptuj wszystko']"));
        agreeButton.click();

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.ENTER);

        WebElement result =
                driver.findElement(By.xpath("//a[contains(@href, 'https://www.selenium.dev/')]//span"));

        Assert.assertTrue(result.isDisplayed());

        // driver.close();
        // driver.quit();
    }

    public WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(false);
                options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(options);

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            default:
                throw new InvalidArgumentException("Invalid browser name");
        }
    }
}
