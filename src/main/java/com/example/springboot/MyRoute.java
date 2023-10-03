package com.example.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;

import java.io.StringWriter;

import static org.apache.velocity.app.Velocity.getTemplate;

@Component
public class MyRoute extends RouteBuilder {
// https://www.baeldung.com/apache-camel-spring-boot

    @Value("${server.port}")
    String serverPort;

    @Value("baeldung.api.path")
    String contextPath;

    @Override
    public void configure() throws Exception {
        System.out.println("configure something");



        restConfiguration()
                .contextPath(contextPath)
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest("/api/")
                .id("api-route")
                .consumes("application/json")
                .post("/bean")
                .to("log")
                .bindingMode(RestBindingMode.json_xml)
                .type(MyBean.class)
                .to("direct:remoteService");

        from("direct:remoteService")
                .routeId("direct-route")
                .tracing()
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .to("log:bar")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MyBean bodyIn = (MyBean) exchange.getIn().getBody();
                        ExampleServices.example(bodyIn);

                        StringWriter w = new StringWriter();
                        VelocityContext context = new VelocityContext();
                        context.put("name", bodyIn.getName());
                        context.put("id", bodyIn.getId());
                        Velocity.mergeTemplate("webapp/templates/index.vm", "utf-8", context, w );

                        exchange.getIn().setBody(w.toString());
                    }
                });

    }
}
