import com.microsoft.playwright.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

public class test {

    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeTest
    public void setup() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(100));

        context = browser.newContext();

        page = context.newPage();
    }

    @Test
    public void registerUser() {
page.navigate("https://paywint-qa.netlify.app/app/login.html");
page.locator("#username").fill("student");
page.locator("#password").fill("Password123");
page.locator("#loginBtn").click();
assertThat(page).hasTitle("Welcome, student!");


    }
    @Test
    public void loginempty(){
        page.navigate("https://paywint-qa.netlify.app/app/login.html");
        page.locator("#loginBtn").click();
        assertThat(page.locator("#err-password")).hasText("password are required");
        assertThat(page.locator("err-username")).hasText("username are required");
    }
    @Test
    public void Loginwrongpassword(){
        page.navigate("https://paywint-qa.netlify.app/app/login.html");
        page.locator("#username").fill("student");
        page.locator("#password").fill("Password12");
        page.locator("#loginBtn").click();
        assertThat(page.locator("#err-password")).isVisible();

    }
    @Test
    public void logout01(){
        for (int i=0; i<3; i++){
            page.navigate("https://paywint-qa.netlify.app/app/login.html");
            page.locator("#username").fill("student");
            page.locator("#password").fill("Password12");
            page.locator("#loginBtn").click();
        }
        assertThat(page.locator("#err-password")).hasText("locked");
    }
    @Test
    public void dashboard(){
        page.navigate("https://paywint-qa.netlify.app/app/login.html");
        page.locator("#username").fill("student");
        page.locator("#password").fill("Password123");
        page.locator("#loginBtn").click();
        int a = page.locator("#tbody").count()-1;
        assertEquals(5,a);
    }
    @Test
    public void search(){
        page.navigate("https://paywint-qa.netlify.app/app/login.html");
        page.locator("#username").fill("student");
        page.locator("#password").fill("Password123");
        page.locator("#loginBtn").click();
        page.locator("#search").fill("keyboard");
        assertThat(page.locator("Mechanical Keyboard")).isVisible();
    }

@Test
    public void add(){
    page.navigate("https://paywint-qa.netlify.app/app/login.html");
    page.locator("#username").fill("student");
    page.locator("#password").fill("Password123");
    page.locator("#loginBtn").click();
    page.locator("#newProduct").fill("webcam");
    page.locator("#addBtn").click();
}
@Test
    public void logout(){
    page.navigate("https://paywint-qa.netlify.app/app/login.html");
    page.locator("#username").fill("student");
    page.locator("#password").fill("Password123");
    page.locator("#loginBtn").click();
    page.locator("#logoutBtn").click();
    assertThat(page.locator("#loginBtn")).isVisible();

}

    @AfterTest
    public void teardown() {

        if (page != null)
            page.close();

        if (context != null)
            context.close();

        if (browser != null)
            browser.close();

        if (playwright != null)
            playwright.close();
    }
}

