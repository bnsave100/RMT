package org.helixcs.rmt.examples.spring.starter;

import org.helixcs.rmt.app.configuration.RmtConfiguration;
import org.helixcs.rmt.expand.ExpandConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import({RmtConfiguration.class, ExpandConfiguration.class})
public class Application {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.SERVLET).run(args);
    }
    
}
