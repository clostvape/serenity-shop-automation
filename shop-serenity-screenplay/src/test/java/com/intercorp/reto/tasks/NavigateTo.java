package com.intercorp.reto.tasks;

import com.intercorp.reto.ui.ShopHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateTo implements Task {

    public enum Category { MEN, LADIES }

    private final Category category;

    private NavigateTo(Category category) {
        this.category = category;
    }

    public static NavigateTo mensOuterwear() { return new NavigateTo(Category.MEN); }
    public static NavigateTo ladiesOuterwear() { return new NavigateTo(Category.LADIES); }

    @Override
    public <T extends Actor> void performAs(T actor) {

        // wait home tabs are rendered
        actor.attemptsTo(
                WaitUntil.the(ShopHomePage.TAB_MENS_OUTERWEAR, isVisible()).forNoMoreThan(25).seconds()
        );

        switch (category) {
            case MEN:
                actor.attemptsTo(
                        WaitUntil.the(ShopHomePage.TAB_MENS_OUTERWEAR, isClickable()).forNoMoreThan(25).seconds(),
                        Click.on(ShopHomePage.TAB_MENS_OUTERWEAR)
                );
                break;
            case LADIES:
                actor.attemptsTo(
                        WaitUntil.the(ShopHomePage.TAB_LADIES_OUTERWEAR, isClickable()).forNoMoreThan(25).seconds(),
                        Click.on(ShopHomePage.TAB_LADIES_OUTERWEAR)
                );
                break;
            default:
                throw new IllegalArgumentException("Categoría no soportada: " + category);
        }
    }
}
