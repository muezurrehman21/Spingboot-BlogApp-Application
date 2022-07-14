package com.codewithmuez.blog.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private List<SecurityContext> securityContexts() {
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}

	private List<SecurityReference> sf() {

		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");

		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] { scope }));
	}

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors
				.any())
				.paths(PathSelectors.any())
				.build();

	}

	private ApiInfo getInfo() {
		return new ApiInfo("Blog App Application", "Developed By Muez From Khired Networks", "1.0",
				"Terms of Services About Our Company Khired Networks",
				new Contact("Muez ur Rehman", "https://khired.com", "muez.rehman@khired.com"), "License of APIs",
				"Api License URL", Collections.emptyList());
	}

//	@Bean
//	public OpenAPI openApiConfig() {
//		return new OpenAPI().info(apiInfo());
//	}
//	
//	public Info apiInfo() {
//		Info info = new Info();
//		info
//			.title("Blog App")
//			.description("Blog App System Swagger OpenAPI")
//			.version("v1.0.0");
//		return info;
//	}

}
