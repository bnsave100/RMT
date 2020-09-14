package org.helixcs.rmt.spring.boot.starter;

import java.lang.reflect.Method;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.helixcs.rmt.app.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@Configuration
@EnableConfigurationProperties(RmtStarterProperties.class)
@ConditionalOnClass(RequestMappingHandlerMapping.class)
@ConditionalOnProperty(prefix = "rmt.starter", name = "enable", havingValue = "true")
public class RmtStarterAutoConfiguration implements WebMvcConfigurer {

    @Autowired
    @Lazy
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private RmtStarterProperties rmtStarterProperties;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        addMappings(rmtStarterProperties.getWeb());
    }

    @SneakyThrows
    public void addMappings(final String webPoints) {
        RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths(webPoints).methods(GET).build();
        Class<IndexController> indexControllerClass = IndexController.class;
        IndexController indexController = indexControllerClass.newInstance();
        Method method = indexControllerClass.getDeclaredMethod("index");
        method.setAccessible(true);
        requestMappingHandlerMapping.registerMapping(requestMappingInfo, indexController, method);

    }

}
