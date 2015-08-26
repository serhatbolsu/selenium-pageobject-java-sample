package leaf.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

public class EmployeePage extends Page {

	//WebElements
	@FindBy(className = "app-frame")
	private WebElement applicationFrame;

	@FindBy(id = "content_nav_item_2")
	private WebElement leftNavigationElementList;
	
	@FindBy(id = "add_user")
	private WebElement addUser;
	
	@FindBy(id = "item_list_hd")
	private WebElement allEmployeesTitle;
	
	@FindBy(id = "item_detail_hd")
	private WebElement userTitle;
	
	@FindBy(id = "user_detail_first")
	private WebElement firstName;
	
	@FindBy(id = "user_detail_last")
	private WebElement lastName;
	
	@FindBy(id = "user_detail_email")
	private WebElement userEmail;
	
	@FindBy(id = "user_detail_phone")
	private WebElement userPhone;
	
	@FindBy(id = "user_detail_pin")
	private WebElement userPin;

	@FindBy(id = "user_detail_staff_type")
	private WebElement userEmployeeType;

	@FindBy(id = "user_detail_see_all_tickets")
	private WebElement userSeeTicketCheckbox;

	@FindBy(id = "user_detail_merchant_code")
	private WebElement userMerchantCode;

	@FindBy(id = "s2id_user_detail_jobcodes")
	private WebElement userJobCodesSelector;
	
	@FindBy(id = "user_detail_save")
	private WebElement userDetailSave;

	public EmployeePage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("app-frame")));

	}

	@Step
	public EmployeePage	listEmployee() throws Exception{
		Thread.sleep(1000);
		webDriver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOf(leftNavigationElementList));
		leftNavigationElementList.click();
		Thread.sleep(1000);
		webDriver.switchTo().frame(webDriver.findElement(By.className("app-frame")));
		wait.until(ExpectedConditions.visibilityOf(allEmployeesTitle));
		webDriver.switchTo().defaultContent();
		return this;
	}

	@Step
	public EmployeePage addUserSimple(String uFname, String uLname, String uEmail, String uPin,
									  String uEmployeeType) throws Exception {
		webDriver.switchTo().frame(webDriver.findElement(By.className("app-frame")));
		System.out.println("--Adding Employee--");
		addUser.click();
		firstName.sendKeys(uFname);
		lastName.sendKeys(uLname);
		userEmail.sendKeys(uEmail);
		userPhone.sendKeys("5554443322");
		userPin.sendKeys(uPin);
		if (uEmployeeType.toLowerCase().equals("staff")){
			Select staffSelection = new Select(userEmployeeType);
			staffSelection.selectByValue("server");
		}else if (uEmployeeType.toLowerCase().equals("manager")){
			Select staffSelection = new Select(userEmployeeType);
			staffSelection.selectByValue("manager");
		}else throw new Exception("Failed to select Employee Type");
		userDetailSave.click();	
		Thread.sleep(1000);
		screenshot.capturePageScreenshot();
		Assert.assertEquals(false,userTitle.isDisplayed());
		return this;
	}

	@Step
	public EmployeePage addUser(String uFname, String uLname, String uEmail, String uPhone, 
								String uPin, String uEmployeeType, Boolean uTicketCheckbox,
								String uMerchantCode, String uJobCode) throws Exception {
		webDriver.switchTo().frame(webDriver.findElement(By.className("app-frame")));
		System.out.println("--Adding Employee--");
		addUser.click();
		firstName.sendKeys(uFname);
		lastName.sendKeys(uLname);
		userEmail.sendKeys(uEmail);
		userPhone.sendKeys(uPhone);
		userPin.sendKeys(uPin);
		if (uEmployeeType.toLowerCase().equals("staff")){
			Select staffSelection = new Select(userEmployeeType);
			staffSelection.selectByValue("server");
		}else if (uEmployeeType.toLowerCase().equals("manager")){
			Select staffSelection = new Select(userEmployeeType);
			staffSelection.selectByValue("manager");
		}else throw new Exception("Failed to select Employee Type");
		if (uTicketCheckbox) {
			if (!userSeeTicketCheckbox.isSelected()) {
				userSeeTicketCheckbox.click();
			}
		}
		userMerchantCode.sendKeys(uMerchantCode);
		userJobCodesSelector.click();
		String index = "";
		switch (uJobCode.toLowerCase()) {
			case "dishwasher": index="1"; break;
			case "kali": index="2"; break;
			case "runner": index="3"; break;
			case "chef": index="4"; break;
			case "cashier": index="5"; break;
			default: throw new Exception("Could not select Job Code");
		}
		String selection = String.format("ul.select2-results li:nth-of-type(%s)",index);
		webDriver.findElement(By.cssSelector(selection)).click();
		userDetailSave.click();
		Thread.sleep(1000);
		screenshot.capturePageScreenshot("Custom.png");
		Assert.assertEquals(false,userTitle.isDisplayed());
		return this;
	}


	
	
}
