package com.bsg5.chapter4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-02.xml")
public class TestLifeCycle02 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    @Test
    public void validateConstruction() {
        HasData o1 = context.getBean(HasData.class);
        Assert.assertEquals(o1.getDatum(), "Initial Value");
    }
}
