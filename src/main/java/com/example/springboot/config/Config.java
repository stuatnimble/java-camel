package com.example.springboot.config;

import org.apache.camel.component.cxf.common.DataFormat;
import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class Config {
    @Value("http://localhost:4300")
    private String addressBaseUrl;

    @Bean
    public VelocityEngine velocityEngine() {
        var vel = new VelocityEngine();
        vel.init();
        return vel;
    }

    @Bean
    public CxfEndpoint createdFileNotification(final Bus bus) {
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setBus(bus);
        endpoint.setWsdlURL("wsdl/HelloService.wsdl");
        endpoint.setBeanId("createdFileNotification");
        endpoint.setAddress("/createdFileNotification");
        endpoint.setDataFormat(DataFormat.PAYLOAD);

        return endpoint;
    }

    @Bean
    public CxfEndpoint soapEndpoint(final Bus bus) {
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setBus(bus);
//        endpoint.setWsdlURL("wsdl/HelloService.wsdl");
        endpoint.setBeanId("soapEndpoint");
        endpoint.setAddress("/soapEndpoint");
        endpoint.setDataFormat(DataFormat.PAYLOAD);

        return endpoint;
    }

    @Bean
    public ServletRegistrationBean<CXFServlet> servletRegistrationBean() {
        ServletRegistrationBean<CXFServlet> servlet = new ServletRegistrationBean<>
                (new CXFServlet(), "/cxf/*");
        servlet.setName("CXFServlet");
        return servlet;
    }

    @Bean
    public SpringBus cxf() {return new SpringBus();}

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl(addressBaseUrl).build();
    }

//    @Bean
//    public RegisterResponse registerUser(UserDto userDto) {
//        Mono<String> responseMono = webClient.post()
//                .uri(endpointPath)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(user), UserDto.class)
//                .retrieve()
//                .bodyToMono(RegisterResponse.class);
//
//        responseMono.subscribe(response -> {
//            // handle the response
//        }, error -> {
//            // handle the error
//        });
//    }
}
