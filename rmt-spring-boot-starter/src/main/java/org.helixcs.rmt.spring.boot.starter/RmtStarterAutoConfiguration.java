package org.helixcs.rmt.spring.boot.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@EnableConfigurationProperties(RmtStarterProperties.class)
@ConditionalOnClass(RequestMappingHandlerMapping.class)
@ConditionalOnProperty(prefix = "rmt.starter", name = "enable", havingValue = "true")
public class RmtStarterAutoConfiguration implements WebMvcConfigurer {
    
    @Autowired
    private TmpComponent tmpComponent;
    
    @Autowired
    private RmtStarterProperties rmtStarterProperties;
    
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String web = rmtStarterProperties.getWeb();
        tmpComponent.addMappings(web);
    }
}
