package com.example.springboot;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyRouteProcessTests {

    @Test
    void testMyRouteProcess() throws Exception {
        MyBean b = new MyBean();
        b.setId(789);
        b.setName("Bobby the bean whose id will be multiplied by 1000");
        CamelContext ctx = new DefaultCamelContext();
        Exchange ex = new DefaultExchange(ctx);
        ex.getIn().setBody(b);
        MyRouteProcess myRoute = new MyRouteProcess();
        myRoute.process(ex);
        Object result = ex.getIn().getBody();
        assertEquals(result.toString(), "<html>\n" +
                "<head>\n" +
                "<title>Hello: Lord Bobby the bean whose id will be multiplied by 1000</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Name: Hello: Lord Bobby the bean whose id will be multiplied by 1000</h1>\n" +
                "<p>ID: 789000</p>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
