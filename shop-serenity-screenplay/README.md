# Reto Técnico – Serenity BDD + Screenplay + Cucumber (SHOP)

Automatización E2E para la tienda demo **SHOP**: https://shop.polymer-project.org/

## Stack
- Java 11
- Maven
- Serenity BDD 4.x
- Screenplay Pattern
- Cucumber (Gherkin) + JUnit 5
- TDM: CSV (OpenCSV)
- Reportes: Serenity Reports

## Estructura
```
src/test/java
  └─ com.intercorp.reto
      ├─ runners
      ├─ stepdefinitions
      ├─ tasks
      ├─ interactions
      ├─ questions
      ├─ ui
      └─ utils
src/test/resources
  ├─ features
  ├─ data
  ├─ serenity.conf
  └─ junit-platform.properties
```

## Cómo ejecutar

### 1) Pre-requisitos
- Java 11 instalado
- Maven 3.9+
- Chrome instalado

### 2) Ejecución (con reportes)
```bash
mvn clean verify
```

El reporte quedará en:
```
target/site/serenity/index.html
```

### 3) Cambiar modo headless (opcional)
Editar `src/test/resources/serenity.conf`:
- `headless.mode=true` -> `false`

## Escenarios implementados
1. Agregar chaqueta hombre (XL) y mujer (S) y validar precio total.
2. Agregar chaqueta hombre (L) y mujer (XS) y realizar checkout (validar finalización).
3. Escenario parametrizable con CSV (positivo/negativo) para chaquetas y tallas.

## Dataset CSV
Ruta: `src/test/resources/data/jackets.csv`

Columnas:
- category (MEN / LADIES)
- productName
- size
- quantity
- valid (true/false)

> Los casos con `valid=false` validan mensajes de error / no avance a checkout cuando falta talla o producto inválido.

## Notas de buenas prácticas aplicadas
- Screenplay: Tasks/Interactions/Questions y UI Targets centralizados.
- Sincronización: `WaitUntil` con condiciones explícitas.
- Localizadores: prioriza CSS y anclas robustas.
- Assertions: `seeThat` + `Ensure` con mensajes.
