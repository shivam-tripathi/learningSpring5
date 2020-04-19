package com.bsg5.chapter4;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Object06 extends HasData {
    static Object semaphore;

    @PostConstruct
    public void initialize() throws Exception {
        semaphore = new Object();
    }

    @PreDestroy
    public void dispose() throws Exception {
        semaphore = null;
        System.out.println("PreDestroy called");
    }
}
