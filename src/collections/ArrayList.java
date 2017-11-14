package collections;

import java.io.Serializable;
import java.util.*;


public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, Serializable {

    private Object[] list;
    private int size;

    public <E> ArrayList() {
        list = new Object[10];
        size = 0;
    }

    public <E> ArrayList(int capacity) {
        list = new Object[capacity];
        size = capacity;
    }

    public <E> ArrayList(Collection<? extends E> collection) {
        size = collection.size();
        list = new Object[size];
        int index = 0;
        for (E element : collection) {
            list[index] = element;
            index++;
        }
    }

    private void manageListCapacity() {
        if (size == list.length) {
            Object[] resizedArray = new Object[size * 2];
            for (int i = 0; i < list.length; i++) {
                resizedArray[i] = list[i];
            }
            list = resizedArray;
        }
    }

    @Override
    public boolean add(E e) {
        manageListCapacity();
        list[size] = e;
        size += 1;
        return true;
    }


    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        size++;
        for (int i = size - 1; i < index; i++) {
            list[i] = list[i - 1];
        }
        list[index] = element;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        ensureCapacity(c.size() + size);
        for (Object element : c) {
            list[size] = element;
            size++;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        int addedSize = c.size();
        if (addedSize == 0) return false;

        size += c.size();
        ensureCapacity(size);
        for (int i = size - 1; i >= size - addedSize; i--) {
            list[i] = list[i - addedSize];
        }
        int replceIndex = index;
        for (Object element : c) {
            list[replceIndex] = element;
            replceIndex++;
        }
        return true;
    }


    @Override
    public void clear() {
        list = new Object[10];
        size = 0;
    }

    @Override
    public Object clone() {
        List<E> newList = new ArrayList<E>();
        for (int i = 0; i < size; i++) {
            newList.add((E) list[i]);
        }
        return newList;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) return true;
        }
        return false;
    }

    public void ensureCapacity(int minCapacity) {
        if (list.length < minCapacity) {
            Object[] resizedArray = new Object[minCapacity];
            for (int i = 0; i < list.length; i++) {
                resizedArray[i] = list[i];
            }
            list = resizedArray;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        return (E) list[index];
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? get(i) == null : o.equals(get(i))) return i;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < size; i++) {
            if (o == null ? get(i) == null : o.equals(get(i))) lastIndex = i;
        }
        return lastIndex;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        E element = (E) list[index];
        list[index] = null;
        size--;
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        return element;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("specified collection is null");
        boolean result = false;
        for (Object element : c) {
            for (int i = 0; i < size; i++) {
                if (list[i].equals(element)) {
                    remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        int diff = toIndex - fromIndex;
        if (diff > 0) {
            for (int i = fromIndex; i < toIndex; i++) {
                remove(i);
            }
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) throw new NullPointerException("specified collection is null");
        boolean result = false;
        Object[] newList = new Object[c.size()];
        int newListIndex = 0;
        for (int i = 0; i < size; i++) {
            for (Object element : c) {
                if (list[i].equals(element)) {
                    newList[newListIndex] = element;
                    newListIndex++;
                }
            }
        }
        if (newListIndex > 0) {
            size = newListIndex;
            list = newList;
            return true;
        }
        return false;
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        list[index] = element;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size) throw new IndexOutOfBoundsException();
        if (fromIndex > toIndex) throw new IllegalArgumentException();
        List<E> newList = new ArrayList<>();
        if (fromIndex == toIndex) return newList;
        for (int i = fromIndex; i < toIndex; i++) {
            newList.add((E) list[i]);
        }
        return newList;
    }

    //    Returns an array containing all of the elements in this list in proper sequence (from first to last element).
    public Object[] toArray() {
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = (E) list[i];
        }
        return array;
    }

    /**
     * if the list fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the specified array and the size of this list.
     * Throws:
     * ArrayStoreException - if the runtime type of the specified array is not a supertype of the runtime type of every element in this list
     * NullPointerException - if the specified array is null
     */
    public <T> T[] toArray(T[] a) {
        if (a == null) throw new NullPointerException("Specified array is null.");
        if (a.length < size) {
            a = (T[]) new Object[size];
        }
        try {
            for (int i = 0; i < size; i++) {
                a[i] = (T) list[i];
            }
        } catch (ClassCastException e) {
            throw new ArrayStoreException("The type of the specified array is not a supertype of elements in this list.");
        }
        return a;
    }

    public void trimToSize() {
        Object[] resizedArray = new Object[size];
        for (int i = 0; i < list.length; i++) {
            resizedArray[i] = list[i];
        }
        list = resizedArray;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ArrayIterator<E>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        return new ArrayIterator<E>(index);
    }

    public class ArrayIterator<E> implements ListIterator<E> {

        private int currentIndex = 0;

        public ArrayIterator(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        public ArrayIterator() {
            this(-1);
        }

        @Override
        public boolean hasNext() {
            try {
                next();
                currentIndex--;
            } catch (NoSuchElementException e) {
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            if (currentIndex + 1 == size) throw new NoSuchElementException();
            currentIndex++;
            return (E) list[currentIndex];
        }


        @Override
        public boolean hasPrevious() {
            try {
                previous();
                currentIndex++;
            } catch (NoSuchElementException e) {
                return false;
            }
            return true;
        }

        @Override
        public E previous() {
            if (currentIndex - 1 == -1) throw new NoSuchElementException();
            currentIndex--;
            return (E) list[currentIndex];
        }

        @Override
        public int nextIndex() {
            return currentIndex == (size - 1) ? size : currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
