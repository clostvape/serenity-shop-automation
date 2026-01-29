# Reto IBK – Automatización con Serenity BDD

Proyecto de automatización de pruebas desarrollado con Serenity BDD + Screenplay + Cucumber, orientado a validar el flujo de compra en la aplicación SHOP.

---

## Stack Técnico

- Java: 21 (Temurin / Eclipse Adoptium – LTS)
- Maven: 3.9.x
- Serenity BDD: 4.3.2
- Cucumber: 7.x
- JUnit Platform
- Patrón: Screenplay

---

## Arquitectura

Estructura basada en Screenplay:

- tasks: acciones de negocio
- interactions: acciones técnicas (Shadow DOM, clicks, inputs)
- questions: validaciones
- ui: localizadores
- model: objetos de datos
- utils: utilidades (lectura CSV)
- features: escenarios Gherkin

---

## Manejo de Datos (TDM)

Datos externos desde:

src/test/resources/data/chaquetas.csv

Permite pruebas parametrizadas sin hardcodeo.

---

## Ejecución

Desde la carpeta del pom.xml:

mvn clean verify

---

## Reportes

Serenity genera el reporte en:

target/site/serenity/index.html

Abrir en navegador al finalizar.

---

## Requisitos del Entorno

- Java 21 configurado en JAVA_HOME
- Maven en PATH
- Google Chrome instalado

Verificar:

java -version  
mvn -version

Ambos deben mostrar Java 21.

---

## Objetivo

Demostrar:

- Buenas prácticas de automatización
- Uso correcto de Screenplay
- Escenarios Gherkin claros
- Sincronización estable
- TDM con CSV
- Reportes Serenity

Proyecto listo para CI/CD.