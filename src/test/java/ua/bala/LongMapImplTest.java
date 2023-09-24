package ua.bala;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongMapImplTest {

    private LongMapImpl<String> longMap;

    @Before
    public void setUp() {
        longMap = new LongMapImpl<>();
    }

    @Test
    public void put() {
        longMap.put(1, "First");

        assertEquals(longMap.get(1), "First");

        assertEquals(longMap.size(), 1);
    }

    @Test
    public void resize() {
        for (int i = 0; i < 20; i++) {
            longMap.put(i, "First");
        }

        assertEquals(longMap.get(1), "First");

        assertEquals(longMap.size(), 20);
    }

    @Test
    public void putDuplicated() {
        longMap.put(1, "First");
        longMap.put(1, "Second");
        longMap.put(1, "Third");

        assertEquals(longMap.get(1), "Third");
        assertEquals(longMap.size(), 1);
    }

    @Test
    public void get() {
        assertNull(longMap.get(1));

        longMap.put(1, "First");

        assertEquals(longMap.get(1), "First");
    }

    @Test
    public void remove() {
        longMap.put(1, "First");

        assertEquals(longMap.get(1), "First");

        assertEquals(longMap.size(), 1);

        String removedFirst = longMap.remove(1);

        assertEquals(removedFirst, "First");

        assertNull(longMap.get(1));

        assertEquals(longMap.size(), 0);
    }

    @Test
    public void isEmpty() {
        assertTrue(longMap.isEmpty());

        longMap.put(1, "First");

        assertFalse(longMap.isEmpty());
    }

    @Test
    public void containsKey() {
        longMap.put(1, "First");

        assertTrue(longMap.containsKey(1));

        assertFalse(longMap.containsKey(2));

        longMap.put(2, "Second");

        assertTrue(longMap.containsKey(2));
    }

    @Test
    public void containsValue() {
        longMap.put(1, "First");

        assertTrue(longMap.containsValue("First"));

        assertFalse(longMap.containsValue("Second"));

        longMap.put(2, "Second");

        assertTrue(longMap.containsValue("Second"));
    }

    @Test
    public void keys() {
        longMap.put(1, "First");

        assertArrayEquals(longMap.keys(), new long[]{1});
    }

    @Test
    public void values() {
        longMap.put(1, "First");

        assertArrayEquals(longMap.values(), new String[]{"First"});
    }

    @Test
    public void size() {
        longMap.put(1, "First");

        assertEquals(longMap.size(), 1);

        longMap.put(2, "Second");

        assertEquals(longMap.size(), 2);

        longMap.remove(2);

        assertEquals(longMap.size(), 1);
    }

    @Test
    public void clear() {
        longMap.put(1, "First");
        longMap.put(2, "Second");
        longMap.put(3, "Third");

        assertEquals(longMap.size(), 3);

        longMap.clear();

        assertEquals(longMap.size(), 0);
    }
}