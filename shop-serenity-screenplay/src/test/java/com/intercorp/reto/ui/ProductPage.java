package com.intercorp.reto.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductPage {

    private ProductPage(){}

    public static final Target BTN_ADD_TO_CART = Target.the("Add to cart button")
            .located(By.cssSelector("div#content>div>shop-button>button"));

    public static final Target SELECT_SIZE = Target.the("Size select")
            .located(By.cssSelector("select#sizeSelect"));

    public static final Target CART_ICON = Target.the("Cart icon")
            .located(By.id("icon"));
}
