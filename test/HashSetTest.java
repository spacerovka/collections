import collections.HashSet;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Svetotulichka on 13.11.2017.
 */
public class HashSetTest {

    private HashSet<String> baseSet;

    @Before
    public void init() {
        baseSet = new HashSet<String>();
        baseSet.add("one");
        baseSet.add("two");
    }

    @Test
    public void testConstructorsAndSize() {
        Set<String> testSet = new HashSet<>(20, 0.5F);
        assertEquals(0, testSet.size());
    }

    @Test
    public void testConstructorFromCollection() {
        Set<String> testSet = new HashSet<>(baseSet);
        assertEquals(2, testSet.size());
        assertTrue(testSet.contains("one"));
        assertTrue(testSet.contains("two"));
    }

    @Test
    public void add() {
        baseSet.add("three");
        assertTrue(baseSet.contains("three"));
    }

    @Test
    public void clear() {
        baseSet.clear();
        assertEquals(0, baseSet.size());
    }

    @Test
    public void testClone() {
        HashSet<String> cloned = baseSet.clone();
        assertEquals(2, cloned.size());
        assertTrue(cloned.contains("one"));
        assertTrue(cloned.contains("two"));
    }

    @Test
    public void contains() {
        assertTrue(baseSet.contains("one"));
        assertTrue(baseSet.contains("two"));

    }

    @Test
    public void isEmpty() {
        baseSet.remove("one");
        assertFalse(baseSet.isEmpty());
        baseSet.remove("two");
        assertTrue(baseSet.isEmpty());
    }

    @Test
    public void iterator() {
        Iterator<String> iterator = baseSet.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("one", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("two", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void remove() {
        baseSet.remove("one");
        assertFalse(baseSet.contains("one"));
    }
}
