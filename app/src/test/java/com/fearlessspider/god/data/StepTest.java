package com.fearlessspider.god.data;

import com.fearlessspider.god.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for Step model
 */
public class StepTest extends Utils {
    private Step step;

    @Before
    public void setUp() {
        step = new Step("123", 10000);
    }

    @Test
    public void testSerializeThenDeserialize() throws IOException, ClassNotFoundException {
        byte[] serialized1 = serialize(step);
        byte[] serialized2 = serialize(step);

        Step deserialized1 = (Step) deserialize(serialized1);
        Step deserialized2 = (Step) deserialize(serialized2);

        Assert.assertEquals(deserialized1.getSteps(), deserialized2.getSteps());
        Assert.assertEquals(deserialized1.getId(), deserialized2.getId());
        Assert.assertEquals(step.getSteps(), deserialized1.getSteps());
        Assert.assertEquals(step.getSteps(), deserialized2.getSteps());
    }
}
