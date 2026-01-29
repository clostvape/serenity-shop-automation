package org.example.retointerbank.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.example.retointerbank.ui.ShopPage;
import org.example.retointerbank.interactions.ClickOnShadow;
import org.example.retointerbank.interactions.GetTextFromShadow;


public class PrecioTotalQuestion implements Question<String> {

    public static PrecioTotalQuestion valor() {
        return new PrecioTotalQuestion();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                ClickOnShadow.element(
                        ShopPage.CART_ICON,
                        ShopPage.SHADOW_HOST_APP
                )
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String rawPrice = actor.asksFor(
                GetTextFromShadow.of(
                        ShopPage.CART_TOTAL_PRICE,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CART
                )
        );

        String formatted = rawPrice.trim();

        if (!formatted.startsWith("$")) {
            formatted = "$" + formatted;
        }

        return formatted;
    }
}