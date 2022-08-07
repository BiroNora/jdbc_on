package com.norab.show.actor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PersonTest {

    @Test
    void testEquals() {
        Person actor = new Person();
        Person actor0 = new Person();
        Person actor1 = new Person("Johnny Depp", (short) 1963);
        Person actor2 = new Person("Johnny Depp", null);
        Person actor3 = new Person("Lugosi Béla", (short) 1882, (short) 1956);
        Person actor4 = new Person("Lugosi Béla", null, (short) 1956);
        Person actor5 = new Person("Lugosi Béla", (short) 1882, null);
        Person actor6 = new Person("Lugosi Béla", null, null);

        assertEquals(actor0, actor0);
        assertEquals(actor1, actor2);
        assertEquals(actor3, actor4);
        assertEquals(actor3, actor5);
        assertEquals(actor3, actor6);

        assertNotEquals(actor, actor0);
        assertNotEquals(actor0, actor1);
        assertNotEquals(actor6, actor2);
    }
}
