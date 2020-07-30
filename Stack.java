package pack1;

import java.util.ArrayList;
import java.util.List;

public class Stack {

	private Node head;
	private int size;

	public Stack() {
		head = null;
		size = 0;
	}

	public Stack copy() {

		if (this.isEmpty()) {
			System.err.println("There is nothing to copy!");
			return null;
		}

		Stack s = new Stack();
		List<Integer> l = new ArrayList<Integer>();

		while (!this.isEmpty())
			l.add(this.pop());

		for (int i = l.size() - 1; i >= 0; i--) {
			this.push(l.get(i));
			s.push(l.get(i));
		}

		return s;
	}

	public void push(int dat) {

		this.size++;

		if (isEmpty()) {
			head = new Node(dat);
			return;
		}

		Node tmp = new Node(dat);
		tmp.next = head;
		head = tmp;
		head.next.last = head;
	}

	public int pop() {

		if (isEmpty())
			throw new NullPointerException("Stack is empty!");

		this.size--;
		int data;

		if (head.next == null) {
			data = head.data;
			head = null;
			return data;
		}

		head.next.last = null;
		data = head.data;
		head = head.next;
		return data;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void print() {

		if (isEmpty())
			throw new NullPointerException("Stack is empty!");

		System.out.print("The stack's elements are: ");
		Node tmp = head;

		while (tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.next;
		}
	}

	public int size() {
		return this.size;
	}

	public int top() {

		if (isEmpty())
			throw new NullPointerException("Stack is empty!");

		return this.head.data;
	}

	class Node {

		Node next, last;
		int data;

		Node(int data) {
			this.data = data;
			this.next = this.last = null;
		}
	};

//	public static void main(String[] args) {
//
//		Stack ll = new Stack();
//		ll.push(1);
//		ll.push(2);
//		ll.push(3);
//		ll.push(4);
//		ll.push(5);
//		ll.pop();
//		ll.print();
//		System.out.println();
//		Stack s = ll.copy();
//		s.print();
//	}
}
