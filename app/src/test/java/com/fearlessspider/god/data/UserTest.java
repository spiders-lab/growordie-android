package com.fearlessspider.god.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User("123","TestUser");
    }

    @Test
    public void testConsistency() throws IOException, ClassNotFoundException {
        byte[] serialized1 = serialize(user);
        byte[] serialized2 = serialize(user);

        User deserialized1 = (User)deserialize(serialized1);
        User deserialized2 = (User)deserialize(serialized2);

        Assert.assertEquals(deserialized1.getUsername(), deserialized2.getUsername());
        Assert.assertEquals(user.getUsername(), deserialized1.getUsername());
        Assert.assertEquals(user.getUsername(), deserialized2.getUsername());
    }

    private static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = new ObjectOutputStream(b);
        o.writeObject(obj);
        return b.toByteArray();
    }

    private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = new ObjectInputStream(b);
        return o.readObject();
    }
}
