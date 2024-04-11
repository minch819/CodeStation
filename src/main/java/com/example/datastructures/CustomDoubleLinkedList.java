package main.java.com.example.datastructures;

import java.util.Arrays;

public class CustomDoubleLinkedList<E> {
	private int size;
	private Node<E> head;
	private Node<E> tail;

	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		Node(Node<E> prev, E data, Node<E> next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}
	}

	public CustomDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	private Node<E> search(int index) {
		Node<E> n = head;

		for (int i = 0; i < index; i++) {
			n = n.next;
		}
		return n;
	}

	public void addFirst(E e) {
		Node<E> first = head;
		Node<E> newNode = new Node<>(null, e, first);

		head = newNode;

		if (first == null) {
			tail = newNode;
		} else {
			first.prev = newNode;
		}
		size++;
	}

	public void addLast(E e) {
		Node<E> last = tail;
		Node<E> newNode = new Node<>(last, e, null);

		tail = newNode;

		if (last == null) {
			head = newNode;
		} else {
			last.next = newNode;
		}
		size++;
	}

	public boolean add(E e) {
		addLast(e);
		return true;
	}

	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			addFirst(e);
			return;
		}

		if (index == size) {
			addLast(e);
			return;
		}

		Node<E> next_node = search(index);
		Node<E> prev_node = next_node.prev;
		Node<E> newNode = new Node<>(prev_node, e, next_node);

		prev_node.next = newNode;
		next_node.prev = newNode;

		size++;

	}

	public E removeFirst() {
		if (head == null) {
			throw new IndexOutOfBoundsException();
		}

		E element = head.data;
		Node<E> first = head.next;

		head.next = null;
		head.data = null;

		head = first;

		if (head == null) {
			tail = null;
		} else {
			first.prev = null;
		}

		size--;

		return element;
	}

	public E removeLast() {
		if (tail == null) {
			throw new IndexOutOfBoundsException();
		}

		E element = tail.data;
		Node<E> last = tail.prev;

		tail.next = null;
		tail.data = null;

		tail = last;

		if (head == null) {
			head = null;
		} else {
			tail.next = null;
		}

		size--;

		return element;
	}

	public E remove() {
		return removeFirst();
	}

	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return removeFirst();
		}

		if (index == size - 1) {
			return removeLast();
		}

		Node<E> delete_node = search(index);
		Node<E> prev_node = delete_node.prev;
		Node<E> next_node = delete_node.next;

		E returnValue = delete_node.data;

		delete_node.prev = null;
		delete_node.data = null;
		delete_node.next = null;

		prev_node.next = next_node;
		next_node.prev = prev_node;

		size--;

		return returnValue;
	}

	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return search(index).data;
	}

	public void set(int index, E e) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> node = search(index);

		node.data = null;
		node.data = e;
	}

	@Override
	public String toString() {
		if (head == null) {
			return "[]";
		}

		Object[] array = new Object[size];

		int index = 0;
		Node<E> n = head;
		while (n != null) {
			array[index] = (E)n.data;
			index++;
			n = n.next;
		}
		return Arrays.toString(array);
	}
}