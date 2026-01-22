package com.intercorp.reto.stepdefinitions;

import com.intercorp.reto.questions.CartTotal;
import com.intercorp.reto.questions.OrderWasPlaced;
import com.intercorp.reto.tasks.AddJacketToCart;
import com.intercorp.reto.tasks.Checkout;
import com.intercorp.reto.tasks.OpenCart;
import com.intercorp.reto.tasks.OpenShop;
import com.intercorp.reto.tasks.NavigateTo;
import com.intercorp.reto.utils.PriceParser;
import io.cucumber.java.Before;
import io.cucumber.java.es.*;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class ShopStepDefinitions {

    private static final String BASE_URL = "https://shop.polymer-project.org/";

    @Managed(driver = "chrome")
    WebDriver hisBrowser;

    private Actor actor;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        actor = OnStage.theActorCalled("Cesar");
    }

    @Dado("que {string} abre la aplicacion SHOP")
    public void abrir_shop(String name) {
        actor = OnStage.theActorCalled(name);
        actor.attemptsTo(OpenShop.home());
    }

    @Cuando("agrega la chaqueta de hombre {string} con talla {string} al carrito")
    public void agrega_chaqueta_hombre(String product, String size) {
        actor.attemptsTo(AddJacketToCart.mens(product, size));
    }

    @Cuando("agrega la chaqueta de mujer {string} con talla {string} al carrito")
    public void agrega_chaqueta_mujer(String product, String size) {
        actor.attemptsTo(AddJacketToCart.ladies(product, size));
    }

    @Cuando("abre el carrito")
    public void abre_el_carrito() {
        actor.attemptsTo(OpenCart.viewCart());
    }

    @Entonces("el total del carrito debe ser mayor a {int}")
    public void total_mayor_a(int min) {
        String totalRaw = actor.asksFor(CartTotal.value());
        double total = PriceParser.parse(totalRaw);

        actor.attemptsTo(
                Ensure.that(total).isGreaterThan((double) min)
        );
    }

    @Cuando("realiza el checkout con datos de envio por defecto")
    public void realiza_checkout() {
        actor.attemptsTo(Checkout.withDefaultShipping());
    }

    @Entonces("debe ver confirmacion de pedido finalizado")
    public void confirmacion_pedido() {
        actor.should(seeThat("Confirmación visible", OrderWasPlaced.displayed()));
    }

    // Escenario parametrizable (CSV-like)
    @Cuando("agrega una chaqueta desde CSV con categoria {string} producto {string} talla {string} cantidad {string}")
    public void agrega_desde_csv(String category, String product, String size, String quantity) {
        NavigateTo.Category cat = category.trim().equalsIgnoreCase("LADIES") ? NavigateTo.Category.LADIES : NavigateTo.Category.MEN;

        // Para este reto la cantidad no se usa porque el sitio no permite editar qty antes del cart.
        if(cat == NavigateTo.Category.MEN){
            actor.attemptsTo(AddJacketToCart.mens(product, size));
        } else {
            actor.attemptsTo(AddJacketToCart.ladies(product, size));
        }
    }

    @Entonces("el resultado debe ser {string}")
    public void validar_resultado(String expected) {
        // Regla simple:
        // - OK: el carrito total > 0
        // - ERROR: no permite agregar (por producto inexistente o talla vacía) => total queda 0 o no muestra total.
        actor.attemptsTo(OpenCart.viewCart());

        String raw = actor.asksFor(CartTotal.value());
        double total = PriceParser.parse(raw);

        if("OK".equalsIgnoreCase(expected)){
            actor.attemptsTo(Ensure.that(total).isGreaterThan(0.0));
        } else {
            actor.attemptsTo(Ensure.that(total).isLessThanOrEqualTo(0.0));
        }
    }
}
