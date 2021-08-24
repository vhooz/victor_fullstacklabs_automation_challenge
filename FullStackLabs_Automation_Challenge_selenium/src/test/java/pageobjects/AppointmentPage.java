package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Report;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AppointmentPage {

	private WebDriver _driver = null;
	private Report report;

	@FindBy(xpath = "//h2[@data-testid='dynamic-title']")
	public WebElement appointment_title;
	@FindBy(xpath = "//div[@data-testid='appointment']/p[1]/span")
	private List<WebElement> petNames;
	@FindBy(xpath = "//div[@data-testid='appointment']/p[2]/span")
	private List<WebElement> ownerNames;
	@FindBy(xpath = "//div[@data-testid='appointment']/p[3]/span")
	private List<WebElement> dates;
	@FindBy(xpath = "//div[@data-testid='appointment']/p[4]/span")
	private List<WebElement> times;
	@FindBy(xpath = "//div[@data-testid='appointment']/p[5]/span")
	private List<WebElement> symptomss;
	@FindBy(xpath = "//button[@data-testid='btn-delete']")
	private List<WebElement> btns_delete;



	public AppointmentPage(WebDriver driver, Report report) {
		this._driver = driver;
		this.report = report;
		PageFactory.initElements(_driver, this);
	}



	public void validateAppointment(int index,String pet, String owner, String date, String time, String symptoms) throws Throwable {

		try {
			String form_header = appointment_title.getText().trim();
			Assert.assertEquals("MANAGE YOUR APPOINTMENTS", form_header);
			validate(petNames,index,pet,"pet name");
			validate(ownerNames,index,owner,"owner name");
			validate(dates,index,date,"date"); //2021-04-11 input -> output is 1995-11-04
			validate(times,index,time,"time"); //0245PM -> 10:00
			validate(symptomss,index,symptoms,"symptom");
			report.testPass("Appointment: ", "appointment_"+index, false);
		} catch (Throwable t) {
			report.testFail("Error al validar la información de la reservación.", "fillFormError", false);
			throw t;
		}

	}

	private void validate(List<WebElement> elements,int index, String value, String propName) throws Throwable {

		try {

			String actualValue = elements.get(index).getText();

			if(actualValue.equals(value)){
				report.testPass("Validate "+ propName +" is: "+ value);
			}else if(propName.equals("date")) {
				String[] parts = value.split("-");

				if(actualValue.contains(parts[0]) && actualValue.contains(parts[1]) && actualValue.contains(parts[2])){
					report.testPass("Validate "+ propName +" is: "+ actualValue);
					validateDateIsValid(elements,index,actualValue);
				} else {
					report.testFail("Validate "+ propName +" is: "+ actualValue);
				}
			}else {
				report.testFail( propName +" is not: "+ value);
			}

		} catch (Throwable t) {
			report.testFail("Error al validar la información del formulario.", "fillFormError_"+index, false);
			throw t;
		}

	}

	private void validateDateIsValid(List<WebElement> elements, int index,String date) throws Throwable {

		try {
			String dateString = elements.get(index).getText(); //MM-DD-YYYY

			if(isValidDate(dateString)){
				report.testPass("Check the date is in the future.");
			} else {
				report.testFail("The date is before the current date. Should not be allowed.");
			}

		} catch (Throwable t) {
			report.testFail("Error al validar la fecha de la reservación.", "fillFormError", false);
			//throw t;
		}

	}

	private static boolean isValidDate(String pDateString) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(pDateString); //2021-11-04 FRONT FORMAT
		//Date date = new SimpleDateFormat("MM-dd-yyyy").parse(pDateString);//MM-DD-YYYY

		return new Date().before(date);
	}

	public void deleteAppointment(int i) throws Exception {
		btns_delete.get(i).click();
		Thread.sleep(2000);
		report.testPass("Click button to delete the appointment.", "delete_"+i, false);
	}
}
