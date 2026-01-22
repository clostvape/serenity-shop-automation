package com.intercorp.reto.questions;

import com.intercorp.reto.ui.CartPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;

public class CartTotal {

    public static Question<String> value(){
        return TextContent.of(CartPage.TOTAL_PRICE).asString();
    }
}
