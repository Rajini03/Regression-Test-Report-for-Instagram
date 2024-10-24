import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;

// FunctionalTests.java
public class FunctionalTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\91739\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.instagram.com");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLoginWithValidCredentials() {

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("example@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("123456");

        WebElement loginBtn = driver.findElement(By.tagName("button"));
        loginBtn.click();

        // Verify login success by checking the presence of an element post-login (e.g., the homepage feed)
        assertTrue(driver.findElement(By.xpath("//div[@role='feed']")).isDisplayed());

    }

    @Test
    public void testUploadPhoto() throws InterruptedException {
        // Login first
        testLoginWithValidCredentials();

        // Navigate to the upload button
        WebElement uploadButton = driver.findElement(By.xpath("//div[@aria-label='New Post']"));
        uploadButton.click();

        // File upload (Instagram requires some interaction)
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
        fileInput.sendKeys("/path/to/your/photo.jpg");

        // Verify that the photo was uploaded successfully
        WebElement nextButton = driver.findElement(By.xpath("//button[contains(text(), 'Next')]"));
        nextButton.click();
        WebElement shareButton = driver.findElement(By.xpath("//button[contains(text(), 'Share')]"));
        shareButton.click();

        Thread.sleep(5000); // Wait for the post to be uploaded
        assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Your photo was uploaded')]")).isDisplayed());
    }

    @Test
    public void testPostComment() throws InterruptedException {
        // Login first
        testLoginWithValidCredentials();

        // Find and click on a post
        WebElement post = driver.findElement(By.xpath("//div[@role='article']"));
        post.click();

        // Add a comment
        WebElement commentBox = driver.findElement(By.xpath("//textarea[@aria-label='Add a comment…']"));
        commentBox.sendKeys("Nice photo!");
        WebElement postButton = driver.findElement(By.xpath("//button[contains(text(), 'Post')]"));
        postButton.click();

        Thread.sleep(2000); // Wait for the comment to post
        // Verify comment was posted
        assertTrue(driver.findElement(By.xpath("//span[contains(text(), 'Nice photo!')]")).isDisplayed());
    }

    @Test
    public void testLikePhoto() throws InterruptedException {
        // Login first
        testLoginWithValidCredentials();

        // Like the first post
        WebElement postLikeButton = driver.findElement(By.xpath("//span[@aria-label='Like']"));
        postLikeButton.click();

        Thread.sleep(2000); // Wait for the like action to complete
        // Verify like action
        assertTrue(driver.findElement(By.xpath("//span[@aria-label='Unlike']")).isDisplayed());
    }
}
