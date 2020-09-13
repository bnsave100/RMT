package org.helixcs.rmt.spring.boot.starter;

import org.helixcs.rmt.app.configuration.AppConfiguration;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Configuration
@Import(AppConfiguration.class)
public class Application {
    
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.SERVLET).run(args);
        
//        TmpComponent bean = context.getBean(TmpComponent.class);
//        bean.addMappings();
    }
    
}
