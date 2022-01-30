package com.fearlessspider.god.data;

import com.fearlessspider.god.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TrackTest extends Utils {
    private Track track;

    @Before
    public void setUp() {
        track = new Track("123", "Python", "journey123");
    }

    @Test
    public void testSerializeThenDeserialize() throws IOException, ClassNotFoundException {
        byte[] serialized1 = serialize(track);
        byte[] serialized2 = serialize(track);

        Track deserialized1 = (Track) deserialize(serialized1);
        Track deserialized2 = (Track) deserialize(serialized2);

        Assert.assertEquals(deserialized1.getName(), deserialized2.getName());
        Assert.assertEquals(track.getName(), deserialized1.getName());
        Assert.assertEquals(track.getName(), deserialized2.getName());
    }
}
