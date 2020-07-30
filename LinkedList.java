package pack1;

public class LinkedList {

	private Node head, tail;
	private int size;

	public LinkedList() {

		head = tail = null;
		size = 0;

	}

	public void addAtHead(int dat) {

		this.size++;

		if (isEmpty()) {
			head = tail = new Node(dat);
			return;
		}

		Node tmp = new Node(dat);
		tmp.next = head;
		head = tmp;
		head.next.last = head;
	}

	public void addAtTail(int dat) {

		this.size++;

		if (isEmpty()) {
			head = tail = new Node(dat);
			return;
		}

		Node tmp = new Node(dat);
		tail.next = tmp;
		tmp.last = tail;
		tail = tail.next;
	}

	public int deleteAtHead() {

		if (isEmpty())
			throw new NullPointerException("LinkedList is empty!");

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

	public void deleteAtTail() {

		if (isEmpty())
			throw new NullPointerException("LinkedList is empty!");

		if (head.next == null) {
			head = null;
			return;
		}

		this.size--;
		tail = tail.last;
		tail.next.last = null;
		tail.next = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void print() {

		if (isEmpty())
			throw new NullPointerException("LinkedList is empty!");

		System.out.print("The LinkedList's elements are: ");
		Node tmp = head;

		while (tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.next;
		}
	}

	public int size() {
		return this.size;
	}

	public int getFirst() {

		if (isEmpty())
			throw new NullPointerException("LinkedList is empty!");

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
//		LinkedList ll = new LinkedList();
//		ll.addAtHead(1);
//		ll.addAtHead(2);
//		ll.addAtHead(3);
//		ll.addAtHead(4);
//		ll.addAtHead(5);
//		ll.deleteAtTail();
//		ll.deleteAtTail();
//		ll.addAtTail(11);
//		ll.print();
//		System.out.println();
//		System.out.println(ll.size());
//	}
}
