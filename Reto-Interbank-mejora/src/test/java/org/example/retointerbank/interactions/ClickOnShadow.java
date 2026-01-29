package org.example.retointerbank.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.JavascriptExecutor;


public class ClickOnShadow implements Interaction {

    private final String innerSelector; // puede ser css o xpath
    private final String[] hostChain;

    private ClickOnShadow(String innerSelector, String... hostChain) {
        this.innerSelector = innerSelector;
        this.hostChain = hostChain;
    }

    public static ClickOnShadow element(String innerSelector, String... hostChain) {
        return new ClickOnShadow(innerSelector, hostChain);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            SearchContext context = driver;
            for (String hostCss : hostChain) {
                WebElement host = context.findElement(By.cssSelector(hostCss));
                context = host.getShadowRoot();
            }
            if (context == null) {
                throw new IllegalStateException("Cadena de shadow roots vacía");
            }
            WebElement target = context.findElement(buildBy(innerSelector));

            try {
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'instant', block: 'center', inline: 'center'});",
                    target
                );
                Thread.sleep(200); // Esperar a que termine el scroll
            } catch (Exception ignored) {}

            for (int i = 0; i < 10; i++) {
                try {
                    if (target.isDisplayed() && target.isEnabled()) {
                        Boolean inViewport = (Boolean) ((JavascriptExecutor) driver).executeScript(
                            "var rect = arguments[0].getBoundingClientRect();" +
                            "return (rect.top >= 0 && rect.left >= 0 && " +
                            "rect.bottom <= window.innerHeight && rect.right <= window.innerWidth);",
                            target
                        );
                        if (Boolean.TRUE.equals(inViewport)) break;
                    }
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }

            try {
                target.click();
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                System.out.println("Click interceptado, usando JavaScript click en: " + innerSelector);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
            }
        } catch (NoSuchElementException e) {
            throw new AssertionError("No se encontró el elemento dentro del shadow root: " + innerSelector, e);
        }
    }

    private By buildBy(String selector) {
        String s = selector.trim();
        if (s.startsWith("//") || s.startsWith("xpath:")) {
            s = s.startsWith("xpath:") ? s.substring("xpath:".length()) : s;
            return By.xpath(s);
        }
        if (s.startsWith("css:")) {
            return By.cssSelector(s.substring("css:".length()));
        }
        return By.cssSelector(s); // default
    }
}