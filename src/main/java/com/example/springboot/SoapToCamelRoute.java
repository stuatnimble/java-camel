package com.example.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class SoapToCamelRoute {

    @Bean
    public RouteBuilder soapRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:processSoapMessage")
                        .setHeader(Exchange.CONTENT_TYPE, simple("application/json"))
                        .process(new SoapProcessor());

                from("cxf:bean:soapEndpoint")
                        .to("log:receivedSoapMessage") // Log the received SOAP message
                        .to("direct:processSoapMessage"); // Route to further processing
            }
        };
    }
}
