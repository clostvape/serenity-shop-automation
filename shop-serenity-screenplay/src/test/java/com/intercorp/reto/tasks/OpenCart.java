package com.intercorp.reto.tasks;

import com.intercorp.reto.ui.CartPage;
import com.intercorp.reto.ui.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class OpenCart implements Task {

    public static OpenCart viewCart(){ return new OpenCart(); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ProductPage.CART_ICON, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(ProductPage.CART_ICON),
                WaitUntil.the(CartPage.LNK_VIEW_CART, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(CartPage.LNK_VIEW_CART)
        );
    }
}
