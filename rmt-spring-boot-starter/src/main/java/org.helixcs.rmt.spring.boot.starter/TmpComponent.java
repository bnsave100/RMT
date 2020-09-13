package org.helixcs.rmt.spring.boot.starter;

import lombok.SneakyThrows;
import org.helixcs.rmt.app.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Component
public class TmpComponent {
    
    @Autowired
    @Lazy
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    
    @SneakyThrows
    public void addMappings(final String web) {
        RequestMappingInfo requestMappingInfo = RequestMappingInfo.paths("/", web).methods(GET).build();
        Class<IndexController> indexControllerClass = IndexController.class;
        IndexController indexController = indexControllerClass.newInstance();
        Method method = indexControllerClass.getDeclaredMethod("index");
        method.setAccessible(true);
        requestMappingHandlerMapping.registerMapping(requestMappingInfo, indexController, method);
        
    }
    
}
