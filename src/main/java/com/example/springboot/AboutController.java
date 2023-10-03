package com.example.springboot;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;

import static org.apache.velocity.app.Velocity.getTemplate;

@RestController
public class AboutController {
    @GetMapping("/about")
    public String index() {

        StringWriter w = new StringWriter();
        VelocityContext context = new VelocityContext();
        Velocity.mergeTemplate("webapp/templates/index.vm", "utf-8", context, w );
        return w.toString();
    }

}
