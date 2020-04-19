package com.bsg5.chapter4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/test-config.xml")
public class TestLifeCycle07 extends AbstractTestNGSpringContextTests {
    @Test
    public void testInitDestroy() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/config-07.xml");
        Object07 o1 = context.getBean(Object07.class);
        Assert.assertNotNull(Object07.semaphore);
        Assert.assertEquals(o1.getDatum(), "default");

        context.close();

        Assert.assertNull(Object07.semaphore);
    }
}
