package tests;

import org.junit.*;
import pageobjects.AppointmentPage;
import pageobjects.FormPage;
import utils.DriverConfig;

import java.text.Normalizer;

public class appointmentManagmentTest extends BaseTest {

	FormPage formPage;
	AppointmentPage appointmentPage;

	@BeforeClass
	public static void onceExecutedBeforeAll() throws Throwable {
		System.out.println("@BeforeClass: Solo se ejecuta 1 sola vez antes de iniciar los escenarios.");
		inicializeReport();
	}

	@Before
	public void setUp() {
		System.out.println("Iniciar Navegador");
		driver = DriverConfig.getBrowser();
		formPage = new FormPage(driver,report);
		appointmentPage = new AppointmentPage(driver,report);
	}

	@Test
	public void Test_1() throws Throwable {
		// Write your test here
		String brakeline = "<br>";
		String testHeader = "<b>" + "ID # " + "1" + "</b>" + brakeline;
		testHeader += "Escenario: " + "<b>" + "Validate a new appointment is added." + "</b>" + brakeline;
		testHeader += "Navegador: " + "<b>" + "Chrome" + "</b>" + brakeline;
		report.startTest(testHeader, driver, "Should add the appointment to the list of appointments", "1");

		String pet = "toribio";
		String owner = "julio";
		String date = "11-04-2021"; //MM-DD-YYYY
		String time = "0245PM";
		String symptoms = "not eating.";


		driver.get("http://localhost:3000/");
		report.testPass("The application loads.", "test_1", false);
		Assert.assertTrue(formPage.app_name.isDisplayed());
		formPage.fillForm(pet,owner,date,time,symptoms);
		String timeValue = formPage.getTime();
		formPage.submit();
		appointmentPage.validateAppointment(0,pet,owner,date,timeValue,symptoms);
		appointmentPage.deleteAppointment(0);
	}

	@Test
	public void Test_2() throws Throwable {
		// Write your test here
		String brakeline = "<br>";
		String testHeader = "<b>" + "ID # " + "2" + "</b>" + brakeline;
		testHeader += "Escenario: " + "<b>" + "Validate that more than one appointments." + "</b>" + brakeline;
		testHeader += "Navegador: " + "<b>" + "Chrome" + "</b>" + brakeline;
		report.startTest(testHeader, driver, "Should show the appointments added to the list.", "2");
		driver.get("http://localhost:3000/");
		report.testPass("The application loads.", "test_2", false);
		Assert.assertTrue(formPage.app_name.isDisplayed());

		//Appointment # 1
		String pet = "toribio";
		String owner = "julio";
		String date = "11-04-2021"; //MM-DD-YYYY
		String time = "0245PM";
		String symptoms = "not eating.";

		formPage.fillForm(pet,owner,date,time,symptoms);
		String timeValue = formPage.getTime();
		formPage.submit();
		appointmentPage.validateAppointment(0,pet,owner,date,timeValue,symptoms);

		//Appointment #2
		pet = "taco";
		owner = "victor";
		date = "11-06-2021"; //MM-DD-YYYY
		time = "0345PM";
		symptoms = "not sleeping.";

		formPage.fillForm(pet,owner,date,time,symptoms);
		timeValue = formPage.getTime();
		formPage.submit();
		appointmentPage.validateAppointment(1,pet,owner,date,timeValue,symptoms);

		//Delete appointments.
		appointmentPage.deleteAppointment(0);
		appointmentPage.deleteAppointment(0);
	}

	@Test
	public void Test_3() throws Throwable {
		// Write your test here
		String brakeline = "<br>";
		String testHeader = "<b>" + "ID # " + "3" + "</b>" + brakeline;
		testHeader += "Escenario: " + "<b>" + "Validate a new appointment with a past date is not added." + "</b>" + brakeline;
		testHeader += "Navegador: " + "<b>" + "Chrome" + "</b>" + brakeline;
		report.startTest(testHeader, driver, "Should not add the appointment to the list of appointments", "3");

		String pet = "toribio";
		String owner = "julio";
		String date = "04-04-2021"; //MM-DD-YYYY
		String time = "0245PM";
		String symptoms = "not eating.";

		driver.get("http://localhost:3000/");
		report.testPass("The application loads.", "test_3", false);
		Assert.assertTrue(formPage.app_name.isDisplayed());
		formPage.fillForm(pet,owner,date,time,symptoms);
		String timeValue = formPage.getTime();
		formPage.submit();
		appointmentPage.validateAppointment(0,pet,owner,date,timeValue,symptoms);
		appointmentPage.deleteAppointment(0);

	}

	@After
	public void endTest() throws Exception {
		tearDown();
	}

}
