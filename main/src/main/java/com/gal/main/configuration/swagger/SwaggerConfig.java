package com.gal.main.configuration.swagger;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import com.gal.api.controllers.OrderController;
import com.gal.api.controllers.OrderItemController;
import com.gal.api.controllers.PaymentController;
import com.gal.api.controllers.TagController;
import com.gal.authorization.controllers.UserController;
import com.gal.entities.enums.PaymentMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.gal.api.controllers.ClientController;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = {ClientController.class, UserController.class, OrderController.class, OrderItemController.class, TagController.class, PaymentController.class})
//@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENSE_TEXT = "License";
    private static final String TITLE = "Gal Finkelshtain REST Api";
    private static final String DESCRIPTION = "Gal Finekshtain REST Api to manage client, reports, etc...";


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(TITLE)
            .description(DESCRIPTION)
            .license(LICENSE_TEXT)
            .version(SWAGGER_API_VERSION)
            .build();
    }

    @SuppressWarnings("unchecked")
    private Predicate<String> bbCompanyPaths() {
        return or(regex(".*[/](api)*(orderitem|client|order|payment|tag)\\b[/]?.*"));
    }

    @SuppressWarnings("unchecked")
    private Predicate<String> bbAuthorizationPaths() {
        return or(regex(".*[/](api)*(auth|user)\\b[/]?.*"));

    }


    @Bean
    public Docket swaggerSpringMvcPluginForBusinessCustomer() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Authorization")
            .apiInfo(apiInfo())
            .select()
            .paths(bbAuthorizationPaths())
            .build()
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true);
        //.securitySchemes(Collections.singletonList(apiKey()));
    }




    @Bean
    public Docket businessApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Business")
            .apiInfo(apiInfo()) // api information above
            //.pathMapping("/") // actual path that serving in my api
            .select() //select this configuration
            .paths(bbCompanyPaths()) // anything after api include in the documentation
            .build()
        .securitySchemes(Collections.singletonList(apiKey()));
    }

    @Bean SecurityConfiguration security()
    {
        return new SecurityConfiguration(null, null, null, null, "Bearer access_token", ApiKeyVehicle.HEADER, "Authorization", ",");
    }


    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Bearer", "header");
    }

}
