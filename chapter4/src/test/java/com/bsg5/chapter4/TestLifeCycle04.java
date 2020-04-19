package com.bsg5.chapter4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/test-config.xml")
public class TestLifeCycle04 extends AbstractTestNGSpringContextTests {
    @Test
    public void testInitAndDestroy() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/config-04.xml");
        Object04 o1 = context.getBean(Object04.class);
        Assert.assertNotNull(Object04.semaphore);
        Assert.assertEquals(o1.getDatum(), "default");

         context.close();

         Assert.assertNull(Object04.semaphore);
    }
}
