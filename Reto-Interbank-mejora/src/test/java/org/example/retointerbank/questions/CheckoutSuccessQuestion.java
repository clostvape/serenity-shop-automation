package org.example.retointerbank.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.example.retointerbank.interactions.GetTextFromShadow;
import org.example.retointerbank.ui.ShopPage;


public class CheckoutSuccessQuestion implements Question<CheckoutSuccessQuestion.CheckoutSuccessData> {

    public static CheckoutSuccessQuestion result() { return new CheckoutSuccessQuestion(); }

    @Override
    public CheckoutSuccessData answeredBy(Actor actor) {
        String title = actor.asksFor(
                GetTextFromShadow.of(
                        ShopPage.CHECKOUT_SUCCESS_TITLE,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );
        String paragraph = actor.asksFor(
                GetTextFromShadow.of(
                        ShopPage.CHECKOUT_SUCCESS_PARAGRAPH,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );
        return new CheckoutSuccessData(title, paragraph);
    }

    public record CheckoutSuccessData(String title, String paragraph) {}
}