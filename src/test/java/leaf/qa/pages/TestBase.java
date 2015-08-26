package leaf.qa.pages;

import leaf.qa.keywords.Screenshot;
import leaf.qa.util.Browser;
import leaf.qa.util.PropertyLoader;
import leaf.qa.util.testrail.APIClient;
import leaf.qa.util.testrail.APIException;
import leaf.qa.webdriver.WebDriverFactory;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase extends TestListenerAdapter{

	protected WebDriver webDriver;
	protected String gridHubUrl;
	protected String websiteUrl;
	protected Browser browser;
	protected APIClient testrail;

	@BeforeClass
	public void init() {
		websiteUrl = PropertyLoader.loadProperty("site.url");
		gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

		browser = new Browser();
		browser.setName(PropertyLoader.loadProperty("browser.name"));
		browser.setVersion(PropertyLoader.loadProperty("browser.version"));
		browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

		String username = PropertyLoader.loadProperty("user.username");
		String password = PropertyLoader.loadProperty("user.password");

		webDriver = WebDriverFactory.getInstance(gridHubUrl, browser, username,
				password);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		
		testrail = new APIClient("https://leaf.testrail.com/");
		testrail.setUser("serhat@vngrs.com");
		testrail.setPassword("23672367");

//		EventFiringWebDriver driver = new EventFiringWebDriver(webDriver);
//		WebDriverEventListener errorListener = new AbstractWebDriverEventListener() {
//			@Override
//			public void onException(Throwable throwable, WebDriver webDriver) {
//				screenshot.capturePageScreenshot("isim");
//			}
//		};
//		driver.register(errorListener);
//		webDriver = driver;

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
		if (webDriver != null) {
			webDriver.quit();
		}
	}


	@Step("Test-NG Listener")
	@Override
	public void onTestFailure(ITestResult testResult) {
		System.out.println("-- Failure Handler --");
		setScreenshot(testResult);
//		postTestResult(testResult);
	}
	

	public void setScreenshot(ITestResult result) {
			Screenshot screenshot = new Screenshot(webDriver);
			screenshot.capturePageScreenshot(result.getTestName());
	}
	
	public void postTestResult(ITestResult result){
		Map data = new HashMap();
		data.put("status_id", new Integer(1)); // Failed Status
		data.put("comment", "This test worked fine!");
		JSONObject r = null;
		try {
			r = (JSONObject) testrail.sendPost("add_result_for_case/20/591", data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
		System.out.println(r.toString());
	}
}
