package crowdar.core.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String scenarioName) {

        File screenshotsDir = new File("screenshots");

        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String cleanScenarioName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");

        String screenshotPath = "screenshots/" + cleanScenarioName + "_" + timestamp + ".png";

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenshotPath);

        try {
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Could not save screenshot", e);
        }

        return screenshotPath;
    }
}
