package com.intercorp.reto.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CheckoutPage {

    private CheckoutPage(){}

    public static final Target INPUT_EMAIL = Target.the("Email")
            .located(By.cssSelector("input#email"));
    public static final Target INPUT_PHONE = Target.the("Phone")
            .located(By.cssSelector("input#phone"));
    public static final Target INPUT_ADDRESS = Target.the("Address")
            .located(By.cssSelector("input#address"));
    public static final Target INPUT_CITY = Target.the("City")
            .located(By.cssSelector("input#city"));
    public static final Target INPUT_STATE = Target.the("State")
            .located(By.cssSelector("input#state"));
    public static final Target INPUT_ZIP = Target.the("Zip")
            .located(By.cssSelector("input#zip"));

    public static final Target BTN_PLACE_ORDER = Target.the("Place order")
            .located(By.cssSelector("input[type='submit'], shop-button>button"));

    public static final Target ORDER_CONFIRMATION = Target.the("Order confirmation page")
            .located(By.cssSelector("shop-checkout-success, h1"));
}
