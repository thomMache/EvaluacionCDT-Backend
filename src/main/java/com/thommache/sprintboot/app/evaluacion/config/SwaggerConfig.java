package com.thommache.sprintboot.app.evaluacion.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configurable
@EnableSwagger2
@EnableOpenApi
public class SwaggerConfig {

	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select().apis(RequestHandlerSelectors.basePackage("com.thommache.springboot.ap.evaluacion.controller"))
				.paths(PathSelectors.any()).build().useDefaultResponseMessages(false);
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Sistema de cotización de créditos api")
				.version("2.0")
				.license("Apache 2.0")
				.contact(new Contact("@thom_mache", "http://www.thommache.com", "contacto@thommache.com"))
				.build();
	}
	

	
}
