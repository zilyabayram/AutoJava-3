import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class AppOrderTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
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
    void shouldTestElements () {
        driver.get("http://localhost:7777");
        driver.findElement(By.cssSelector("[data-test-id= 'name'] input")).sendKeys("иванов иван");
        driver.findElement(By.cssSelector("[data-test-id= 'phone'] input")).sendKeys("+79179179172");
        driver.findElement(By.cssSelector("[data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String actualMessage = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        String expectedMessage = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expectedMessage, actualMessage.strip());

    }

    @AfterEach
    void tearDown () {
        driver.quit();
        driver = null;
    }

}
