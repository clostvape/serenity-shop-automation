package org.example.retointerbank.ui.base;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public abstract class BasePage extends PageObject {

    protected static final int LONG_TIMEOUT_SECONDS = 30;

    private final Config config;

    
    protected BasePage() {
        this.config = ConfigFactory.load();
    }

    
    protected String getBaseUrl() {
        return config.hasPath("webdriver.base.url")
                ? config.getString("webdriver.base.url")
                : "";
    }

    
    protected void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(LONG_TIMEOUT_SECONDS));
        wait.until(driver -> {
            Object result = ((JavascriptExecutor) driver).executeScript("return document.readyState");
            return result != null && result.equals("complete");
        });
    }

    
    protected String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }
}
