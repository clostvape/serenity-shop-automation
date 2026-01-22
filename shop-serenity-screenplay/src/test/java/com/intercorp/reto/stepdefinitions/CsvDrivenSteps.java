package com.intercorp.reto.stepdefinitions;

import com.intercorp.reto.models.JacketData;
import com.intercorp.reto.tasks.AddJacketToCart;
import com.intercorp.reto.tasks.OpenCart;
import com.intercorp.reto.tasks.OpenShop;
import com.intercorp.reto.tasks.NavigateTo;
import com.intercorp.reto.utils.CsvTdmReader;
import com.intercorp.reto.utils.PriceParser;
import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;

public class CsvDrivenSteps {

    private static final String BASE_URL = "https://shop.polymer-project.org/";

    @Dado("que el actor abre SHOP para ejecutar casos desde CSV {string}")
    public void abre_shop_csv(String csvPath){
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(OpenShop.home());
        actor.remember("csvPath", csvPath);
    }

    @Cuando("ejecuta todos los casos de chaquetas desde el CSV")
    public void ejecuta_casos_csv(){
        Actor actor = OnStage.theActorInTheSpotlight();
        String csvPath = actor.recall("csvPath");
        List<JacketData> cases = CsvTdmReader.readJackets(csvPath);

        for (JacketData tc : cases) {
            NavigateTo.Category cat = tc.getCategory().trim().equalsIgnoreCase("LADIES") ? NavigateTo.Category.LADIES : NavigateTo.Category.MEN;

            if(cat == NavigateTo.Category.MEN){
                actor.attemptsTo(AddJacketToCart.mens(tc.getProductName(), tc.getSize()));
            } else {
                actor.attemptsTo(AddJacketToCart.ladies(tc.getProductName(), tc.getSize()));
            }
        }

        actor.attemptsTo(OpenCart.viewCart());
    }

    @Entonces("el total del carrito debe ser mayor a {double}")
    public void total_mayor_double(double min){
        Actor actor = OnStage.theActorInTheSpotlight();
        String raw = actor.asksFor(com.intercorp.reto.questions.CartTotal.value());
        double total = PriceParser.parse(raw);
        actor.attemptsTo(Ensure.that(total).isGreaterThan(min));
    }
}
