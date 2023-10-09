package com.example.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.StringWriter;

public class MyRouteProcess implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        MyBean bodyIn = (MyBean) exchange.getIn().getBody();

//        EmployeeResponse emp = WebClient.create().get().uri("http://localhost:8080/health")
//                .retrieve().bodyToMono(EmployeeResponse.class).block();
//
//        System.out.println(emp.getHello());
//        System.out.println(emp.getNow());

        ExampleServices.example(bodyIn);

        StringWriter w = new StringWriter();
        VelocityContext context = new VelocityContext();
        context.put("name", bodyIn.getName());
        context.put("id", bodyIn.getId());
        Velocity.mergeTemplate("webapp/templates/index.vm", "utf-8", context, w );

        exchange.getIn().setBody(w.toString());
    }
}
