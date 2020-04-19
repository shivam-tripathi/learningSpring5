package com.bsg5.chapter4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/test-config.xml")
public class TestLifeCycle03 extends AbstractTestNGSpringContextTests {
    @Test
    public void testInitDestroyMethods() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/config-03.xml");
        Object03 o1 = context.getBean(Object03.class);
        Assert.assertNotNull(Object03.semaphore);
        Assert.assertEquals(o1.getDatum(), "default");

         context.close();

         Assert.assertNull(Object03.semaphore);
    }
}
