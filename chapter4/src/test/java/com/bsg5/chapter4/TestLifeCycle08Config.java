package com.bsg5.chapter4;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestLifeCycle08Config {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    Object08 foo() {
        return new Object08();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    Object08 bar() {
        return new Object08();
    }
}
