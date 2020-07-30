package pack1;

public class MaxHeap {

	private int[] heap;
	private int heapSize;

	public MaxHeap(int size) {
		this.heap = new int[size];
		this.heapSize = 0;
	}

	public int size() {
		return this.heapSize;
	}

	private boolean isEmpty() {
		return this.heapSize == 0;
	}

	private int parent(int index) {
		return (index - 1) / 2;
	}

	private int rightChild(int index) {
		return index * 2 + 2;
	}

	private int leftChild(int index) {
		return index * 2 + 1;
	}

	public void add(int dat) {

		if (this.heapSize == this.heap.length)
			return;

		this.heapSize++;
		this.heap[this.heapSize - 1] = dat;
		heapifyUp(this.heapSize - 1);
	}

	public void delete() {

		if (isEmpty()) {
			return;
		}

		this.heap[0] = this.heap[this.heapSize - 1];
		this.heapSize--;
		if (this.heapSize > 0) {
			heapifyDown(0);
		}
	}

	private void heapifyUp(int index) {

		int parent = parent(index), tmp;

		if (index != 0) {

			if (this.heap[index] > this.heap[parent]) {

				tmp = this.heap[index];
				this.heap[index] = this.heap[parent];
				this.heap[parent] = tmp;

				heapifyUp(parent);
			}
		}
	}

	private void heapifyDown(int index) {

		int left = leftChild(index);
		int right = rightChild(index);
		int maxIndex, tmp;

		if (right >= this.heapSize) {

			if (left >= this.heapSize)
				return;
			else
				maxIndex = left;
		} else {

			if (this.heap[right] >= this.heap[left])
				maxIndex = right;
			else
				maxIndex = left;
		}

		if (this.heap[index] < this.heap[maxIndex]) {

			tmp = this.heap[index];
			this.heap[index] = this.heap[maxIndex];
			this.heap[maxIndex] = tmp;
			heapifyDown(maxIndex);
		}
	}

	public int peek() {
		return this.heap[0];
	}

	public void heapPrint() {

		String tmp = "";

		for (int i = 0; i < this.heapSize; i++) {
			tmp += this.heap[i] + ", ";
		}
		System.out.println(tmp.substring(0, tmp.length() - 2));
	}

	public static void main(String[] args) {

		MaxHeap m = new MaxHeap(10);

		for (int i = 1; i <= 10; i++)
			m.add(i);

		m.delete();
		m.heapPrint();
	}
}
