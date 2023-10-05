package com.example.springboot;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;

public class MyRouteProcess implements Processor {
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
}
