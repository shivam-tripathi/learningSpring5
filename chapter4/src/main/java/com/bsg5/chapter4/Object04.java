package com.bsg5.chapter4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Object04 extends HasData implements InitializingBean, DisposableBean{
    static Object semaphore = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        semaphore = new Object();
    }

    @Override
    public void destroy() throws Exception {
        semaphore = null;
    }
}