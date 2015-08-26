package leaf.qa.pages;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

public class EmployeeTest extends TestBase {

	LoginPage loginpage;
	EmployeePage employeepage;

	@Parameters({ "path" })
	@BeforeMethod
	public void testInit(@Optional("") String path) {
		// Load the page in the browser
		webDriver.get(websiteUrl + path);
		loginpage = PageFactory.initElements(webDriver, LoginPage.class);
	}
	
	@Test( description = "TestRail Test")
	public void testrailFirst() throws Exception{
		JSONObject json = (JSONObject) testrail.sendGet("get_case/642");
		System.out.println(json.get("title"));
		assert webDriver
				.findElement(By.tagName("body"))
				.getText()
				.contains("Transactionss");
	}

	@Test(description = "Move to Employee List")
	public void employeeListTest() throws Exception{
		loginpage.login("meltem+master@vngrs.com", "Meltem123")
				.goToEmployeePage()
				.listEmployee();
	}

	@Features("Employee Page")
	@Parameters({"firstName","lastName","email","phone","pin","employeeType"})
	@Test(description = "Add New Employee Simple")
	public void addNewEmployeeSimple(@Optional("serhat")String firstName,
									@Optional("Bolsu")String lastName,
									@Optional("serhat@vngrs.com")String email,
									@Optional("5554443322")String phone,
									@Optional("1234")String pin,
									@Optional("MANAGER")String employeeType) throws Exception{
		loginpage.login("meltem+master@vngrs.com", "Meltem123")
				.goToEmployeePage()
				.listEmployee()
				.addUserSimple(firstName, lastName, email, pin, employeeType);
	}

	@Features("Employee Page")
	@Parameters({"firstName","lastName","email","phone","pin","employeeType",
				"checkbox","employeeCode","jobCodes"})
	@Test(description = "Add New Employee Complex")
	public void addNewEmployeeComplex(@Optional("serhat")String firstName,
									  @Optional("Bolsu")String lastName,
									  @Optional("serhat@vngrs.com")String email,
									  @Optional("5554443322")String phone,
									  @Optional("1234")String pin,
									  @Optional("Staff")String employeeType,
									  @Optional("True")Boolean seeAllTickets,
									  @Optional("")String employeeCode,
									  @Optional("DISHWASHER")String jobCode) throws Exception{
		loginpage.login("meltem+master@vngrs.com", "Meltem123")
				.goToEmployeePage()
				.listEmployee()
				.addUser(firstName,lastName,email,phone,pin,employeeType,seeAllTickets,employeeCode,jobCode);
	}


	
}
