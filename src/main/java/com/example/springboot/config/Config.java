package com.example.springboot.config;

import org.apache.camel.component.cxf.common.DataFormat;
        import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CxfEndpoint createdFileNotification(final Bus bus) {
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setBus(bus);
        endpoint.setWsdlURL("wsdl/ReceiveFileNotificationService.wsdl");
        endpoint.setBeanId("createdFileNotification");
        endpoint.setAddress("/createdFileNotification");
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
}
