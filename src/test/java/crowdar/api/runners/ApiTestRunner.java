package crowdar.api.runners;

import com.intuit.karate.junit5.Karate;

public class ApiTestRunner {

    @Karate.Test
    Karate testMercadoLibreApi() {

        return Karate.run(
                "classpath:features/api/mercadolibre/getDepartments.feature"
        )
        .outputCucumberJson(true)
        .reportDir("reports/api");
    }
}
