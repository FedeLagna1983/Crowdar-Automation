# Crowdar Automation Challenge

## Instalacion

- Clonar el repositorio:

```bash
git clone https://github.com/FedeLagna1983/Crowdar-Automation.git
cd Crowdar-Automation
```

- Verificar que Java y Maven esten instalados:

```bash
java -version
mvn -version
```

- Instalar dependencias del proyecto:

```bash
mvn clean install
```

## Ejecucion de Pruebas

- Ejecutar todas las pruebas con la configuracion por defecto:

```bash
mvn clean test
```

- Ejecutar todas las pruebas en Chrome:

```bash
mvn clean test -Dbrowser=chrome
```

- Ejecutar todas las pruebas en Firefox:

```bash
mvn clean test -Dbrowser=firefox
```

## Ejecucion por Tags

Tags disponibles:

- `@login`
- `@cart`
- `@api`
- `@intentionalFail`

El tag `@intentionalFail` ejecuta un escenario que falla de forma intencional. Sirve para demostrar la captura de error, screenshots y reportes cuando una prueba falla.

### Chrome

- Ejecutar pruebas de login:

```bash
mvn clean test -Dbrowser=chrome "-Dcucumber.filter.tags=@login"
```

- Ejecutar pruebas de carrito:

```bash
mvn clean test -Dbrowser=chrome "-Dcucumber.filter.tags=@cart"
```

- Ejecutar prueba con fallo intencional:

```bash
mvn clean test -Dbrowser=chrome "-Dcucumber.filter.tags=@intentionalFail"
```

- Ejecutar pruebas de API:

```bash
mvn clean test -Dbrowser=chrome "-Dcucumber.filter.tags=@api"
```

### Firefox

- Ejecutar pruebas de login:

```bash
mvn clean test -Dbrowser=firefox "-Dcucumber.filter.tags=@login"
```

- Ejecutar pruebas de carrito:

```bash
mvn clean test -Dbrowser=firefox "-Dcucumber.filter.tags=@cart"
```

- Ejecutar prueba con fallo intencional:

```bash
mvn clean test -Dbrowser=firefox "-Dcucumber.filter.tags=@intentionalFail"
```

- Ejecutar pruebas de API:

```bash
mvn clean test -Dbrowser=firefox "-Dcucumber.filter.tags=@api"
```
