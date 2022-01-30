package com.fearlessspider.god.data;

import com.fearlessspider.god.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ExerciseTest extends Utils {
    private Exercise exercise;

    @Before
    public void setUp() {
        exercise = new Exercise("123", "Comment", "track123");
    }

    @Test
    public void testSerializeThenDeserialize() throws IOException, ClassNotFoundException {
        byte[] serialized1 = serialize(exercise);
        byte[] serialized2 = serialize(exercise);

        Exercise deserialized1 = (Exercise) deserialize(serialized1);
        Exercise deserialized2 = (Exercise) deserialize(serialized2);

        Assert.assertEquals(deserialized1.getComment(), deserialized2.getComment());
        Assert.assertEquals(exercise.getComment(), deserialized1.getComment());
        Assert.assertEquals(exercise.getComment(), deserialized2.getComment());
    }
}
