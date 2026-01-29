# language: es
Característica: Compra de chaquetas en SHOP
  Como usuario de la tienda
  Quiero poder comprar chaquetas de diferentes tallas y géneros
  Para validar el proceso de compra y los precios

  @solo
  Escenario: Agregar chaqueta para hombre (XL) y mujer (S) y validar el precio total
    Dado que Carlos abre la tienda SHOP
    Cuando él agrega una chaqueta de "hombre" talla "XL"
    Y él agrega una chaqueta de "mujer" talla "S"
    Entonces él verifica que el precio total debe ser "$91.80"
  @3
  Escenario: Agregar chaqueta para hombre (L) y mujer (XS) y realizar el Checkout
    Dado que Lucía abre la tienda SHOP
    Cuando ella agrega una chaqueta de "hombre" talla "L"
    Y ella agrega como última prenda una chaqueta de "mujer" talla "XS"
    Y ella realiza el checkout
    Entonces ella verifica que el proceso de compra finaliza correctamente
  @4
  Escenario: Validar chaquetas parametrizables desde CSV con pruebas negativas
    Dado que el sistema carga el archivo CSV "src/test/resources/data/chaquetas.csv"
    Cuando se ejecutan las pruebas de cada chaqueta del CSV
    Entonces se validan los resultados esperados de cada prueba
