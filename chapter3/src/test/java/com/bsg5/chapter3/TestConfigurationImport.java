package com.bsg5.chapter3;

import com.bsg5.chapter3.mem03.CapLeadingNormalizer;
import com.bsg5.chapter3.mem03.SimpleNormalizer;
import com.bsg5.chapter3.mem04.MusicService4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

@ContextConfiguration(locations = "/config-04.xml")
public class TestConfigurationImport extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;

    @DataProvider
    Object[][] resources() {
        return new Object[][] {
            { "musicServiceTests" },
            { MusicServiceTests.class },
            { "foo" },
            { SimpleNormalizer.class },
            { "bar" },
            { CapLeadingNormalizer.class },
            { "musicService4" },
            { MusicService4.class }
        };
    }

    @Test
    public void printBeans() {
        List<String> beans = Arrays.asList(context.getBeanDefinitionNames());
        for (String bean: beans) System.out.printf("%s\n", bean);
        System.out.printf("\n\n");
    }

    @Test(dataProvider = "resources")
    public void validateResourcesExistence(Object resource) {
        if (resource instanceof String) {
            assertNotNull(context.getBean(resource.toString()));
        } else if (resource instanceof Class<?>) {
            assertNotNull(context.getBean((Class<?>) resource));
        } else {
            fail("Invalid resource type");
        }
    }
}
