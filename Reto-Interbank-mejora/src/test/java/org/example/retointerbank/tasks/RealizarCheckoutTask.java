package org.example.retointerbank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.example.retointerbank.ui.ShopPage;
import org.example.retointerbank.interactions.ClickOnShadow;
import org.example.retointerbank.interactions.TypeIntoShadow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RealizarCheckoutTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(RealizarCheckoutTask.class);
    public static RealizarCheckoutTask ahora() {
        return new RealizarCheckoutTask();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(2000);

            actor.attemptsTo(
                TypeIntoShadow.theValue("tester@example.com", ShopPage.CHECKOUT_EMAIL, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("9999999999", ShopPage.CHECKOUT_PHONE, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("Av. Principal 123", ShopPage.CHECKOUT_ADDRESS, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("Lima", ShopPage.CHECKOUT_CITY, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("LI", ShopPage.CHECKOUT_STATE, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("00001", ShopPage.CHECKOUT_ZIP, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("Senior Tester", ShopPage.CHECKOUT_CARDHOLDER_NAME, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("4111 1111 1111 1111", ShopPage.CHECKOUT_CARD_NUMBER, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT),
                TypeIntoShadow.theValue("123", ShopPage.CHECKOUT_CVV, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT)
            );

            Thread.sleep(1000);

            actor.attemptsTo(ClickOnShadow.element(ShopPage.CHECKOUT_PLACE_ORDER_BTN, ShopPage.SHADOW_HOST_APP, ShopPage.SHADOW_HOST_CHECKOUT));
            LOGGER.info("Formulario de checkout completado y orden enviada");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted durante RealizarCheckoutTask", e);
        }
    }
}