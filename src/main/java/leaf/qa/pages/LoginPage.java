package leaf.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginPage extends Page {
	
	//WebElements
	@FindBy(using = "user_name")
	@CacheLookup
	private WebElement userName;

	@FindBy(using = "password")
	@CacheLookup
	private WebElement password;

	@FindBy(css = ".btn.btn-success")
	@CacheLookup
	private WebElement loginbtn;

	public LoginPage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
	}

	//Methods
	@Step
	public HomePage login(String uName, String passw){
		userName.sendKeys(uName);
		password.sendKeys(passw);
		loginbtn.click();
		return new HomePage(webDriver);
	}

}
