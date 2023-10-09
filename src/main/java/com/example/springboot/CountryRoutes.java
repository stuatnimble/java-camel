package com.example.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;

import org.springframework.http.HttpHeaders;

@Component
public class CountryRoutes extends RouteBuilder {

    @Value("${server.port}")
    String serverPort;

    @Value("baeldung.api.path")
    String contextPath;

    @Override
    public void configure() throws Exception {
        rest("/api/")
                .get("/country")
                .consumes("application/json")
                .bindingMode(RestBindingMode.json)
                .produces("application/json")
                .to("direct:countryList");

        from("direct:countryList")
                .routeId("countryList")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                .setHeader(HttpHeaders.ACCEPT, simple("*/*"))
                .to("https://restcountries.com/v3.1/all?bridgeEndpoint=true")
                .convertBodyTo(String.class)
                .tracing();

    }
}
