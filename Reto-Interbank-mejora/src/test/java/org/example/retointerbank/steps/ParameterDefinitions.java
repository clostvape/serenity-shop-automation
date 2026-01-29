package org.example.retointerbank.steps;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;


public class ParameterDefinitions {

    
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    
    @ParameterType("(?:[A-ZÁÉÍÓÚÑ][\\wÁÉÍÓÚÑáéíóúñ]+|él|ella|ellos|ellas|el usuario|la usuaria|los usuarios|las usuarias)")
    public Actor actor(String token) {
        String lowered = token.toLowerCase();
        boolean esPronombre = lowered.matches("él|ella|ellos|ellas");
        boolean esGenerico = lowered.matches("el usuario|la usuaria|los usuarios|las usuarias");
        if (esPronombre || esGenerico) {
            Actor spotlight = OnStage.theActorInTheSpotlight();
            if (spotlight == null) {
                throw new IllegalStateException("Se usó '" + token + "' antes de definir un actor por nombre. Define primero: 'Dado que Juan abre la tienda'.");
            }
            return spotlight;
        }
        return OnStage.theActorCalled(token);
    }

}