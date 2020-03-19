package com.br.Meeting.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class ConfigSwageer {
	
	  @Bean
	  public Docket greetingApi() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.br.Meeting"))
	        .paths(PathSelectors.any())
	        .build()
	        .apiInfo(metaInfo());

	  }

	  private ApiInfo metaInfo() {
	    
		  ApiInfo apiInfo = new ApiInfo(
	        "Meeting API REST",
	        "Api for scheduling meeting rooms ",
	        "1.0",
	        "Terms of Service",
	        new Contact(" Brenda Alexia e Andressa", "https://github.com/lexie2510 , https://github.com/Andressa15",
	        		"brendinha.ae@gmail.com"),
	        "Apache License Version 2.0",
	        "http://www.apache.org/licesen.html", new ArrayList<VendorExtension>());
		  
		  return apiInfo;
	  }	

}
