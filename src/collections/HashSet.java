package collections;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Svetotulichka on 23.10.2017.
 */
public class HashSet<E> extends AbstractSet<E>
        implements Set<E>, Cloneable, Serializable {

    private HashMap<E, Byte> innerMap;
    private byte EXISTS = 1;


    public HashSet() {
        innerMap = new HashMap<>();
    }

    public HashSet(Collection<? extends E> c) {
        this();
        for (E element : c) {
            add(element);
        }
    }

    public HashSet(int initialCapacity) {
        innerMap = new HashMap<>(initialCapacity);
    }

    public HashSet(int initialCapacity, float loadFactor) {
        innerMap = new HashMap<>(initialCapacity, loadFactor);
    }

    public boolean add(E e) {
        return innerMap.put(e, EXISTS) != null;
    }

    public void clear() {
        innerMap = new HashMap<>(size());
    }

    public HashSet<E> clone() {
        HashSet<E> newSet = null;
        try {
            newSet = (HashSet<E>) super.clone();
            newSet.clear();
            for (E key : innerMap.keySet()) {
                newSet.add(key);
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newSet;
    }

    public boolean contains(Object o) {
        return innerMap.containsKey(o);
    }

    public boolean isEmpty() {
        return innerMap.size() == 0;
    }

    public Iterator<E> iterator() {
        return innerMap.keySet().iterator();
    }

    public boolean remove(Object o) {
        return innerMap.remove(o) != null;
    }

    public int size() {
        return innerMap.size();
    }
}
