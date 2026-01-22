Feature: Compra de chaquetas en SHOP
  Como usuario de SHOP
  Quiero agregar chaquetas de hombre y mujer al carrito
  Para validar precios y completar el checkout

  Background:
    Given que "Cesar" abre la aplicacion SHOP

  @precio_total
  Scenario: Agregar chaqueta hombre XL y mujer S y validar total
    When agrega la chaqueta de hombre "Men's Tech Shell Full-Zip" con talla "XL" al carrito
    And agrega la chaqueta de mujer "Ladies Modern Stretch Full Zip" con talla "S" al carrito
    And abre el carrito
    Then el total del carrito debe ser mayor a 0

  @checkout
  Scenario: Agregar chaqueta hombre L y mujer XS y completar checkout
    When agrega la chaqueta de hombre "Men's Tech Shell Full-Zip" con talla "L" al carrito
    And agrega la chaqueta de mujer "Ladies Modern Stretch Full Zip" con talla "XS" al carrito
    And abre el carrito
    And realiza el checkout con datos de envio por defecto
    Then debe ver confirmacion de pedido finalizado

  @csv @negativos
  Scenario Outline: Agregar chaquetas desde CSV y validar negativos
    When agrega una chaqueta desde CSV con categoria "<category>" producto "<productName>" talla "<size>" cantidad "<quantity>"
    Then el resultado debe ser "<expected>"

    Examples:
      | category | productName                    | size | quantity | expected |
      | MEN      | Men's Tech Shell Full-Zip      | XL   | 1        | OK       |
      | LADIES   | Ladies Modern Stretch Full Zip | XS   | 1        | OK       |
      | MEN      | Producto Inexistente           | XL   | 1        | ERROR    |
      | LADIES   | Ladies Modern Stretch Full Zip |      | 1        | ERROR    |
