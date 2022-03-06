package com.fearlessspider.god.data;

import com.fearlessspider.god.Utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for User model
 */
public class UserTest extends Utils {
    private User user;

    @Before
    public void setUp() {
        user = new User("123", "TestUser");
    }

    @Test
    public void testSerializeThenDeserialize() throws IOException, ClassNotFoundException {
        byte[] serialized1 = serialize(user);
        byte[] serialized2 = serialize(user);

        User deserialized1 = (User) deserialize(serialized1);
        User deserialized2 = (User) deserialize(serialized2);

        Assert.assertEquals(deserialized1.getUsername(), deserialized2.getUsername());
        Assert.assertEquals(deserialized1.getId(), deserialized2.getId());
        Assert.assertEquals(user.getUsername(), deserialized1.getUsername());
        Assert.assertEquals(user.getUsername(), deserialized2.getUsername());
    }
}
