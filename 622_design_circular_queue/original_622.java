class MyCircularQueue {

    Node head, tail;
    int size, capacity;

    public MyCircularQueue(int k) {
        this.size = 0;
        this.capacity = k;
    }
    
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
        }
        size++;
        return true;
    }
    
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        if (size == 1) {
            head = tail = null;
        } else {
            head = head.next;
            tail.next = head;
        }
        size--;
        return true;
    }
    
    public int Front() {
        return isEmpty() ? -1 : head.val;
    }
    
    public int Rear() {
        return isEmpty() ? -1 : tail.val;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    public static void main(String[] args) {
        MyCircularQueue o1 = new MyCircularQueue(3);
        System.out.println(o1.enQueue(1)); // true
        System.out.println(o1.enQueue(2)); // true
        System.out.println(o1.enQueue(3)); // true
        System.out.println(o1.enQueue(4)); // false
        System.out.println(o1.Rear());    // 3
        System.out.println(o1.isFull());  // true
        System.out.println(o1.deQueue());  // true
        System.out.println(o1.enQueue(4)); // true
        System.out.println(o1.Rear());    // 4
    }
}

class Node {
    int val;
    Node next;
    public Node() {}
    public Node(int val) {
        this.val = val;
        this.next = null;
    }
}