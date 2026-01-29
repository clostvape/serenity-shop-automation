package org.example.retointerbank.model;

public class ChaquetaInfo {
    private final String genero; // hombre / mujer
    private final String talla;  // XL, L, S, XS, etc.
    private final String precioUnitario; // Formato "$45.00" si se provee
    private final boolean valida; // true = agregar; false = negativa
    private final String resultadoEsperado; // exitoso / fallido

    public ChaquetaInfo(String genero, String talla, String precioUnitario, boolean valida, String resultadoEsperado) {
        this.genero = genero;
        this.talla = talla;
        this.precioUnitario = precioUnitario;
        this.valida = valida;
        this.resultadoEsperado = resultadoEsperado;
    }

    public String getGenero() { return genero; }
    public String getTalla() { return talla; }
    public String getPrecioUnitario() { return precioUnitario; }
    public boolean isValida() { return valida; }
    public String getResultadoEsperado() { return resultadoEsperado; }
}
