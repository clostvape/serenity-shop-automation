package org.example.retointerbank.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.example.retointerbank.navigation.NavigateTo;


public class AbrirLaTiendaTask {

    private AbrirLaTiendaTask() {
    }

    
    public static Performable abrirLaTienda() {
        return Task.where("{0} abre la tienda SHOP",
                NavigateTo.theShopHomePage()
        );
    }

}