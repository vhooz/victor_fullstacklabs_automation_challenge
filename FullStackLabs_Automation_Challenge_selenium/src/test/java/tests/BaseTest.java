package tests;

import org.openqa.selenium.WebDriver;

import utils.Report;

public class BaseTest {

	public static WebDriver driver;
	public static Report report;

	public static void inicializeReport() throws Throwable {
		if (report == null) {
			report = new Report();
		}
	}

	public void tearDown() throws Exception {
		System.out.println("@After each test:");
		System.out.println("Escribiendo resultados en el los reportes html");

		report.endTest();
		closeBrowser();
	}

	public static void closeBrowser() throws Exception {
		driver.quit();
	}

	public static void endReport() throws Exception {
		report.endReport();
	}

}
