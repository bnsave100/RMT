package org.helixcs.rmt.app.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * @Email: zhangjian12424@gmail.com.
 * @Author: helicxs
 * @Date: 6/17/2020.
 * @Desc:
 */
@SpringBootApplication(scanBasePackages = {"org.helixcs.rmt"})
@EnableWebSocket
public class AppConfiguration implements CommandLineRunner {
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println(":)");
    }
}
