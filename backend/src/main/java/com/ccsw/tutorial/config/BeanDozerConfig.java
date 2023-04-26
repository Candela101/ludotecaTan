package com.ccsw.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccsw
 * 
 */
@Configuration
public class BeanDozerConfig {

    @Bean
    public CustomDozerMapper getCustomDozerMapper() {
        return new CustomDozerMapper();
    }

}
