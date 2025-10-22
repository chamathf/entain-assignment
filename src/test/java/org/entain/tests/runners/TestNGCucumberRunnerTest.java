package org.entain.tests.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.entain.tests.stepdefinitions", "org.entain.tests.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        monochrome = true
)
@Listeners({org.entain.tests.retry.RetryTransformer.class})
public class TestNGCucumberRunnerTest extends AbstractTestNGCucumberTests {
}
