package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Report;

public class FormPage {

	private WebDriver driver = null;
	private Report report;

	@FindBy(xpath = "//h1[@data-testid='app-name']")
	public WebElement app_name;
	@FindBy(xpath = "//h2[@data-testid='Title']")
	public WebElement form_title;
	@FindBy(xpath = "//input[@data-testid='pet']")
	public WebElement pet;
	@FindBy(xpath = "//input[@data-testid='owner']")
	public WebElement owner;
	@FindBy(xpath = "//input[@data-testid='date']")
	public WebElement date;
	@FindBy(xpath = "//input[@data-testid='time']")
	public WebElement time;
	@FindBy(xpath = "//textarea[@data-testid='symptoms']")
	public WebElement symptoms;
	@FindBy(xpath = "//button[@data-testid='btn-submit']")
	public WebElement btn_submit;

	public FormPage(WebDriver driver,Report report) {
		this.driver = driver;
		this.report = report;

		PageFactory.initElements(driver, this);
	}


	public void fillForm(String spet, String sowner, String sdate, String stime, String ssymptoms) throws Throwable {

		try {

			String apptitle = app_name.getText().trim();
			//Assert.assertTrue(apptitle.equals("APPOINTMENT MANAGEMENT"));
			if(apptitle.equals("APPOINTMENT MANAGEMENT")){
				report.testPass("Validate application title is = APPOINTMENT MANAGEMENT");
			} else {
				report.testFail("Validate application title is = APPOINTMENT MANAGEMENT");
			}

			String form_header = form_title.getText().trim();
			Assert.assertEquals("CREATE APPOINTMENT", form_header);
			pet.sendKeys(spet);
			report.testPass("Input pet name: "+spet);
			owner.sendKeys(sowner);
			report.testPass("Input owner name: "+sowner);
			date.sendKeys(sdate);
			report.testPass("Input date: "+sdate);
			//Thread.sleep(10000);
			time.sendKeys(stime);
			report.testPass("Input time: "+stime);
			symptoms.sendKeys(ssymptoms);
			report.testPass("Input symptoms: "+ssymptoms);
			report.testPass("Formulario: ", "formfill", false);

		} catch (Throwable t) {
			report.testFail("Error al llenar la información del formulario.", "fillFormError", false);
			throw t;
		}

	}

	public void submit() throws Throwable {

		try {
			btn_submit.click();
			report.testPass("Click on submit button");

		} catch (Throwable t) {
			report.testFail("Error al buscar submitir el formulario.", "submit_error", false);
			throw t;
		}

	}

	public String getTime() throws Throwable {

		try {

			return time.getAttribute("value");

		} catch (Throwable t) {
			report.testFail("Error al buscar la hora ingresada.", "time_error", false);
			throw t;
		}

	}

}
