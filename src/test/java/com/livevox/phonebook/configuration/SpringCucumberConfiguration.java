package com.livevox.phonebook.configuration;

import com.livevox.phonebook.utils.TestContext;
import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(loader = SpringBootContextLoader.class)
@ComponentScan(basePackages = {"com.livevox.phonebook"})
public class SpringCucumberConfiguration {

  @Before
  public void init() {
    TestContext.CONTEXT.reset();
  }
}
