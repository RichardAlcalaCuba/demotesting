package examples;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import java.io.File;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;
import net.masterthought.cucumber.Configuration;

import java.io.File;

import net.masterthought.cucumber.ReportBuilder;

class ExamplesTest {

    // @Test
    // void testParallel() {
    //     Results results = Runner.path("classpath:examples")
    //             //.outputCucumberJson(true)
    //             .parallel(1);
    //     assertEquals(0, results.getFailCount(), results.getErrorMessages());
    // }

    @Test
    void testParallel() {
        Results results = Runner.path("classpath:examples")
                .outputCucumberJson(true)
                .karateEnv("dev")
                .parallel(1);
        generateReport(results.getReportDir());
        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
    }


    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "api-demo");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }

}
