package com.intercorp.reto.questions;

import com.intercorp.reto.ui.CheckoutPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class OrderWasPlaced {

    public static Question<Boolean> displayed(){
        return Visibility.of(CheckoutPage.ORDER_CONFIRMATION).asBoolean();
    }
}
