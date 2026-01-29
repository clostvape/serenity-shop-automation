package org.example.retointerbank.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvReaderUtil {
    public static List<Map<String, String>> leerDatos(String rutaCsv) throws IOException {
        List<Map<String, String>> datos = new ArrayList<>();
        try (Reader reader = new FileReader(rutaCsv);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord record : csvParser) {
                datos.add(record.toMap());
            }
        }
        return datos;
    }
}
