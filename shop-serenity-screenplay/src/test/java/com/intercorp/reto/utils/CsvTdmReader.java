package com.intercorp.reto.utils;

import com.intercorp.reto.models.JacketData;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvTdmReader {

    private CsvTdmReader(){}

    public static List<JacketData> readJackets(String resourcePath) {
        List<JacketData> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(
                CsvTdmReader.class.getClassLoader().getResourceAsStream(resourcePath),
                StandardCharsets.UTF_8))) {

            String[] line;
            boolean first = true;

            while ((line = reader.readNext()) != null) {
                if (first) { first = false; continue; }
                String category = line.length > 0 ? line[0] : "";
                String product = line.length > 1 ? line[1] : "";
                String size = line.length > 2 ? line[2] : "";
                String qty = line.length > 3 ? line[3] : "1";
                String valid = line.length > 4 ? line[4] : "true";

                data.add(new JacketData(category, product, size, Integer.parseInt(qty), Boolean.parseBoolean(valid)));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Error leyendo CSV: " + resourcePath, e);
        }
        return data;
    }
}
