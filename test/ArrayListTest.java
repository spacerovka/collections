import collections.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Svetotulichka on 28.10.2017.
 */
@RunWith(JUnit4.class)
public class ArrayListTest {

    private ArrayList<String> list;
    private java.util.ArrayList collection;

    @Before
    public void init() {
        list = new ArrayList<String>();
        list.add("zero");
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");

        collection = new java.util.ArrayList();
        collection.add("collection_0");
        collection.add("collection_1");
    }

    @Test
    public void testConstructorAndGet() {

        List<String> myList = new ArrayList<>(collection);
        assertEquals("collection_1", myList.get(1));
    }

    @Test
    public void testSize() {
        assertEquals(5, list.size());
    }

    @Test
    public void add() {
        list.add("five");
        assertEquals(list.get(5), "five");
    }

    @Test
    public void addWithIndex() {
        list.add(2, "five");
        assertEquals(list.get(2), "five");
    }

    @Test
    public void addAll() {
        list.addAll(collection);
        assertEquals("collection_1", list.get(6));
    }

    @Test
    public void addAllWithIndex() {
        list.addAll(1, collection);
        assertEquals("four", list.get(6));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAllWithIndexException() {
        list.addAll(100, collection);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void clear() {
        list.clear();
        list.get(3);
    }

    @Test
    public void testClone() {
        List<String> newList = (List<String>) list.clone();
        list.set(0, "undefined");
        assertEquals("zero", newList.get(0));
    }

    @Test
    public void contains() {
        assertTrue(list.contains("zero"));
        assertFalse(list.contains("undefined"));
    }

    @Test
    public void indexOf() {
        assertEquals(0, list.indexOf("zero"));
    }

    @Test
    public void isEmpty() {
        list.clear();
        assertTrue(list.isEmpty());
    }


    @Test
    public void lastIndexOf() {
        list.add("zero");
        assertEquals(5, list.lastIndexOf("zero"));
    }

    @Test
    public void removeIndex() {
        list.remove(0);
        assertEquals("one", list.get(0));
    }

    @Test
    public void removeObject() {
        list.remove("zero");
        assertEquals("one", list.get(0));
    }

    @Test
    public void removeAll() {
        collection.clear();
        collection.add("zero");
        collection.add("one");
        list.removeAll(collection);
        assertEquals("two", list.get(0));
    }

    @Test
    public void retainAll() {
        collection.clear();
        collection.add("zero");
        collection.add("two");
        collection.add("six");
        list.retainAll(collection);
        assertEquals("zero", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void set() {
        list.set(0, "newZero");
        assertEquals("newZero", list.get(0));
    }

    @Test
    public void subList() {
        List<String> newList = list.subList(1, 2);
        assertEquals("one", newList.get(0));
    }

    @Test
    public void toArray() {
        Object[] array = list.toArray();
        assertEquals("zero", array[0]);
    }

    @Test
    public void toParticularArray() {
        String[] array = list.toArray(new String[10]);
        assertEquals("zero", array[0]);
    }

    @Test
    public void listIterator() {
        Iterator<String> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("zero", iterator.next());
    }
}
