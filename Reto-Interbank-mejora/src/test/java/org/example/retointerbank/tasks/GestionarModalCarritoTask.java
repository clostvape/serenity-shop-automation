package org.example.retointerbank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.example.retointerbank.interactions.ClickOnShadow;
import org.example.retointerbank.ui.ShopPage;


public class GestionarModalCarritoTask implements Task {

    private final boolean irCheckout;

    public GestionarModalCarritoTask(boolean irCheckout) {
        this.irCheckout = irCheckout;
    }

    public static GestionarModalCarritoTask cerrar() { return new GestionarModalCarritoTask(false); }
    public static GestionarModalCarritoTask irAlCheckout() { return new GestionarModalCarritoTask(true); }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(1000); // Esperar a que el modal aparezca
            if (irCheckout) {
                actor.attemptsTo(
                    ClickOnShadow.element(ShopPage.CART_MODAL_CHECKOUT_BTN, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_CART_MODAL)
                );
            } else {
                actor.attemptsTo(
                    ClickOnShadow.element(ShopPage.CART_MODAL_CLOSE_BTN, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_CART_MODAL)
                );
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted durante GestionarModalCarritoTask", e);
        }
    }
}