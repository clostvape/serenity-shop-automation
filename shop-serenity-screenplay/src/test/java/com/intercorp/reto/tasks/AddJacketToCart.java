package com.intercorp.reto.tasks;

import com.intercorp.reto.ui.ProductPage;
import com.intercorp.reto.ui.ShopHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

public class AddJacketToCart implements Task {

    private final NavigateTo.Category category;
    private final String productName;
    private final String size;

    private AddJacketToCart(NavigateTo.Category category, String productName, String size) {
        this.category = category;
        this.productName = productName;
        this.size = size;
    }

    public static AddJacketToCart mens(String productName, String size){
        return new AddJacketToCart(NavigateTo.Category.MEN, productName, size);
    }

    public static AddJacketToCart ladies(String productName, String size){
        return new AddJacketToCart(NavigateTo.Category.LADIES, productName, size);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Navegar a categoría
        actor.attemptsTo(
                category == NavigateTo.Category.MEN ? NavigateTo.mensOuterwear() : NavigateTo.ladiesOuterwear(),
                WaitUntil.the(ShopHomePage.productCardByName(productName), isClickable()).forNoMoreThan(15).seconds(),
                Click.on(ShopHomePage.productCardByName(productName))
        );

        // Seleccionar talla (si viene)
        if(size != null && !size.trim().isEmpty()){
            actor.attemptsTo(
                    WaitUntil.the(ProductPage.SELECT_SIZE, isVisible()).forNoMoreThan(10).seconds(),
                    SelectFromOptions.byVisibleText(size).from(ProductPage.SELECT_SIZE)
            );
        }

        // Agregar al carrito
        actor.attemptsTo(
                WaitUntil.the(ProductPage.BTN_ADD_TO_CART, isClickable()).forNoMoreThan(10).seconds(),
                Click.on(ProductPage.BTN_ADD_TO_CART)
        );
    }
}
