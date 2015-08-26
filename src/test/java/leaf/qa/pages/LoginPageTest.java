package leaf.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Issue;
import ru.yandex.qatools.allure.annotations.TestCaseId;


public class LoginPageTest extends TestBase {

	LoginPage loginpage;
	
	@Parameters({ "path" })
	@BeforeClass
	public void testInit(@Optional("/") String path) {
		// Load the page in the browser
		webDriver.get(websiteUrl + path);
		loginpage = PageFactory.initElements(webDriver, LoginPage.class);
	}

	@Features("Login Page")
	@TestCaseId("642")
	@Issue("642")
	@Parameters({"username","password"})
	@Test(description = "Login with valid credentails")
	public void loginTest(@Optional("meltem+master@vngrs.com") String username,
						  @Optional("Meltem123") String password) {
		loginpage.login(username, password);

		assert webDriver
				.findElement(By.tagName("body"))
				.getText()
				.contains("Transactions");
		
	}
	
}
