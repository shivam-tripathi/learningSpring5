package com.bsg5.chapter4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(locations = "/test-config.xml")
public class TestLifeCycle06 extends AbstractTestNGSpringContextTests {
    @Test
    public void testInitDestroy() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/config-06.xml");
        Object06 obj = context.getBean(Object06.class);
        assertNotNull(Object06.semaphore);
        assertEquals(obj.getDatum(), "default");
        context.close();
        assertNull(Object06.semaphore);
    }
}
