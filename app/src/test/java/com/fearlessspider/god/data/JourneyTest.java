package com.fearlessspider.god.data;

import com.fearlessspider.god.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for Journey model
 */
public class JourneyTest extends Utils {
    private Journey journey;

    @Before
    public void setUp() {
        journey = new Journey("123", "Programming", "user123");
    }

    @Test
    public void testSerializeThenDeserialize() throws IOException, ClassNotFoundException {
        byte[] serialized1 = serialize(journey);
        byte[] serialized2 = serialize(journey);

        Journey deserialized1 = (Journey) deserialize(serialized1);
        Journey deserialized2 = (Journey) deserialize(serialized2);

        Assert.assertEquals(deserialized1.getName(), deserialized2.getName());
        Assert.assertEquals(deserialized1.getId(), deserialized2.getId());
        Assert.assertEquals(deserialized1.getUserId(), deserialized2.getUserId());
        Assert.assertEquals(journey.getName(), deserialized1.getName());
        Assert.assertEquals(journey.getName(), deserialized2.getName());
    }
}
