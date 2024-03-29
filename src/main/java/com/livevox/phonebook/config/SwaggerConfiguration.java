package com.livevox.phonebook.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import com.google.common.base.Predicate;
import java.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  private static final String DETAIL_DESCRIPTION =
      "REST API for service endpoints. \n \n" + "Below is a list of available REST API.";


  @Bean
  public Docket swaggerSpringMvcPlugin() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("phonebook-service")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(paths())
        .build()
        .pathMapping("/")
        .directModelSubstitute(LocalDate.class, String.class)
        .genericModelSubstitutes(ResponseEntity.class);
  }

  private Predicate<String> paths() {
    return or(regex("/v1/contacts.*"));

  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("Overview")
        .description(DETAIL_DESCRIPTION)
        .termsOfServiceUrl("http://springfox.io")
        .license("Apache License Version 2.0")
        .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
        .version("2.0")
        .build();
  }
}