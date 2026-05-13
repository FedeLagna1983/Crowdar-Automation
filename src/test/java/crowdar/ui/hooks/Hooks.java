package crowdar.ui.hooks;

import crowdar.core.driver.DriverFactory;
import crowdar.core.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            ScreenshotUtils.takeScreenshot(
                    DriverFactory.getDriver(),
                    scenario.getName()
            );
        }

        DriverFactory.quitDriver();
    }
}
