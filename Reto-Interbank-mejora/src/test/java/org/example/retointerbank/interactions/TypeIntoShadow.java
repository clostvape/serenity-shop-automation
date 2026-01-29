package org.example.retointerbank.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TypeIntoShadow implements Interaction {
    private final String text;
    private final String innerSelector;
    private final String[] shadowHosts;

    private TypeIntoShadow(String text, String innerSelector, String... shadowHosts) {
        this.text = text;
        this.innerSelector = innerSelector;
        this.shadowHosts = shadowHosts;
    }

    public static TypeIntoShadow theValue(String text, String innerSelector, String... shadowHosts) {
        return new TypeIntoShadow(text, innerSelector, shadowHosts);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        try {
            SearchContext currentContext = driver;

            for (String host : shadowHosts) {
                WebElement shadowHost = currentContext.findElement(By.cssSelector(host));
                SearchContext shadowRoot = shadowHost.getShadowRoot();
                currentContext = shadowRoot;
            }

            WebElement inputElement = currentContext.findElement(By.cssSelector(innerSelector));

            inputElement.clear();

            inputElement.sendKeys(text);

        } catch (Exception e) {
            System.out.println("Fallback a JavaScript para escribir en: " + innerSelector);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            StringBuilder script = new StringBuilder("let element = document");
            for (String host : shadowHosts) {
                script.append(".querySelector('").append(host).append("').shadowRoot");
            }
            script.append(".querySelector('").append(innerSelector).append("');");
            script.append("element.value = '").append(text).append("';");
            script.append("element.dispatchEvent(new Event('input', { bubbles: true }));");

            js.executeScript(script.toString());
        }
    }
}
