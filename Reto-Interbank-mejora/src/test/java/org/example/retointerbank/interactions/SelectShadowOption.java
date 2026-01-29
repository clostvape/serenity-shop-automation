package org.example.retointerbank.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchShadowRootException;

public class SelectShadowOption implements Interaction {
    private final String optionText;
    private final String selectCss;
    private final String[] hostChain;

    private SelectShadowOption(String optionText, String selectCss, String... hostChain) {
        this.optionText = optionText;
        this.selectCss = selectCss;
        this.hostChain = hostChain;
    }

    public static SelectShadowOption byVisibleText(String optionText, String selectCss, String... hostChain) {
        return new SelectShadowOption(optionText, selectCss, hostChain);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        SearchContext context = driver;

        for (String host : hostChain) {
            WebElement hostEl = retryFind(context, host);
            try {
                context = hostEl.getShadowRoot();
            } catch (NoSuchShadowRootException | UnsupportedCommandException e) {
                context = hostEl;
            }
        }

        WebElement select = retryFind(context, selectCss);
        ensureElementVisible(driver, select);

        boolean found = false;
        for (WebElement option : select.findElements(By.tagName("option"))) {
            if (option.getText().trim().equalsIgnoreCase(optionText.trim())) {
                option.click();
                found = true;
                break;
            }
        }
        if (!found) {
            throw new AssertionError("No se encontró la opción de talla: " + optionText);
        }
    }

    private WebElement retryFind(SearchContext context, String css) {
        for (int i = 0; i < 10; i++) {
            try {
                return context.findElement(By.cssSelector(css));
            } catch (NoSuchElementException e) {
                try { Thread.sleep(500); } catch (InterruptedException ie) { Thread.currentThread().interrupt(); }
            }
        }
        throw new AssertionError("Host/Elemento no encontrado tras reintentos: " + css);
    }

    private void ensureElementVisible(WebDriver driver, WebElement el) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        } catch (Exception ignored) {}
    }
}