package com.example.springboot;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SoapProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("SoapProcessor:");

        // Process the SOAP message here
        // You can access the SOAP message using exchange.getIn().getBody(String.class)

        try {
            var xml = exchange.getIn().getBody(String.class);
            JSONObject jsonObj = XML.toJSONObject(xml);

            System.out.println(jsonObj);
        } catch (JSONException e) {
            System.out.println("ERROR");
        }
        try {
            exchange.getIn().setBody("<main>xello world</main>");
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
