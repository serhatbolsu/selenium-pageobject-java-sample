package leaf.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.allure.annotations.Step;

/*
 * Sample page
 *
 */
public class HomePage extends Page {
	
	@FindBy(using = "nav-item-staff")
	@CacheLookup
	private WebElement employeeIcon;
	
	@FindBy(css = "li.nav-item-staff li.submenu-item-staff")
	@CacheLookup
	private WebElement employeeList;
	
	@FindBy(css = ".nav-item-utilities a")
	private WebElement settingsNav;

	@FindBy(id = "content_nav_item_2")
	private WebElement leftNavigationElementList;

	public HomePage(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(webDriver, this);
	}

	@Step
	public EmployeePage goToEmployeePage () throws Exception{
//		Actions action = new Actions(webDriver);
		// precaution for moveTo not to fail, do it seperately.
//		action.moveToElement(employeeIcon).perform();
//		action.moveToElement(employeeList).click(employeeList).perform();
		employeeIcon.click();
		return new EmployeePage(webDriver);
	}

}
