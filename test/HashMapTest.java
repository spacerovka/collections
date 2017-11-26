import collections.ArrayList;
import collections.HashMap;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Svetotulichka on 07.11.2017.
 */
public class HashMapTest {

    private HashMap<String, String> baseMap;

    @Before
    public void init() {
        baseMap = new HashMap<String, String>();
        baseMap.put("one", "is good");
        baseMap.put("two", "is bad");
    }

    @Test
    public void testConstructorHashMap() {
        HashMap myMap = new HashMap(30, 0.9F);
        assertTrue(myMap.isEmpty());
    }

    @Test
    public void testConstructorFromMap() {
        HashMap mySecondMap = new HashMap(baseMap);
        assertEquals(2, mySecondMap.size());
        assertTrue(mySecondMap.containsKey("one"));
        assertTrue(mySecondMap.containsValue("is good"));
    }

    @Test
    public void clear() {
        baseMap.clear();
        assertFalse(baseMap.containsKey("one"));
    }

    @Test
    public void testClone() {
        Map<String, String> cloned = baseMap.clone();
        assertEquals(2, cloned.size());
        assertTrue(cloned.containsKey("one"));
        assertTrue(cloned.containsValue("is bad"));
    }

    @Test
    public void containsKey() {
        assertTrue(baseMap.containsKey("one"));
        assertFalse(baseMap.containsKey("none"));

    }

    @Test
    public void containsValue() {
        assertTrue(baseMap.containsValue("is bad"));
        assertFalse(baseMap.containsValue("none"));
    }

    @Test
    public void entrySet() {
        Set<Map.Entry<String, String>> entrySet = baseMap.entrySet();
        assertEquals("one", entrySet.iterator().next().getKey());
    }

    @Test
    public void isEmpty() {
        assertFalse(baseMap.isEmpty());
        assertTrue(new HashMap().isEmpty());
    }

    @Test
    public void keySet() {
        Set<String> keySet = baseMap.keySet();
        assertTrue(keySet.contains("one"));
    }


    @Test
    public void put() {
        baseMap.put("three", "new value");
        assertTrue(baseMap.containsKey("three"));
        assertTrue(baseMap.containsValue("new value"));
    }

    @Test
    public void putAll() {
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("3", "003");
        newMap.put("4", "004");
        baseMap.putAll(newMap);
        assertTrue(baseMap.containsKey("3"));
        assertTrue(baseMap.containsValue("003"));
        assertTrue(baseMap.containsKey("4"));
        assertTrue(baseMap.containsValue("004"));
        assertEquals(4, baseMap.size());

    }

    @Test
    public void remove() {
        baseMap.remove("one");
        assertEquals(1, baseMap.size());
        assertFalse(baseMap.containsKey("one"));
    }

    @Test
    //    Removes the entry for the specified key only if it is currently mapped to the specified value.
    public void removeWithValue() {
        baseMap.remove("one", "is good");
        assertEquals(1, baseMap.size());
        assertFalse(baseMap.containsKey("one"));

        baseMap.remove("two", "is good");
        assertEquals(1, baseMap.size());
        assertTrue(baseMap.containsKey("two"));
    }


    @Test
    public void values() {
        List<String> values = new ArrayList<>(baseMap.values());
        assertEquals("is good", values.get(0));
        assertEquals("is bad", values.get(1));
    }

    @Test
    public void nullKey() {
        baseMap.put(null, "null");
        assertEquals("null", baseMap.get(null));
        baseMap.remove(null);
        assertEquals(null, baseMap.get(null));
    }

}
