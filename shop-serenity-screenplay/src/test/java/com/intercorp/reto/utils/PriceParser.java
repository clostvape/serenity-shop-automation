package com.intercorp.reto.utils;

public class PriceParser {

    private PriceParser(){}

    public static double parse(String raw){
        if(raw == null) return 0;
        // Example: "$54.99"
        String cleaned = raw.replaceAll("[^0-9.,]", "");
        cleaned = cleaned.replace(",", "");
        if(cleaned.isBlank()) return 0;
        return Double.parseDouble(cleaned);
    }
}
