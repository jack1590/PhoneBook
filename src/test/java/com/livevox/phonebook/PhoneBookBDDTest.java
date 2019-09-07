package com.livevox.phonebook;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",
    plugin = {"json:target/cucumber-report.json",
        "pretty", "html:target/cucumber-html-report"},
    glue = {"com.livevox.phonebook.configuration",
        "com.livevox.phonebook.stepdefs"})
public class PhoneBookBDDTest {

}
