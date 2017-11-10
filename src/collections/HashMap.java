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
            Node<K, V>[] oldArr = array;
            array = newArr;
            for (Node node : oldArr) {
                if (node != null) {
                    put((K) node.getKey(), (V) node.getValue());
                    while (node.next != null) {
                        put((K) node.next.getKey(), (V) node.next.getValue());
                    }
                }
            }
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
        for (int i = 0; i < array.length; i++) {
            Node<K, V> existingNode = array[i];
            if (existingNode != null) {
                if (existingNode.getValue() == null ? value == null : existingNode.getValue().equals(value)) {
                    return true;
                }
            }
            while (existingNode.next != null) {
                Node<K, V> nextNode = existingNode.next;
                if (existingNode.getValue() == null ? value == null : existingNode.getValue().equals(value)) {
                    return true;
                }
            }

        }
        return false;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

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
            int keyHash = hash(key);
            int index = keyHash & (array.length - 1);
            Node<K, V> existingNode = array[index];
            if (existingNode != null) {
                if (existingNode.hash == keyHash && existingNode.getKey().equals(key)) {
                    return true;
                } else {
                    while (existingNode.next != null) {
                        Node nextNode = existingNode.next;
                        if (nextNode.hash == keyHash && nextNode.getKey().equals(key)) {
                            return true;
                        }
                        existingNode = nextNode;
                    }
                }
            }
            return false;
        }
    }


    public void putAll(Map<? extends K, ? extends V> m) {
        putFromAnotherMap(m);
    }

    public V remove(Object key) {
        int keyHash = hash(key);
        int index = keyHash & (array.length - 1);
        Node<K, V> existingNode = array[index];
        if (existingNode != null) {
            if (existingNode.hash == keyHash && existingNode.getKey().equals(key)) {
                array[index] = existingNode.next;
                size--;
                return existingNode.getValue();
            } else {
                while (existingNode.next != null) {
                    Node nextNode = existingNode.next;
                    if (nextNode.hash == keyHash && nextNode.getKey().equals(key)) {
                        existingNode.next = nextNode.next;
                        size--;
                        return (V) nextNode.getValue();
                    }
                    existingNode = nextNode;
                }
            }
        }
        return null;
    }

    public V put(K key, V value) {
        checkLoadFactorAndResize();
        int keyHash = hash(key);
        int index = keyHash & (array.length - 1);
        Node<K, V> existingNode = array[index];
        if (existingNode == null) {
            array[index] = new Node<K, V>(key, value, null);
            size++;
            return value;
        } else {
            if (existingNode.hash == keyHash && existingNode.getKey().equals(key)) {
                V prevValue = existingNode.getValue();
                existingNode.setValue(value);
                size++;
                return prevValue;
            } else {
                while (existingNode.next != null) {
                    Node nextNode = existingNode.next;
                    if (nextNode.hash == keyHash && nextNode.getKey().equals(key)) {
                        V prevValue = (V) nextNode.getValue();
                        nextNode.setValue(value);
                        size++;
                        return prevValue;
                    }
                    existingNode = nextNode;
                }
                existingNode.next = new Node<K, V>(key, value, null);
                size++;
                return value;
            }
        }
    }

    private Node<K, V> getNode(Object key) {
        int keyHash = hash(key);
        int index = keyHash & (array.length - 1);
        Node<K, V> existingNode = array[index];
        if (existingNode != null) {
            if (existingNode.hash == keyHash && existingNode.getKey().equals(key)) {
                return existingNode;
            } else {
                while (existingNode.next != null) {
                    Node nextNode = existingNode.next;
                    if (nextNode.hash == keyHash && nextNode.getKey().equals(key)) {
                        return nextNode;
                    }
                    existingNode = nextNode;
                }
            }
        }
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
