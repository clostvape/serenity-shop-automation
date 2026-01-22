package com.intercorp.reto.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ShopHomePage {

    private ShopHomePage(){}

    // More robust targets (works even if shadow DOM structure changes)
    public static final Target TAB_MENS_OUTERWEAR = Target.the("Tab Men's Outerwear")
            .locatedBy("a[href='/list/mens_outerwear']");

    public static final Target TAB_LADIES_OUTERWEAR = Target.the("Tab Ladies Outerwear")
            .locatedBy("a[href='/list/ladies_outerwear']");

    public static Target productCardByName(String productName) {
        // The product link text is inside a shop-list-item card
        return Target.the("Product card: " + productName)
                .locatedBy("//shop-list-item//a[.//div[contains(@class,'title') and normalize-space()='{0}'] or normalize-space()='{0}']")
                .of(productName);
    }
}
