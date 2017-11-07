import collections.LinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Svetotulichka on 06.11.2017.
 */
public class LinkedListTest {

    private List<String> collection;
    private LinkedList<String> myList;

    @Before
    public void init() {
        collection = new java.util.ArrayList<>();
        collection.add("collection_0");
        collection.add("collection_1");
        myList = new LinkedList<>();
        myList.add("one");
        myList.add("two");
    }

    @Test
    public void testConstroctorAndAdd() {
        List<String> myList = new LinkedList<>();
        myList.add("one");
        myList.add("two");
        assertEquals(2, myList.size());
    }

    @Test
    public void testCollectionConstructor() {
        List<String> myList = new LinkedList<>(collection);
        assertEquals(2, myList.size());
    }

    @Test
    public void addWithIndex() {

        myList.add(1, "three");
        assertEquals("three", myList.get(1));
    }

    @Test
    public void addCollection() {
        List<String> myList = new LinkedList<>();
        myList.addAll(collection);
        assertEquals("collection_1", myList.get(1));
    }

    @Test
    public void addAllWithIndex() {

        myList.addAll(1, collection);
        assertEquals("collection_0", myList.get(1));
        assertEquals("collection_1", myList.get(2));
        assertEquals(4, myList.size());
    }

    @Test
    public void addFirst() {
        LinkedList<String> myList = new LinkedList<>();
        myList.addFirst("first");
        assertEquals("first", myList.get(0));
    }

    @Test
    public void addLast() {
        LinkedList<String> myList = new LinkedList<>();
        myList.add("one");
        myList.addLast("last");
        assertEquals("last", myList.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void clear() {

        myList.clear();
        myList.get(1);
    }

    @Test
    public void cloneList() {

        List<String> secondList = (LinkedList) myList.clone();
        assertEquals("two", secondList.get(1));
    }

    @Test
    public void contains() {

        assertTrue(myList.contains("one"));
    }


    @Test
    public void descendingIterator() {

        Iterator<String> iterator = myList.descendingIterator();
        String result = "";
        while (iterator.hasNext()) {
            result = result + iterator.next();
        }
        assertEquals("twoone", result);
    }


    @Test
    public void element() {

        assertEquals("one", myList.element());
    }


    @Test
    public void getFirst() {

        assertEquals("one", myList.getFirst());
    }


    @Test
    public void getLast() {

        assertEquals("two", myList.getLast());
    }


    @Test
    public void indexOf() {
        assertEquals(1, myList.indexOf("two"));
        assertEquals(-1, myList.indexOf("none"));
    }


    @Test
    public void lastIndexOf() {
        myList.add("three");
        myList.add("two");
        assertEquals(3, myList.lastIndexOf("two"));
    }


    @Test
    public void listIterator() {
        LinkedList<String> myList = new LinkedList<>();
        myList.add("one");
        myList.add("two");
        Iterator<String> iterator = myList.listIterator(0);
        String result = "";
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals("onetwo", result);
    }


    @Test
    public void offer() {
        myList.offer("three");
        assertEquals("three", myList.get(2));
    }


    @Test
    public void offerFirst() {
        myList.offerFirst("three");
        assertEquals("three", myList.get(0));
    }


    @Test
    public void offerLast() {
        myList.offerLast("three");
        assertEquals("three", myList.get(2));
    }


    @Test
    public void peek() {
        assertEquals("one", myList.peek());
    }


    @Test
    public void peekFirst() {
        assertEquals("one", myList.peekFirst());
    }


    @Test
    public void peekLast() {
        assertEquals("two", myList.peekLast());
    }


    @Test
    public void poll() {
        assertEquals("one", myList.poll());
        assertEquals("two", myList.get(0));
    }


    @Test
    public void pollFirst() {
        assertEquals("one", myList.pollFirst());
        assertEquals("two", myList.get(0));
    }


    @Test
    public void pollLast() {
        assertEquals("two", myList.pollLast());
        assertEquals(1, myList.size());
    }


    @Test
    public void pop() {
        assertEquals("one", myList.pop());
        assertEquals(1, myList.size());
    }


    @Test
    public void push() {
        myList.push("three");
        assertEquals("three", myList.get(0));
    }


    @Test
    public void remove() {
        myList.remove();
        assertEquals("two", myList.get(0));
    }


    @Test
    public void removeByIndex() {
        myList.remove(0);
        assertEquals("two", myList.get(0));
    }


    @Test
    public void removeObject() {
        myList.remove("one");
        assertEquals("two", myList.get(0));
    }


    @Test
    public void removeFirst() {
        myList.removeFirst();
        assertEquals("two", myList.get(0));
    }


    @Test
    public void removeFirstOccurrence() {
        myList.removeFirstOccurrence("one");
        assertEquals("two", myList.get(0));
    }


    @Test
    public void removeLast() {
        myList.removeLast();
        assertEquals("one", myList.getLast());
    }


    @Test
    public void removeLastOccurrence() {
        myList.add("one");
        myList.add("three");
        myList.removeLastOccurrence("one");
        assertEquals("three", myList.get(2));
    }


    @Test
    public void set() {
        myList.set(1, "new");
        assertEquals("new", myList.get(1));
    }


    @Test
    public void toArray() {
        List<Integer> integerList = new LinkedList<>();
        integerList.add(new Integer(0));
        integerList.add(new Integer(1));
        Object[] numArray = integerList.toArray();
        assertEquals(1, numArray[1]);
    }


    @Test
    public void toExistingArray() {
        List<Integer> integerList = new LinkedList<>();
        integerList.add(new Integer(0));
        integerList.add(new Integer(1));
        Number[] numArray = integerList.toArray(new Number[5]);
        assertEquals(1, numArray[1]);
    }
}
