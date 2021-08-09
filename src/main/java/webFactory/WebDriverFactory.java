package webFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class WebDriverFactory {

    public static WebDriver createDriver(WebDriverType wdType) {

        switch (wdType) {
            case IE:
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver();
            default:
                WebDriver defaultDriver = new InternetExplorerDriver();
                return defaultDriver;

        }
    }

    public static WebDriver createDriver(WebDriverType type, MutableCapabilities wdOptions){
        switch (type){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver( (ChromeOptions) wdOptions);
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver((OperaOptions) wdOptions);
            case IE:
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver((InternetExplorerOptions) wdOptions);
            default:
                return null;
        }
    }
}
