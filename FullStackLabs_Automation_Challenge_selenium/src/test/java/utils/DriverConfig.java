package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverConfig {

	public enum DriverType {

		CHROME, FIREFOX, OPERA, EDGE, IE

	}

	public static WebDriver getBrowser() {

		WebDriver _driver = null;
		String _browserName = System.getProperty("browser", DriverType.CHROME.toString()).toUpperCase();
		DriverType _driverType = DriverType.valueOf(_browserName);

		switch (_driverType) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			_driver = new ChromeDriver();
			break;
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			_driver = new FirefoxDriver();
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			_driver = new EdgeDriver();
			break;
		default:
			WebDriverManager.chromedriver().setup();
			_driver = new ChromeDriver();
			break;
		}
		// _driver.manage().window().maximize();
		_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return _driver;
	}

}
