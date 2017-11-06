package collections;

import java.util.*;


public class ArrayList<E> implements List<E> {


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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        for (Object element : list) {
            if (element.equals(object)) return true;
        }
        return false;
    }


    @Override
    public E[] toArray() {
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
    @Override
    public Object[] toArray(Object[] array) {
        if (array == null) throw new NullPointerException("Specified array is null.");
        if (array.length < size) {
            array = new Object[size];
        }
        try {
            for (int i = 0; i < size; i++) {
                array[i] = list[i];
            }
        } catch (ClassCastException e) {
            throw new ArrayStoreException("The type of the specified array is not a supertype of elements in this list.");
        }
        return array;
    }

    @Override
    public boolean add(E element) {
        manageListCapacity();
        list[size] = element;
        size += 1;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     */
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

    private void manageListCapacity() {
        if (size == list.length) {
            Object[] resizedArray = new Object[size * 2];
            for (int i = 0; i < list.length; i++) {
                resizedArray[i] = list[i];
            }
            list = resizedArray;
        }
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * If the list does not contain the element, it is unchanged.
     * More formally, removes the element with the lowest index i such that
     * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists).
     * Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index > 0) {
            size--;
            for (int i = index; i < size; i++) {
                list[i] = list[i + 1];
            }
            return true;
        }
        return false;
    }

    /**
     * Throws:
     * NullPointerException - if the specified collection is null
     */
    @Override
    public boolean addAll(Collection c) {
        manageListCapacity();
        for (Object element : c) {
            list[size] = element;
            size++;
        }
        return false;
    }

    /**
     * Inserts all of the elements in the specified collection into this list,
     * starting at the specified position.
     * Shifts the element currently at that position (if any)
     * and any subsequent elements to the right (increases their indices).
     * The new elements will appear in the list in the order that they are returned by the specified collection's iterator.
     */
    @Override
    public boolean addAll(int index, Collection c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        int addedSize = c.size();
        if (addedSize == 0) return false;
        size += c.size();
        for (int i = size - 1; i < size - addedSize; i++) {
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
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        return (E) list[index];
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
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index is out of range");
        }
        E element = (E) list[index];
        list[index] = null;
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        return element;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     * More formally, returns the lowest index i such that
     * (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? get(i) == null : o.equals(get(i))) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(o)) lastIndex = i;
        }
        return lastIndex;
    }


    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * (If fromIndex and toIndex are equal, the returned list is empty.)
     * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations.
     * Throws:
     * IndexOutOfBoundsException - if an endpoint index value is out of range (fromIndex < 0 || toIndex > size)
     * IllegalArgumentException - if the endpoint indices are out of order (fromIndex > toIndex)
     */

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

    /**
     * removes from this list all of its elements that are not contained in the specified collection.
     * Throws:
     * ClassCastException - if the class of an element of this list is incompatible with the specified collection (optional)
     * NullPointerException - if this list contains a null element and the specified collection does not permit null elements (optional), or if the specified collection is null
     */
    @Override
    public boolean retainAll(Collection c) {
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

    /**
     * Removes from this list all of its elements that are contained in the specified collection.
     * NullPointerException -  if the specified collection is null
     */
    @Override
    public boolean removeAll(Collection c) {
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

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     * This implementation iterates over the specified collection, checking each element returned by the iterator in turn to see if it's contained in this collection.
     * If all elements are so contained true is returned, otherwise false.
     * Throws:
     * ClassCastException - if the types of one or more elements in the specified collection are incompatible with this collection (optional)
     * NullPointerException - if the specified collection contains one or more null elements and this collection does not permit null elements (optional), or if the specified collection is null.
     */
    @Override
    public boolean containsAll(Collection c) {
        if (c == null) throw new NullPointerException("specified collection is null");
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }


    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ArrayIterator<>();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        return new ArrayIterator<>(index);
    }

    public class ArrayIterator<E> implements ListIterator<E> {

        private int currentIndex = 0;

        public ArrayIterator(int currentIndex) {
            this.currentIndex = currentIndex;
        }

        public ArrayIterator() {
            this(0);
        }

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the forward direction. (In other words,
         * returns {@code true} if {@link #next} would return an element rather
         * than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the forward direction
         */
        @Override
        public boolean hasNext() {
            try {
                next();
            } catch (NoSuchElementException e) {
                return false;
            }
            return true;
        }

        /**
         * Returns the next element in the list and advances the cursor position.
         * This method may be called repeatedly to iterate through the list,
         * or intermixed with calls to {@link #previous} to go back and forth.
         * (Note that alternating calls to {@code next} and {@code previous}
         * will return the same element repeatedly.)
         *
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        @Override
        public E next() {
            if (++currentIndex == size) throw new NoSuchElementException();
            return (E) list[currentIndex];
        }


        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the reverse direction.  (In other words,
         * returns {@code true} if {@link #previous} would return an element
         * rather than throwing an exception.)
         *
         * @return {@code true} if the list iterator has more elements when
         * traversing the list in the reverse direction
         */
        @Override
        public boolean hasPrevious() {
            try {
                previous();
            } catch (NoSuchElementException e) {
                return false;
            }
            return true;
        }

        /**
         * Returns the previous element in the list and moves the cursor
         * position backwards.  This method may be called repeatedly to
         * iterate through the list backwards, or intermixed with calls to
         * {@link #next} to go back and forth.  (Note that alternating calls
         * to {@code next} and {@code previous} will return the same
         * element repeatedly.)
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous
         *                                element
         */
        @Override
        public E previous() {
            if (--currentIndex == -1) throw new NoSuchElementException();
            return (E) list[currentIndex];
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #next}. (Returns list size if the list
         * iterator is at the end of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code next}, or list size if the list
         * iterator is at the end of the list
         */
        @Override
        public int nextIndex() {
            return currentIndex == (size - 1) ? size : currentIndex + 1;
        }

        /**
         * Returns the index of the element that would be returned by a
         * subsequent call to {@link #previous}. (Returns -1 if the list
         * iterator is at the beginning of the list.)
         *
         * @return the index of the element that would be returned by a
         * subsequent call to {@code previous}, or -1 if the list
         * iterator is at the beginning of the list
         */
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
