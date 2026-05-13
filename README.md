# Crowdar Automation Challenge

Proyecto de automatizacion para validar flujos UI de [SauceDemo](https://www.saucedemo.com/) y pruebas API sobre Mercado Libre usando Selenium, Cucumber, JUnit Platform y Karate.

## Tabla de contenidos

- [Stack tecnico](#stack-tecnico)
- [Instalacion](#instalacion)
- [Configuracion](#configuracion)
- [Ejecucion de pruebas](#ejecucion-de-pruebas)
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

Los comandos UI ejecutan los escenarios disponibles, incluyendo `@intentionalFail`. Ese caso falla a proposito para validar screenshots y reportes, por lo que la ejecucion UI puede terminar con error esperado.

- Ejecutar UI en Chrome, incluyendo el caso de fallo intencional:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=chrome"
```

- Ejecutar UI en Firefox, incluyendo el caso de fallo intencional:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dbrowser=firefox"
```

- Ejecutar pruebas UI con tag `@smoke`:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dcucumber.filter.tags=@smoke"
```

- Ejecutar pruebas UI con tag `@regression`:

```bash
mvn clean test "-Dtest=UiTestRunner" "-Dcucumber.filter.tags=@regression"
```

- Ejecutar pruebas API:

```bash
mvn clean test "-Dtest=ApiTestRunner"
```

## Reportes y evidencias

Los reportes se generan al ejecutar las pruebas:

| Tipo | Ubicacion |
| --- | --- |
| Reporte HTML Cucumber UI | `reports/ui/cucumber-report.html` |
| Reporte JSON Cucumber UI | `reports/ui/cucumber-report.json` |
| Reportes Karate API | `reports/api/` |
| Reportes Surefire | `target/surefire-reports/` |
| Screenshots de fallos UI | `screenshots/` |

El escenario `@intentionalFail` esta incluido para comprobar que el framework captura evidencias cuando una prueba UI falla.
