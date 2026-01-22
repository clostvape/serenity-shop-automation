package com.intercorp.reto.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CartPage {

    private CartPage(){}

    public static final Target LNK_VIEW_CART = Target.the("View cart link")
            .located(By.cssSelector("a#viewCartAnchor"));

    public static final Target TOTAL_PRICE = Target.the("Total price")
            .located(By.cssSelector("div>div:nth-of-type(2)>div:nth-of-type(3)"));

    public static final Target BTN_CHECKOUT = Target.the("Checkout button")
            .located(By.cssSelector("div:nth-of-type(2)>shop-button:nth-of-type(2)>a"));

    public static final Target BTN_REMOVE_FIRST = Target.the("Remove first item")
            .located(By.cssSelector("shop-cart-item:nth-of-type(1) iron-icon[icon='shop:close']"));
}
