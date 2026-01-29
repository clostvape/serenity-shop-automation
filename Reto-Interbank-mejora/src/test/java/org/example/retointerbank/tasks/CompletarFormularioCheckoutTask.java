package org.example.retointerbank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.example.retointerbank.ui.ShopPage;
import org.example.retointerbank.interactions.TypeIntoShadow;
import org.example.retointerbank.interactions.ClickOnShadow;


public class CompletarFormularioCheckoutTask implements Task {

    public static CompletarFormularioCheckoutTask conDatosDePrueba() {
        return new CompletarFormularioCheckoutTask();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        pause(1000);

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "test@example.com",
                        ShopPage.CHECKOUT_EMAIL,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "1234567890",
                        ShopPage.CHECKOUT_PHONE,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "123 Main Street",
                        ShopPage.CHECKOUT_ADDRESS,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "New York",
                        ShopPage.CHECKOUT_CITY,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "NY",
                        ShopPage.CHECKOUT_STATE,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "10001",
                        ShopPage.CHECKOUT_ZIP,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "John Doe",
                        ShopPage.CHECKOUT_CARDHOLDER_NAME,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "4111 1111 1111 1111",
                        ShopPage.CHECKOUT_CARD_NUMBER,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        actor.attemptsTo(
                TypeIntoShadow.theValue(
                        "123",
                        ShopPage.CHECKOUT_CVV,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        pause(500);

        actor.attemptsTo(
                ClickOnShadow.element(
                        ShopPage.CHECKOUT_PLACE_ORDER_BTN,
                        ShopPage.SHADOW_HOST_APP,
                        ShopPage.SHADOW_HOST_CHECKOUT
                )
        );

        pause(1500);
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
