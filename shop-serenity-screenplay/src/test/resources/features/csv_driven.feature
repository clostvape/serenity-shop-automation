Feature: Compra data-driven con CSV
  Para demostrar TDM con CSV
  Como QA automation
  Quiero ejecutar múltiples casos desde un dataset

  @tdm_csv
  Scenario: Ejecutar dataset de chaquetas
    Given que "Cesar" abre la aplicacion SHOP
    And que el actor abre SHOP para ejecutar casos desde CSV "data/jackets.csv"
    When ejecuta todos los casos de chaquetas desde el CSV
    Then el total del carrito debe ser mayor a 0.0
