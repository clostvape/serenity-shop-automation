package org.example.retointerbank.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GetTextFromShadow implements Question<String> {

    private final String innerSelector;
    private final String[] hostChain;

    public GetTextFromShadow(String innerSelector, String... hostChain) {
        this.innerSelector = innerSelector;
        this.hostChain = hostChain;
    }

    public static GetTextFromShadow of(String innerSelector, String... hostChain) {
        return new GetTextFromShadow(innerSelector, hostChain);
    }

    @Override
    public String answeredBy(Actor actor) {
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

            for (int i = 0; i < 10; i++) {
                String text = target.getText().trim();
                if (!text.isEmpty()) {
                    return text;
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            String text = target.getText().trim();
            if (text.isEmpty()) {
                text = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("return arguments[0].textContent;", target);
                if (text != null) {
                    text = text.trim();
                }
            }

            return text != null ? text : "";

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
        return By.cssSelector(s);
    }
}
