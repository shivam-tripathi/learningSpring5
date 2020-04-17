package com.bsg5.chapter3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.testng.annotations.Test;

public class MusicServiceRunner {
    @Test
    public static void runnerTest() {
        Class<?>[] configurations = new Class<?>[] {
            Configuration7.class,
            TestConfiguration.class
        };

        try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(configurations)) {
            for (String beanName: context.getBeanDefinitionNames()) {
                System.out.printf("%s\n", beanName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
