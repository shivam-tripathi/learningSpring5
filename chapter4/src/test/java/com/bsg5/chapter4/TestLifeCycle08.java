package com.bsg5.chapter4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertNotSame;

@ContextConfiguration(classes = { TestLifeCycle08Config.class })
public class TestLifeCycle08 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    @DataProvider
    Object[][] getReferences() {
        return new Object[][] {
                { "foo", true },
                { "bar", false }
        };
    }

    @Test(dataProvider = "getReferences")
    public void testReferenceTypes(String beanName, boolean isSingleton) {
        HasData obj1 = (HasData) context.getBean(beanName);
        String defaultValue = obj1.getDatum();
        obj1.setDatum(UUID.randomUUID().toString());

        HasData obj2 = (HasData) context.getBean(beanName);
        if (!isSingleton) {
            assertNotEquals(obj1, obj2);
            assertNotSame(obj1, obj2);
            assertEquals(defaultValue, obj2.getDatum());
            assertNotEquals(obj1.getDatum(), obj2.getDatum());
        } else {
            assertEquals(obj1, obj2);
            assertSame(obj1, obj2);
            assertNotEquals(defaultValue, obj2.getDatum());
            assertEquals(obj1.getDatum(), obj2.getDatum());
        }
    }
}
