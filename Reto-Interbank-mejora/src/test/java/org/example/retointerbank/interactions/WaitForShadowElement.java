package org.example.retointerbank.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;


public class WaitForShadowElement implements Interaction {

    public enum Condition { VISIBLE, CLICKABLE, TEXT_NOT_EMPTY }

    private final String selector;
    private final String[] hosts;
    private final Condition condition;
    private final Duration timeout;

    private WaitForShadowElement(String selector, Condition condition, Duration timeout, String... hosts) {
        this.selector = selector;
        this.condition = condition;
        this.timeout = timeout;
        this.hosts = hosts;
    }

    public static WaitForShadowElement forCondition(String selector, Condition condition, Duration timeout, String... hosts) {
        return new WaitForShadowElement(selector, condition, timeout, hosts);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        SearchContext context = driver;
        try {
            for (String host : hosts) {
                WebElement hostEl = context.findElement(By.cssSelector(host));
                try {
                    context = hostEl.getShadowRoot();
                } catch (UnsupportedCommandException | NoSuchElementException | NoSuchShadowRootException e) {
                    context = hostEl; // fallback si no tiene shadow root
                }
            }
            WebElement element = context.findElement(By.cssSelector(selector));

            switch (condition) {
                case VISIBLE -> wait.until(driver1 -> element.isDisplayed());
                case CLICKABLE -> wait.until(ExpectedConditions.elementToBeClickable(element));
                case TEXT_NOT_EMPTY -> wait.until((ExpectedCondition<Boolean>) d -> !element.getText().trim().isEmpty());
            }
        } catch (Exception e) {
            throw new AssertionError("No se cumplió la condición " + condition + " para '" + selector + "' en hosts " + Arrays.toString(hosts), e);
        }
    }
}
