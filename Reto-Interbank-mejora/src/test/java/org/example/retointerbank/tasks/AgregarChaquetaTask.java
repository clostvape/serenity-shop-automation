package org.example.retointerbank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.example.retointerbank.ui.ShopPage;
import org.example.retointerbank.interactions.ClickOnShadow;
import org.example.retointerbank.interactions.SelectShadowOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgregarChaquetaTask implements Task {
    private final String genero;
    private final String talla;
    private final boolean isUltimaPrenda;
    private static final Logger LOGGER = LoggerFactory.getLogger(AgregarChaquetaTask.class);

    public AgregarChaquetaTask(String genero, String talla) {
        this(genero, talla, false);
    }

    public AgregarChaquetaTask(String genero, String talla, boolean isUltimaPrenda) {
        this.genero = genero;
        this.talla = talla;
        this.isUltimaPrenda = isUltimaPrenda;
    }

    public static AgregarChaquetaTask para(String genero, String talla) {
        return new AgregarChaquetaTask(genero, talla, false);
    }

    public static AgregarChaquetaTask paraUltimaPrenda(String genero, String talla) {
        return new AgregarChaquetaTask(genero, talla, true);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String categoriaSelector = genero.equalsIgnoreCase("hombre") ?
                ShopPage.CATEGORIA_HOMBRE_SHADOW :
                ShopPage.CATEGORIA_MUJER_SHADOW;

        try {
            actor.attemptsTo(
                    ClickOnShadow.element(categoriaSelector, ShopPage.SHADOW_HOST_APP)
            );

            Thread.sleep(2000); // Esperar a que cargue la lista

            actor.attemptsTo(
                    ClickOnShadow.element(
                            ShopPage.SHADOW_FIRST_LIST_ITEM,
                            ShopPage.SHADOW_HOST_APP,
                            ShopPage.SHADOW_HOST_LIST
                    )
            );

            Thread.sleep(2000); // Esperar a que cargue el detalle

            actor.attemptsTo(
                    SelectShadowOption.byVisibleText(
                            talla,
                            ShopPage.SHADOW_SIZE_SELECT,
                            ShopPage.SHADOW_HOST_APP,
                            ShopPage.SHADOW_HOST_DETAIL
                    )
            );

            Thread.sleep(500); // Esperar a que se seleccione la talla

            actor.attemptsTo(
                    ClickOnShadow.element(
                            ShopPage.ADD_TO_CART_BUTTON,
                            ShopPage.SHADOW_HOST_APP,
                            ShopPage.SHADOW_HOST_DETAIL
                    )
            );

            Thread.sleep(1000); // Esperar a que aparezca el modal

            if (isUltimaPrenda) {
                LOGGER.info("Última prenda agregada, se procederá al checkout.");
                actor.attemptsTo(GestionarModalCarritoTask.irAlCheckout());
            } else {
                actor.attemptsTo(GestionarModalCarritoTask.cerrar());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted durante AgregarChaquetaTask", e);
        }
    }
}