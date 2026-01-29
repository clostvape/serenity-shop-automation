package org.example.retointerbank.navigation;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.example.retointerbank.ui.ShopHomePage;


public class NavigateTo {

    private NavigateTo() {
    }

    
    public static Performable theShopHomePage() {
        return Task.where("{0} navega a la página principal de SHOP",
                Open.browserOn().the(ShopHomePage.class));
    }

}
