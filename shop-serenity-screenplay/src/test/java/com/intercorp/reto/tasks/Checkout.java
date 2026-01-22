package com.intercorp.reto.tasks;

import com.intercorp.reto.ui.CartPage;
import com.intercorp.reto.ui.CheckoutPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class Checkout implements Task {

    public static Checkout withDefaultShipping() { return new Checkout(); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CartPage.BTN_CHECKOUT, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(CartPage.BTN_CHECKOUT),

                WaitUntil.the(CheckoutPage.INPUT_EMAIL, isVisible()).forNoMoreThan(15).seconds(),
                Enter.theValue("cesar.latorre@test.com").into(CheckoutPage.INPUT_EMAIL),
                Enter.theValue("999999999").into(CheckoutPage.INPUT_PHONE),
                Enter.theValue("Av. Demo 123").into(CheckoutPage.INPUT_ADDRESS),
                Enter.theValue("Lima").into(CheckoutPage.INPUT_CITY),
                Enter.theValue("Lima").into(CheckoutPage.INPUT_STATE),
                Enter.theValue("15001").into(CheckoutPage.INPUT_ZIP),

                WaitUntil.the(CheckoutPage.BTN_PLACE_ORDER, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(CheckoutPage.BTN_PLACE_ORDER)
        );
    }
}
