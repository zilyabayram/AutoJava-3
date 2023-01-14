import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class AppOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }

    @AfterEach
    void tearDown () {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    void createBrowser () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Test
    void Test () {
        driver.get("http://localhost:7777 ");
        driver.findElement(By.cssSelector("[type='text']")).sendKeys("иванов иван");
        driver.findElement(By.cssSelector("[type='tel']")).sendKeys("+79179179172");
        driver.findElement(By.cssSelector("[type='checkbox']")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String actualMessage = driver.findElement(By.cssSelector(".Success_successBlock__2L3Cw")).getText();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage.strip());

    }

}
