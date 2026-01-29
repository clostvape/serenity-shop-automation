package org.example.retointerbank.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.example.retointerbank.model.ChaquetaInfo;
import org.example.retointerbank.utils.CsvReaderUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ValidarChaquetasDesdeCsvTask {

    private ValidarChaquetasDesdeCsvTask() {}

    public static Performable desde(String rutaCsv) {
        return Task.where("{0} valida chaquetas desde CSV", actor -> {
            List<ChaquetaInfo> chaquetas;
            try {
                chaquetas = cargarDesdeCSV(rutaCsv);
            } catch (IOException e) {
                throw new AssertionError("Error al leer CSV: " + rutaCsv, e);
            }

            actor.remember("resultados_pruebas", new ArrayList<String>());

            for (int i = 0; i < chaquetas.size(); i++) {
                ChaquetaInfo chaqueta = chaquetas.get(i);
                ejecutarPrueba(actor, chaqueta, i + 1);
            }
        });
    }

    private static void ejecutarPrueba(Actor actor, ChaquetaInfo chaqueta, int numeroPrueba) {
        String resultado;
        try {
            actor.attemptsTo(AgregarChaquetaTask.para(chaqueta.getGenero(), chaqueta.getTalla()));
            resultado = "exitoso";
            System.out.println("Prueba " + numeroPrueba + ": " + chaqueta.getGenero() + "/" +
                             chaqueta.getTalla() + " → exitoso");
        } catch (Exception e) {
            resultado = "fallido";
            System.out.println("Prueba " + numeroPrueba + ": " + chaqueta.getGenero() + "/" +
                             chaqueta.getTalla() + " → fallido (esperado)");
        }

        String esperado = chaqueta.getResultadoEsperado();
        if (!resultado.equals(esperado)) {
            throw new AssertionError("Prueba " + numeroPrueba + " falló: esperado=" +
                                   esperado + ", obtenido=" + resultado);
        }

        List<String> resultados = actor.recall("resultados_pruebas");
        resultados.add("Prueba " + numeroPrueba + ": " + resultado + " (OK)");
    }

    private static List<ChaquetaInfo> cargarDesdeCSV(String rutaCsv) throws IOException {
        List<Map<String, String>> registros = CsvReaderUtil.leerDatos(rutaCsv);
        return registros.stream().map(m -> new ChaquetaInfo(
                m.getOrDefault("genero", ""),
                m.getOrDefault("talla", ""),
                m.getOrDefault("precio", ""),
                Boolean.parseBoolean(m.getOrDefault("valida", "true")),
                m.getOrDefault("resultado_esperado", "exitoso")
        )).collect(Collectors.toList());
    }
}
