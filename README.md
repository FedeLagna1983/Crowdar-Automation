# Crowdar Automation

Proyecto base de automatizacion con Java, Maven, Selenium WebDriver, TestNG y REST Assured.

## Estructura

```text
CROWDAR AUTOMATION
|-- pom.xml
|-- testng.xml
|-- README.md
|-- reports
|-- screenshots
`-- src
    `-- test
        |-- java
        |   `-- crowdar
        |       |-- base
        |       |   `-- BaseTest.java
        |       |-- factory
        |       |   `-- DriverFactory.java
        |       |-- pages
        |       |   |-- BasePage.java
        |       |   |-- LoginPage.java
        |       |   |-- ProductsPage.java
        |       |   `-- CartPage.java
        |       |-- tests
        |       |   |-- LoginTests.java
        |       |   |-- CartTests.java
        |       |   `-- MercadoLibreApiTests.java
        |       `-- utils
        |           `-- ScreenshotUtils.java
        `-- resources
            `-- config.properties
```

## Requisitos

- Java 17 o superior.
- Maven 3.9 o superior.
- Chrome, Firefox o Edge instalado para las pruebas UI.

## Configuracion

Editar `src/test/resources/config.properties` para cambiar navegador, modo headless, URL base y credenciales.

## Ejecucion

```bash
mvn test
```

Para compilar sin ejecutar pruebas:

```bash
mvn -DskipTests test
```

Los reportes de Surefire se generan en `reports/surefire` y las capturas de pantalla de fallos UI en `screenshots`.
