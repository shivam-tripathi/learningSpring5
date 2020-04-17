package com.bsg5.chapter3;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import com.bsg5.chapter3.mem03.CapLeadingNormalizer;

public class TestCapLeadingNormalizer {
    Normalizer normalizer = new CapLeadingNormalizer();

    @DataProvider
    Object[][] data() {
        String expected = "This Is A Test";
        return new Object[][] {
            { "this is a test", expected },
            { " This IS a test ", expected },
            { "this    is   a test", expected }
        };
    }

    @Test(dataProvider = "data")
    public void testNormalization(String input, String expected) {
        assertEquals(normalizer.transform(input), expected);
    }
}
