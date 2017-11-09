package collections;

import java.util.*;

/**
 * Created by Svetotulichka on 23.10.2017.
 */
public class HashMap<K, V>
        extends AbstractMap<K, V>
        implements Map<K, V> {

    private int size = 0;
    private Node<K, V>[] array;
    private float loadFactor;
    private EntrySet<K, V> entrySet = new EntrySet<>();

    static class Node<K, V> implements Map.Entry {

        private int hash;
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            hash = hash(key);
        }

        @Override
        public K getKey() {
            return key;
        }


        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(Object value) {
            return this.value = (V) value;
        }
    }

    class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            // TODO: 09.11.2017 implement entry iterator
            return null;
        }

        @Override
        public int size() {
            return size;
        }

        public void clear() {
            this.clear();
        }
    }

    public HashMap() {
        this(16, 0.75F);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, 0.75F);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        array = new Node[initialCapacity];
        this.loadFactor = loadFactor;
    }

    public HashMap(Map<? extends K, ? extends V> m) {
        this(m.size());
        putFromAnotherMap(m);

    }

    private void putFromAnotherMap(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    private void checkLoadFactorAndResize() {
        if (array.length * loadFactor >= size) {
            Node<K, V>[] newArr = new Node[array.length];
            System.arraycopy(array, 0, newArr, 0, newArr.length);
            array = newArr;
        }
    }

    public void clear() {
        size = 0;
        array = new Node[16];
        loadFactor = 0.75F;
    }


    public Object clone() {
        HashMap<K, V> newHashMap = null;
        try {
            newHashMap = (HashMap<K, V>) super.clone();
            newHashMap.clear();
            newHashMap.putFromAnotherMap(HashMap.this);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newHashMap;

    }


    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    public boolean containsValue(Object value) {
        // TODO: 09.11.2017
        //iterate for all array
        // for not null iterate from start to next and return if it is equal
        return false;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

    //    Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
    public V get(Object key) {
        Node<K, V> node = getNode(key);
        return node != null ? node.getValue() : null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Set<K> keySet() {
        return new KeyCollection();
    }

    class KeyCollection extends AbstractSet<K> {
        @Override
        public Iterator<K> iterator() {
            // TODO: 09.11.2017 imlement set iterator
            return null;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean contains(Object key) {
            // TODO: 09.11.2017
            return false;
        }
    }


    public void putAll(Map<? extends K, ? extends V> m) {
        putFromAnotherMap(m);
    }

    public V remove(Object key) {
        // TODO: 09.11.2017
        //check if it is in [] if has next - set next to this position
        //go further next and find element - set next to its position
        //return element value or null
        return null;
    }

    public V put(K key, V value) {
        // TODO: 09.11.2017
        //check if [size-1 & hash] is null and create new node
        // not nll - check hash and key - set element to it
        // go next until match of key-value or null
        //set element value to new data
        return null;
    }

    private Node<K, V> getNode(Object key) {
        // TODO: 09.11.2017
        // get node by hash and key
        //if node hash is eq and key is eq - return it
        //or go to node.next till hash and key is equal
        return null;
    }

    public int size() {
        return size;
    }

    public Collection<V> values() {
        return new ValueCollection();
    }

    class ValueCollection extends AbstractCollection<V> {

        @Override
        public Iterator<V> iterator() {
            // TODO: 09.11.2017 implement values iterator
            return null;
        }

        @Override
        public int size() {
            return size;
        }
    }

    private static int hash(Object element) {
        return element == null ? 0 : element.hashCode();
    }

}
