package collections;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Svetotulichka on 23.10.2017.
 */
public class LinkedList<E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, Serializable {

    private int size;
    private Node<E> head;
    private Node<E> tail;

    private class Node<E> {
        private E content;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e) {
            content = e;
        }

        public E getContent() {
            return content;
        }

        public void setContent(E content) {
            this.content = content;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
            if (next != null) {
                next.setPrev(this);
            }
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    public LinkedList() {
        size = 0;
    }

    public LinkedList(Collection<? extends E> c) {
        this();
        for (E element : c) {
            add(element);
        }
    }

    public boolean add(E e) {
        if (head == null) {
            head = new Node<>(e);
            tail = head;
        } else {
            Node<E> newNode = new Node<E>(e);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        return true;
    }


    public void add(int index, E element) {
        checkIndex(index);
        Node<E> iteratedNode = getNode(index);
        Node<E> newNode = new Node<E>(element);

        if (iteratedNode.getPrev() == null) {
            head = newNode;
        } else {
            iteratedNode.getPrev().setNext(newNode);
        }
        newNode.setNext(iteratedNode);
    }


    public boolean addAll(Collection<? extends E> c) {
        checkNotNullCollection(c);
        for (E element : c) {
            add(element);
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index);
        checkNotNullCollection(c);

        Node<E> lastExistingNodeToAppend = getNode(index);

        Node<E> current = null;
        for (E element : c) {
            if (current == null) {
                current = new Node<>(element);
                if (lastExistingNodeToAppend.getPrev() == null) {
                    head = current;
                } else {
                    lastExistingNodeToAppend.getPrev().setNext(current);
                }
            } else {
                current.setNext(new Node<>(element));
                current = current.getNext();
            }
            size++;
        }
        current.setNext(lastExistingNodeToAppend);

        return true;
    }


    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.setNext(head);
        if (tail == null) tail = newNode;
        head = newNode;
        size++;
    }


    public void addLast(E e) {
        add(e);
    }


    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }


    public Object clone() {
        try {
            LinkedList<E> newList = (LinkedList<E>) super.clone();
            newList.clear();
            if (size > 0) {
                Node<E> current = head;
                while (current != null) {
                    newList.add(current.getContent());
                    current = current.getNext();
                }
            }
            return newList;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean contains(Object o) {
        Node<E> current = head;
        while (current != null) {
            E e = current.getContent();
            if (o == null ? e == null : o.equals(e)) return true;
            current = current.getNext();
        }
        return false;
    }


    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     */
    public E element() {
        checkIfEmpty();
        return head.getContent();
    }


    public E get(int index) {
        checkIndex(index);
        return getNode(index).getContent();
    }


    public E getFirst() {
        checkIfEmpty();
        return head.getContent();
    }


    public E getLast() {
        checkIfEmpty();
        return tail.getContent();
    }


    public int indexOf(Object o) {
        Node<E> iteratedNode = head;
        for (int i = 0; i < size; i++) {
            if (iteratedNode.getContent() == null ? o == null : iteratedNode.getContent().equals(o)) return i;
            iteratedNode = iteratedNode.getNext();
        }
        return -1;
    }


    public int lastIndexOf(Object o) {
        Node<E> iteratedNode = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (iteratedNode.getContent() == null ? o == null : iteratedNode.getContent().equals(o)) index = i;
            iteratedNode = iteratedNode.getNext();
        }
        return index;
    }

    public boolean offer(E e) {
        return add(e);
    }


    public boolean offerFirst(E e) {
        add(0, e);
        return true;
    }


    public boolean offerLast(E e) {
        return add(e);
    }

    //    Retrieves, but does not remove, the head (first element) of this list.
    public E peek() {
        return peekFirst();
    }

    //    Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
    public E peekFirst() {
        return head == null ? null : head.getContent();
    }

    //    Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
    public E peekLast() {
        return tail == null ? null : tail.getContent();
    }

    public E poll() {
        return removeFirst();
    }


    public E pollFirst() {
        return removeFirst();
    }


    public E pollLast() {
        return removeLast();
    }


    public E pop() {
        checkIfEmpty();
        return removeFirst();
    }


    public void push(E e) {
        addFirst(e);
    }


    public E remove() {
        checkIfEmpty();
        return removeFirst();
    }


    public E remove(int index) {
        checkIndex(index);
        Node<E> nodeToRemove = getNode(index);
        return removeNodeAndReturnContent(nodeToRemove);
    }


    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }


    public E removeFirst() {
        checkIfEmpty();
        Node<E> nodeToRemove = head;
        if (nodeToRemove.getNext() != null) {
            nodeToRemove.getNext().setPrev(null);
        } else {
            tail = null;
        }
        head = nodeToRemove.getNext();
        size--;
        return nodeToRemove.getContent();
    }


    public boolean removeFirstOccurrence(Object o) {
        Node<E> nodeToRemove;
        Node<E> iteratedNode = head;
        for (int i = 0; i < size; i++) {
            if (iteratedNode.getContent() == null ? o == null : iteratedNode.getContent().equals(o)) {
                nodeToRemove = iteratedNode;
                removeNode(nodeToRemove);
                return true;
            }
            iteratedNode = iteratedNode.getNext();
        }
        return false;
    }


    public E removeLast() {
        checkIfEmpty();
        Node<E> nodeToRemove = tail;
        if (nodeToRemove.getPrev() != null) {
            nodeToRemove.getPrev().setNext(null);
        } else {
            head = null;
        }
        tail = nodeToRemove.getPrev();
        size--;
        return nodeToRemove.getContent();
    }


    public boolean removeLastOccurrence(Object o) {
        Node<E> nodeToRemove = null;
        Node<E> iteratedNode = head;
        for (int i = 0; i < size; i++) {
            if (iteratedNode.getContent() == null ? o == null : iteratedNode.getContent().equals(o))
                nodeToRemove = iteratedNode;
            iteratedNode = iteratedNode.getNext();
        }
        if (nodeToRemove != null) {
            removeNode(nodeToRemove);
            return true;
        }
        return false;
    }

    /**
     * Returns:
     * the element previously at the specified position
     */
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node = getNode(index);
        E previous = node.getContent();
        node.setContent(element);
        return previous;
    }


    public int size() {
        return size;
    }


    public Object[] toArray() {
        E[] array = (E[]) new Object[size];
        Node<E> iteratedNode = head;
        for (int i = 0; i < size; i++) {
            array[i] = iteratedNode.getContent();
            iteratedNode = iteratedNode.getNext();
        }
        return array;
    }
//    Returns an array containing all of the elements in this list in proper sequence (from first to last element).


    public <T> T[] toArray(T[] a) {
        if (a == null) throw new NullPointerException("Specified array is null.");
        if (a.length < size) {
            a = (T[]) new Object[size];
        }
        try {
            Node<E> iteratedNode = head;
            for (int i = 0; i < size; i++) {
                a[i] = (T) iteratedNode.getContent();
                iteratedNode = iteratedNode.getNext();
            }
        } catch (ClassCastException e) {
            throw new ArrayStoreException("The type of the specified array is not a supertype of elements in this list.");
        }
        return a;
    }

    public ListIterator<E> listIterator(int index) {
        return new LinkedIterator(index);

    }

    public Iterator<E> descendingIterator() {
        return new DescListIterator();
    }


    private Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> iterated = head;
        for (int i = 0; i <= index; i++) {
            if (i == index) break;
            iterated = iterated.getNext();
        }
        return iterated;
    }

    private Node<E> removeNode(Node<E> node) {
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            head = node.getNext();
        }
        if (node.getNext() == null) {
            tail = node.getPrev();
        }
        size--;
        node.setPrev(null);
        node.setNext(null);
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException("Index is out of range");
    }

    private void checkNotNullCollection(Collection<? extends E> c) {
        if (c == null) throw new NullPointerException("The specified collection is null");
    }

    public class LinkedIterator implements ListIterator<E> {

        private Node<E> nextNode;
        private Node<E> currentNode;
        private int currentIndex;


        public LinkedIterator(int currentIndex) {
            checkIndex(currentIndex);
            this.currentIndex = currentIndex;
            nextNode = getNode(currentIndex);
            currentNode = nextNode.getPrev();
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            currentIndex++;
            currentNode = nextNode;
            nextNode = nextNode.getNext();
            return currentNode.getContent();
        }


        @Override
        public boolean hasPrevious() {
            return currentNode != null && currentNode.getPrev() != null;
        }


        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            currentIndex--;
            nextNode = currentNode;
            currentNode = currentNode.getPrev();
            return currentNode.getContent();
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
            removeNode(currentNode);
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

    public class DescListIterator extends LinkedIterator {
        public DescListIterator() {
            super(size - 1);
            super.currentNode = new Node<>(null);
            super.currentNode.setPrev(super.nextNode);
        }

        @Override
        public boolean hasNext() {
            return super.hasPrevious();
        }

        @Override
        public E next() {
            return super.previous();
        }


        @Override
        public boolean hasPrevious() {
            return super.hasNext();
        }


        @Override
        public E previous() {
            return super.next();
        }


        @Override
        public int nextIndex() {
            return super.previousIndex();
        }


        @Override
        public int previousIndex() {
            return super.nextIndex();
        }

    }

    private E removeNodeAndReturnContent(Node<E> nodeToRemove) {
        if (nodeToRemove.getPrev() != null) {
            nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
        } else {
            head = nodeToRemove.getNext();
        }
        if (nodeToRemove.getNext() != null) {
            nodeToRemove.getNext().setPrev(null);
        } else {
            tail = nodeToRemove.getPrev();
        }
        return nodeToRemove.getContent();
    }

    private void checkIfEmpty() {
        if (size == 0) throw new NoSuchElementException();
    }
}
