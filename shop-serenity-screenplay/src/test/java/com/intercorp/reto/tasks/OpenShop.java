package com.intercorp.reto.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenShop implements Task {

    private static final String SHOP_URL = "https://shop.polymer-project.org/";

    /**
     * Polymer SHOP is built using Web Components (Shadow DOM).
     * The navigation links are rendered inside the <shop-app> shadow root, so
     * Selenium cannot find them with a plain CSS selector.
     *
     * Instead, we wait for the <shop-app> custom element as a reliable marker
     * that the application has been loaded.
     */
    private static final Target SHOP_APP = Target.the("Main app")
            .locatedBy("shop-app");

    public static OpenShop home() {
        return new OpenShop();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(SHOP_URL),
                // Visibility is a safer signal that the Polymer app is ready.
                WaitUntil.the(SHOP_APP, isVisible()).forNoMoreThan(40).seconds()
        );
    }
}
