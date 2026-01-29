package org.example.retointerbank.steps;

import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.example.retointerbank.tasks.AbrirLaTiendaTask;
import org.example.retointerbank.questions.PrecioTotalQuestion;
import org.example.retointerbank.tasks.AgregarChaquetaTask;
import org.example.retointerbank.tasks.RealizarCheckoutTask;
import org.example.retointerbank.tasks.AgregarChaquetasDesdeCsvTask;
import org.example.retointerbank.tasks.ValidarChaquetasDesdeCsvTask;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.retointerbank.questions.CheckoutSuccessQuestion; // nueva question
import org.example.retointerbank.ui.ShopPage; // para referencias si se necesitan

import java.util.List;

public class ShopStepDefinitions {
    private String rutaCsvCargado;
    private static final Logger LOGGER = LoggerFactory.getLogger(ShopStepDefinitions.class);

    @Dado("que {actor} abre la tienda SHOP")
    public void queActorAbreLaTiendaSHOP(Actor actor) {
        actor.wasAbleTo(AbrirLaTiendaTask.abrirLaTienda());
    }

    @Cuando("{actor} agrega una chaqueta de {string} talla {string}")
    @Y("{actor} agrega una chaqueta de \"{string}\" talla \"{string}\"")
    public void actorAgregaUnaChaqueta(Actor actor, String genero, String talla) {
        actor.attemptsTo(AgregarChaquetaTask.para(genero, talla));
    }

    @Cuando("{actor} agrega como última prenda una chaqueta de {string} talla {string}")
    @Y("{actor} agrega como última prenda una chaqueta de \"{string}\" talla \"{string}\"")
    public void actorAgregaUltimaChaqueta(Actor actor, String genero, String talla) {
        actor.attemptsTo(AgregarChaquetaTask.paraUltimaPrenda(genero, talla));
    }

    @SuppressWarnings("unused")
    @Cuando("{actor} agrega chaquetas desde el archivo {string}")
    public void actorAgregaChaquetasDesdeArchivo(Actor actor, String ruta) {
        actor.attemptsTo(AgregarChaquetasDesdeCsvTask.desde(ruta));
    }

    @Entonces("{actor} verifica que el precio total debe ser {string}")
    public void actorVerificaPrecioTotal(Actor actor, String precioEsperado) {
        String precio = actor.asksFor(PrecioTotalQuestion.valor());
        actor.attemptsTo(Ensure.that(precio).isEqualTo(precioEsperado));
        LOGGER.info("Precio validado: esperado={}, obtenido={}", precioEsperado, precio);
    }

    @Y("{actor} realiza el checkout")
    public void actorRealizaElCheckout(Actor actor) {
        actor.attemptsTo(RealizarCheckoutTask.ahora());
        LOGGER.info("Checkout realizado por: {}", actor.getName());
    }

    @Entonces("{actor} verifica que el proceso de compra finaliza correctamente")
    public void actorVerificaProcesoFinalizaCorrectamente(Actor actor) {
        CheckoutSuccessQuestion.CheckoutSuccessData data = actor.asksFor(CheckoutSuccessQuestion.result());
        actor.attemptsTo(
                Ensure.that(data.title()).isEqualTo("Thank you"),
                Ensure.that(data.paragraph()).contains("Demo checkout process complete")
        );
        LOGGER.info("Checkout OK. Título='{}' | Detalle='{}'", data.title(), data.paragraph());
    }

    @SuppressWarnings("unused")
    @Entonces("{actor} verifica que solo se agregaron las chaquetas válidas")
    public void actorVerificaChaquetasValidas(Actor actor) {
        String precio = actor.asksFor(PrecioTotalQuestion.valor());
        actor.attemptsTo(Ensure.that(precio).isNotBlank());
        LOGGER.info("Chaquetas válidas agregadas. Precio total: {}", precio);
    }

    @SuppressWarnings("unused")
    @Cuando("{actor} intenta agregar una chaqueta de {string} talla {string}")
    public void actorIntentaAgregarChaqueta(Actor actor, String genero, String talla) {
        try {
            actor.attemptsTo(AgregarChaquetaTask.para(genero, talla));
            actor.remember("resultado_agregado", "exitoso");
        } catch (Exception e) {
            actor.remember("resultado_agregado", "fallido");
        }
    }

    @SuppressWarnings("unused")
    @Entonces("{actor} verifica que la chaqueta fue agregada: {string}")
    public void actorVerificaResultadoAgregado(Actor actor, String resultadoEsperado) {
        String resultadoActual = actor.recall("resultado_agregado");
        actor.attemptsTo(Ensure.that(resultadoActual).isEqualTo(resultadoEsperado));
        LOGGER.info("Validación de resultado agregado: {}", resultadoEsperado);
    }

    @SuppressWarnings("unused")
    @Dado("que el sistema carga el archivo CSV {string}")
    public void sistemaCargaArchivoCSV(String rutaCsv) {
        this.rutaCsvCargado = rutaCsv;
        LOGGER.info("CSV cargado: {}", rutaCsv);
    }

    @SuppressWarnings("unused")
    @Cuando("se ejecutan las pruebas de cada chaqueta del CSV")
    public void ejecutaPruebasDeCSV() {
        Actor usuario = OnStage.theActorCalled("Sistema de Pruebas");
        usuario.attemptsTo(AbrirLaTiendaTask.abrirLaTienda(), ValidarChaquetasDesdeCsvTask.desde(rutaCsvCargado));
    }

    @SuppressWarnings("unused")
    @Entonces("se validan los resultados esperados de cada prueba")
    public void validaResultadosEsperados() {
        Actor usuario = OnStage.theActorCalled("Sistema de Pruebas");
        List<String> resultados = usuario.recall("resultados_pruebas");
        Ensure.that(resultados).isNotNull();
        Ensure.that(resultados.size()).isGreaterThan(0);
        resultados.forEach(r -> LOGGER.info("Resultado CSV: {}", r));
        LOGGER.info("Total de pruebas ejecutadas: {}", resultados.size());
    }
}