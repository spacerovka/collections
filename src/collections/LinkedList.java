package collections;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Svetotulichka on 23.10.2017.
 */
public class LinkedList<E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, Serializable {

    public boolean add(E e) {
        // TODO: 29.10.2017
        return false;
    }
//    Appends the specified element to the end of this list.


    public void add(int index, E element) {
        // TODO: 29.10.2017
    }
//    Inserts the specified element at the specified position in this list.


    public boolean addAll(Collection<? extends E> c) {
        // TODO: 29.10.2017
        return false;
    }
//    Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator.


    public boolean addAll(int index, Collection<? extends E> c) {
        // TODO: 29.10.2017
        return false;
    }
//    Inserts all of the elements in the specified collection into this list, starting at the specified position.


    public void addFirst(E e) {
        // TODO: 29.10.2017
    }
//    Inserts the specified element at the beginning of this list.


    public void addLast(E e) {
        // TODO: 29.10.2017
    }
//    Appends the specified element to the end of this list.


    public void clear() {
        // TODO: 29.10.2017
    }
//    Removes all of the elements from this list.


    public Object clone() {
        // TODO: 29.10.2017
        return null;
    }
//    Returns a shallow copy of this LinkedList.


    public boolean contains(Object o) {
        // TODO: 29.10.2017
        return false;
    }
//    Returns true if this list contains the specified element.


    public Iterator<E> descendingIterator() {
        // TODO: 29.10.2017
        return null;
    }
//    Returns an iterator over the elements in this deque in reverse sequential order.


    public E element() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves, but does not remove, the head (first element) of this list.


    public E get(int index) {
        // TODO: 29.10.2017
        return null;
    }
//    Returns the element at the specified position in this list.


    public E getFirst() {
        // TODO: 29.10.2017
        return null;
    }
//    Returns the first element in this list.


    public E getLast() {
        // TODO: 29.10.2017
        return null;
    }
//    Returns the last element in this list.


    public int indexOf(Object o) {
        // TODO: 29.10.2017
        return 0;
    }
//    Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.


    public int lastIndexOf(Object o) {
        // TODO: 29.10.2017
        return 0;
    }
//    Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.


    public ListIterator<E> listIterator(int index) {
        // TODO: 29.10.2017
        return null;
    }
//    Returns a list-iterator of the elements in this list (in proper sequence), starting at the specified position in the list.


    public boolean offer(E e) {
        // TODO: 29.10.2017
        return false;
    }
//    Adds the specified element as the tail (last element) of this list.


    public boolean offerFirst(E e) {
        // TODO: 29.10.2017
        return false;
    }
//    Inserts the specified element at the front of this list.


    public boolean offerLast(E e) {
        // TODO: 29.10.2017
        return false;
    }
//    Inserts the specified element at the end of this list.


    public E peek() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves, but does not remove, the head (first element) of this list.


    public E peekFirst() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.


    public E peekLast() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.


    public E poll() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves and removes the head (first element) of this list.


    public E pollFirst() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves and removes the first element of this list, or returns null if this list is empty.


    public E pollLast() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves and removes the last element of this list, or returns null if this list is empty.


    public E pop() {
        // TODO: 29.10.2017
        return null;
    }
//    Pops an element from the stack represented by this list.


    public void push(E e) {
        // TODO: 29.10.2017
    }
//    Pushes an element onto the stack represented by this list.


    public E remove() {
        // TODO: 29.10.2017
        return null;
    }
//    Retrieves and removes the head (first element) of this list.


    public E remove(int index) {
        // TODO: 29.10.2017
        return null;
    }
//    Removes the element at the specified position in this list.


    public boolean remove(Object o) {
        // TODO: 29.10.2017
        return false;
    }
//    Removes the first occurrence of the specified element from this list, if it is present.


    public E removeFirst() {
        // TODO: 29.10.2017
        return null;
    }
//    Removes and returns the first element from this list.


    public boolean removeFirstOccurrence(Object o) {
        // TODO: 29.10.2017
        return false;
    }
//    Removes the first occurrence of the specified element in this list (when traversing the list from head to tail).


    public E removeLast() {
        // TODO: 29.10.2017
        return null;
    }
//    Removes and returns the last element from this list.


    public boolean removeLastOccurrence(Object o) {
        // TODO: 29.10.2017
        return false;
    }
//    Removes the last occurrence of the specified element in this list (when traversing the list from head to tail).


    public E set(int index, E element) {
        // TODO: 29.10.2017
        return null;
    }
//    Replaces the element at the specified position in this list with the specified element.


    public int size() {
        // TODO: 29.10.2017
        return 0;
    }
//    Returns the number of elements in this list.


    public Object[] toArray() {
        // TODO: 29.10.2017
        return null;
    }
//    Returns an array containing all of the elements in this list in proper sequence (from first to last element).


    public <T> T[] toArray(T[] a) {
        // TODO: 29.10.2017
        return null;
    }
//    Returns an array containing all of the elements in this list in proper sequence (from first to last element); the runtime type of the returned array is that of the specified array.

}
