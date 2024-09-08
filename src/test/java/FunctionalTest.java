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

import static org.testng.AssertJUnit.assertNotNull;

// FunctionalTests.java
public class FunctionalTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\91739\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();


        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.instagram.com");

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("example@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("123456");

        WebElement loginBtn = driver.findElement(By.tagName("button"));
        loginBtn.click();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void testLoginFunctionality() {

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("example@gmail.com");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("123456");

        WebElement loginBtn = driver.findElement(By.tagName("button"));
        loginBtn.click();

    }

    @Test
    public void testPostCreation() {
        // Navigate to the photo page
        WebElement photoLink = driver.findElement(By.id("photoLink"));
        photoLink.click();

        // Verify the photo is uploaded
        WebElement uploadedPhoto = driver.findElement(By.xpath("//img[contains(@src, 'photo.jpg')]"));
        assertNotNull("Photo was not uploaded", uploadedPhoto);
    }

    @Test
    public void testCommentPosting() {
        // Navigate to the photo page
        WebElement photoLink = driver.findElement(By.id("photoLink"));
        photoLink.click();

        // Post a comment
        WebElement commentField = driver.findElement(By.id("commentField"));
        commentField.sendKeys("Nice");
        WebElement postCommentButton = driver.findElement(By.id("postCommentButton"));
        postCommentButton.click();

        // Verify the comment is displayed
        WebElement commentDisplay = driver.findElement(By.xpath("//div[@class='comment' and contains(text(), 'Nice')]"));
        Assert.assertNotNull(commentDisplay, "Comment was not posted successfully.");
    }

    @Test
    public void testLikeFunctionality() {
        WebElement likeButton = driver.findElement(By.id("like-button-id")); // Find the like button
        WebElement likeCountElement = driver.findElement(By.id("like-count-id")); // Find the like count element

        String initialLikeCountText = likeCountElement.getText();
        int initialLikeCount = Integer.parseInt(initialLikeCountText);

        likeButton.click(); // Perform the like action

        // Verify that the like count has been updated
        String updatedLikeCountText = likeCountElement.getText();
        int updatedLikeCount = Integer.parseInt(updatedLikeCountText);

        Assert.assertTrue(updatedLikeCount > initialLikeCount, "Like count did not update as expected");
    }

    @Test
    public void testDirectMessaging() {
        String sender = "user1";
        String receiver = "user2";
        String messageContent = "Hello, how are you?";

        //boolean isSent = messagingService.sendMessage(sender, receiver, messageContent);
        //Assert.assertTrue(isSent, "Message should be sent successfully.");

        Assert.assertEquals(sender,receiver);
    }



    @Test
    public void testLogoutFunctionality() {
        // Perform logout
        WebElement profileMenu = driver.findElement(By.id("profileMenu"));
        WebElement logoutButton = driver.findElement(By.id("logoutButton"));

        profileMenu.click();
        logoutButton.click();

        // Verify logout
        WebElement loginPageElement = driver.findElement(By.id("loginPageElement")); // Assuming an element unique to login page
        Assert.assertTrue(loginPageElement.isDisplayed(), "Logout failed or login page did not appear.");
    }

    @Test
    public void testSearchFunctionality() {
        // Locate the search input field and enter a query
        WebElement searchBox = driver.findElement(By.id("search-input")); // Replace with actual ID or locator
        searchBox.sendKeys("test query"); // Replace with the query you want to test

        // Submit the search
        searchBox.submit();

        // Wait for results to load (you may need to adjust the wait time or use WebDriverWait)
        try {
            Thread.sleep(2000); // Simple wait, use WebDriverWait for better practice
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify the search results
        WebElement result = driver.findElement(By.id("result")); // Replace with actual result locator
        String resultText = result.getText();

        // Replace "expected result" with the actual expected result
        Assert.assertTrue(resultText.contains("expected result"), "Search result is not as expected.");

    }

    @Test
    public void testProfileUpdates() {
        // Navigate to the profile page
        WebElement profileLink = driver.findElement(By.id("profileLink"));
        profileLink.click();

        // Step 4: Update profile information
        WebElement profileEditButton = driver.findElement(By.id("editProfileButton"));
        profileEditButton.click();

        WebElement nameField = driver.findElement(By.id("name"));
        WebElement saveButton = driver.findElement(By.id("saveButton"));

        nameField.clear();
        nameField.sendKeys("New Name");
        saveButton.click();

        // Step 5: Verify the update
        WebElement updatedName = driver.findElement(By.id("profileName"));
        Assert.assertEquals(updatedName.getText(), "New Name", "Profile name update failed!");

    }

    @Test
    public void testNotificationSettings() {
        // Locate the notification element
        WebElement notificationElement = driver.findElement(By.id("notificationId")); // Replace with actual locator

        // Check if the notification is displayed
        Assert.assertTrue(notificationElement.isDisplayed(), "Notification is not displayed");

        // Check if the notification text is correct
        String expectedText = "Expected notification message";
        String actualText = notificationElement.getText();
        Assert.assertEquals(actualText, expectedText, "Notification message does not match expected text");

        // Optional: Check if notification appears within a certain timeframe
        long startTime = System.currentTimeMillis();
        while (!notificationElement.isDisplayed() && (System.currentTimeMillis() - startTime) < 5000) {
            // Wait for notification to appear
        }
        Assert.assertTrue(notificationElement.isDisplayed(), "Notification did not appear within the expected time frame");
    }
}


