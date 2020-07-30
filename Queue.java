package pack1;

public class Queue {

	private Node head, tail;
	private int size;

	public Queue() {
		head = tail = null;
		size = 0;
	}

	public Queue copy() {

		if (this.isEmpty()) {
			System.err.println("There is nothing to copy!");
			return null;
		}

		Queue q = new Queue();
		Node tmp = this.head;

		while (tmp != null) {
			q.enqueue(tmp.data);
			tmp = tmp.next;
		}

		return q;
	}

	public void enqueue(int dat) {

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

	public int dequeue() {

		if (isEmpty())
			throw new NullPointerException("Queue is empty!");

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
			throw new NullPointerException("Queue is empty!");

		System.out.print("The queue's elements are: ");
		Node tmp = head;

		while (tmp != null) {
			System.out.print(tmp.data + " ");
			tmp = tmp.next;
		}
	}

	public int size() {
		return this.size;
	}

	public int peek() {

		if (isEmpty())
			throw new NullPointerException("Queue is empty!");

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
//		Queue ll = new Queue();
//		ll.enqueue(1);
//		ll.enqueue(2);
//		ll.enqueue(3);
//		ll.enqueue(4);
//		ll.enqueue(5);
//		ll.dequeue();
//		ll.print();
//	}
}
