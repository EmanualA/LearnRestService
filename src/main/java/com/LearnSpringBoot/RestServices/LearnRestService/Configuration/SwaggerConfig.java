package com.LearnSpringBoot.RestServices.LearnRestService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    public static final Contact DEFAULT_GENERATED_CONTACT
            = new Contact(
            "Emanual Alby",
            "http://www.LearnSpring.com",
            "emanual.alby@gmail.com");
    
    public static final ApiInfo DEFAULT_API_INFO
            = new ApiInfo(
            "Wonderful Lean springboot title",
            "Wonderful Lean springboot title",
            "1.0",
            "urn:tos",
            DEFAULT_GENERATED_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0",
            new ArrayList<>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES
            = new HashSet<String>(Arrays.asList("application/XML", "application/JSON"));

    @Bean
    public Docket swaggerDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.LearnSpringBoot.RestServices.LearnRestService.api"))
                .paths(PathSelectors.any())
                .build();
    }
}
