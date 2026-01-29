package org.example.retointerbank.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.example.retointerbank.utils.CsvReaderUtil;
import org.example.retointerbank.model.ChaquetaInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class AgregarChaquetasDesdeCsvTask {

    private AgregarChaquetasDesdeCsvTask() {}

    public static Performable desde(String rutaCsv) {
        return Task.where("{0} agrega chaquetas desde csv", actor -> {
            List<ChaquetaInfo> chaquetas;
            try {
                chaquetas = cargar(rutaCsv);
            } catch (IOException e) {
                throw new AssertionError("Fallo leyendo el CSV: " + rutaCsv, e);
            }
            for (ChaquetaInfo c : chaquetas) {
                if (c.isValida()) {
                    actor.attemptsTo(AgregarChaquetaTask.para(c.getGenero(), c.getTalla()));
                } else continue; // talla inválida: se omite
            }
        });
    }

    private static List<ChaquetaInfo> cargar(String rutaCsv) throws IOException {
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