import collections.HashMap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        HashMap myMap = new HashMap();
        assertTrue(myMap.isEmpty());
        HashMap mySecondMap = new HashMap(baseMap);
        assertEquals(2, mySecondMap.size());
        assertTrue(mySecondMap.containsKey("one"));
        assertTrue(mySecondMap.containsValue("is good"));
    }

    @Test
    //    Removes all of the mappings from this map.
    public void clear() {
    }

    @Test
    //    Returns a shallow copy of this HashMap instance: the keys and values themselves are not cloned.
    public void testClone() {
    }

    @Test
    //    Returns true if this map contains a mapping for the specified key.
    public void containsKey() {
    }

    @Test
    //    Returns true if this map maps one or more keys to the specified value.
    public void containsValue() {
    }

    @Test
    //    Returns a Set view of the mappings contained in this map.
    public void entrySet() {
    }

    @Test
    //    Returns true if this map contains no key-value mappings.
    public void isEmpty() {
    }

    @Test
    //    Returns a Set view of the keys contained in this map.
    public void keySet() {
    }


    @Test
    //    Associates the specified value with the specified key in this map.
    public void put() {
    }

    @Test
    //    Copies all of the mappings from the specified map to this map.
    public void putAll() {
    }

    @Test
    //    Removes the mapping for the specified key from this map if present.
    public void remove() {
    }

    @Test
    //    Removes the entry for the specified key only if it is currently mapped to the specified value.
    public void removeWithValue() {
    }

    @Test
    //    Replaces the entry for the specified key only if it is currently mapped to some value.
    public void replace() {
    }

    @Test
    //    Replaces the entry for the specified key only if currently mapped to the specified value.
    public void replaceWithValue() {
    }

    @Test
    //    Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception.
    public void replaceAll() {
    }

    @Test
    //    Returns a Collection view of the values contained in this map.
    public void values() {
    }

}
