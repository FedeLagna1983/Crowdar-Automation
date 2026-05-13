# Crowdar Automation Challenge

[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36.svg)](https://maven.apache.org/)
[![Selenium](https://img.shields.io/badge/Selenium-4.31.0-43B02A.svg)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.18.1-23D96C.svg)](https://cucumber.io/)
[![Karate](https://img.shields.io/badge/Karate-1.5.2-5A0FC8.svg)](https://karatelabs.github.io/karate/)

Proyecto de automatizacion para validar flujos UI de [SauceDemo](https://www.saucedemo.com/) y pruebas API sobre Mercado Libre usando Selenium, Cucumber, JUnit Platform y Karate.

## Tabla de contenidos

- [Stack tecnico](#stack-tecnico)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Instalacion](#instalacion)
- [Configuracion](#configuracion)
- [Ejecucion de pruebas](#ejecucion-de-pruebas)
- [Tags disponibles](#tags-disponibles)
- [Reportes y evidencias](#reportes-y-evidencias)

## Stack tecnico

| Herramienta | Uso | Version |
| --- | --- | --- |
| Java | Lenguaje base del proyecto | 21 |
| Maven | Gestion de dependencias y ejecucion | 3.x |
| Selenium WebDriver | Automatizacion UI | 4.31.0 |
| WebDriverManager | Gestion automatica de drivers | 5.8.0 |
| Cucumber | BDD para pruebas UI | 7.18.1 |
| JUnit Platform Suite | Runner de pruebas UI | 1.11.4 |
| Karate | Automatizacion API | 1.5.2 |

## Estructura del proyecto

```text
.
|-- pom.xml
|-- src/test/java/crowdar
|   |-- api/runners
|   |   `-- ApiTestRunner.java
|   |-- core
|   |   |-- config
|   |   |-- driver
|   |   `-- utils
|   `-- ui
|       |-- hooks
|       |-- pages
|       |   |-- cart
|       |   |   `-- CartPage.java
|       |   |-- inventory
|       |   |   |-- InventoryItemPage.java
|       |   |   `-- InventoryPage.java
|       |   `-- login
|       |       `-- LoginPage.java
|       |-- runners
|       `-- stepdefinitions
|-- src/test/resources
|   |-- config.properties
|   |-- karate-config.js
|   `-- features
|       |-- api/mercadolibre
|       `-- ui
|           |-- cart
|           `-- login
|-- reports
`-- screenshots
```

## Instalacion

1. Clonar el repositorio:

```bash
git clone https://github.com/FedeLagna1983/Crowdar-Automation.git
cd Crowdar-Automation
```

2. Verificar Java y Maven:

```bash
java -version
mvn -version
```

3. Instalar dependencias:

```bash
mvn clean install -DskipTests
```

## Configuracion

La configuracion UI se encuentra en `src/test/resources/config.properties`:

```properties
browser=chrome
baseUrl=https://www.saucedemo.com/
```

El navegador se puede cambiar por linea de comandos con `-Dbrowser=chrome` o `-Dbrowser=firefox`.

La configuracion API se encuentra en `src/test/resources/karate-config.js`:

```javascript
mercadoLibreBaseUrl: 'https://www.mercadolibre.com.ar'
```

## Ejecucion de pruebas

Ejecutar todas las pruebas evitando el escenario de fallo intencional:

```bash
mvn clean test "-Dcucumber.filter.tags=not @intentionalFail"
```

Ejecutar solo pruebas UI en Chrome:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=chrome" "-Dcucumber.filter.tags=not @intentionalFail"
```

Ejecutar solo pruebas UI en Firefox:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=firefox" "-Dcucumber.filter.tags=not @intentionalFail"
```

Ejecutar solo pruebas API:

```bash
mvn clean test "-Dtest=ApiTestRunner"
```

## Tags disponibles

| Tag | Descripcion | Runner |
| --- | --- | --- |
| `@login` | Escenarios de login valido e invalido en SauceDemo | `UiTestRunner` |
| `@cart` | Agregar y remover productos del carrito | `UiTestRunner` |
| `@intentionalFail` | Escenario que falla a proposito para validar screenshots y reportes | `UiTestRunner` |
| `@api` | Pruebas API con Karate | `ApiTestRunner` |
| `@mercadolibre` | Pruebas API sobre Mercado Libre | `ApiTestRunner` |
| `@departments` | Validacion del endpoint de departamentos | `ApiTestRunner` |

### Ejecucion por tag UI

Login en Chrome sin fallo intencional:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=chrome" "-Dcucumber.filter.tags=@login and not @intentionalFail"
```

Carrito en Chrome:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=chrome" "-Dcucumber.filter.tags=@cart"
```

Fallo intencional en Chrome:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=chrome" "-Dcucumber.filter.tags=@intentionalFail"
```

Login en Firefox sin fallo intencional:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=firefox" "-Dcucumber.filter.tags=@login and not @intentionalFail"
```

Carrito en Firefox:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=firefox" "-Dcucumber.filter.tags=@cart"
```

Fallo intencional en Firefox:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=firefox" "-Dcucumber.filter.tags=@intentionalFail"
```

### Ejecucion por tag API

Pruebas API:

```bash
mvn clean test "-Dtest=ApiTestRunner" "-Dkarate.options=--tags @api"
```

Pruebas API de departamentos:

```bash
mvn clean test "-Dtest=ApiTestRunner" "-Dkarate.options=--tags @departments"
```

## Reportes y evidencias

Los reportes se generan al ejecutar las pruebas:

| Tipo | Ubicacion |
| --- | --- |
| Reporte HTML Cucumber UI | `reports/ui/cucumber-report.html` |
| Reporte JSON Cucumber UI | `reports/ui/cucumber-report.json` |
| Reportes Karate API | `target/reports/api/` |
| Reportes Surefire | `target/surefire-reports/` |
| Screenshots de fallos UI | `screenshots/` |

El escenario `@intentionalFail` esta incluido para comprobar que el framework captura evidencias cuando una prueba UI falla.
