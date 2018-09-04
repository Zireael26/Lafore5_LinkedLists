public class MyLinkedList {

    private class Node {
        int data;
        Node next;

        public Node (int dataVal, Node nextNode){
            this.data = dataVal;
            this.next = nextNode;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public void display() {
        Node node = this.head;
        while(node != null) {
            System.out.print(node.data + " => ");
            node = node.next;
        }

        System.out.println("\nEnd of linked list.");
    }

    public void addFirst(int data) {
        Node node = new Node(data, this.head); // make a new node
        this.head = node; // set it as head

        if (this.isEmpty()) { // if its the only node
            this.tail = this.head; // then its also the tail
        }

        this.size++;
    }

    public void addLast(int data) {
        Node node = new Node(data, this.tail); // make a new node object with data
        if (this.isEmpty()) { // to see if this is the first node
            this.head = node; //since this is the first node, it will also be the head
            this.tail = node; //and the tail
        } else {
            this.tail.next = node; // set the next pointer of the current tail to new node object
            this.tail = node; // set new node object as the new tail
        }
        this.size++;
    }

    public void addAt(int data, int idx) throws Exception {
        if (idx < 0 || idx > this.size()) {
            throw new Exception("Invalid index value!" );
        }

        if(idx == 0) {
            addFirst(data);
        } else if (idx == this.size()) {
            addLast(data);
        } else {
            Node nm1 = this.getNodeAt(idx - 1); // node at n-1
            Node n = nm1.next; // get address value of next field from the (n-1)th node

            Node node = new Node(data, n); // make a new node object, pass address of n as next node address
            nm1.next = node; // connect the (n-1)th node to new node
            this.size++;
        }
    }

    private Node getNodeAt(int idx) throws Exception {
        if(idx < 0 ||idx >= this.size()) { // valid index values are from 0 to size-1
            throw new Exception("Invalid Index value!");
        }

        int counter = 0; // to count to the desired index
        Node rv = this.head; // to start iteration at the head

        while(counter < idx) {
            rv = rv.next; // moving to next node
            counter++; // incrementing counter
        }
        return rv; // return the needed node
    }

    public int getFirst() throws Exception {
        if(this.isEmpty())
            throw new Exception("Linked list is empty");

        return this.head.data; // return the data of the first / head node
    }

    public int getLast() throws Exception {
        if (this.isEmpty())
            throw new Exception("Linked list is empty");

        return this.tail.data; // return the data of the last / tail node
    }

    public int getAt(int idx) throws Exception {
        if(this.isEmpty())
            throw new Exception("Linked list is empty");

        if(idx < 0 || idx >= this.size()) // invalid index
            throw new Exception("Index out of bounds");

        if (idx == 0) {
            return this.getFirst();
        } else if (idx == this.size()) {
            return this.getLast();
        } else {
            return this.getNodeAt(idx).data; // use the previously built getNodeAt method to get node, return its data
        }
    }

    public int removeFirst() throws Exception {
        if (this.isEmpty())
            throw new Exception("Nothing to remove, empty list");

        Node rv = this.head; // get the node in a separate object

        if(this.size() == 1) { // if it was the only node
            this.head = this.tail = null;  // the list is now empty and sad
        } else { // if not
            this.head = this.head.next; // the head Node object now refers to the next node
        }

        size--; // removed a node, reduced a size

        return rv.data; // return the data
    }

    // O(n)
    public int removeLast() throws Exception {
        if(this.isEmpty())
            throw new Exception("Nothing to remove, empty list");

        Node rv = this.tail;

        if(this.size() == 1) { // if there's only one node
            this.head = this.tail = null; // the list is now empty
        } else {
            Node secondLast = getNodeAt(this.size() - 2); // get (n-1)th node, size-1 is the last one, so size-2 is the second last one
            secondLast.next = null; // set its next pointer to null, as it will be new tail
            this.tail = secondLast; // set it as new tail;
        }
        size--;

        return rv.data;
    }

    // O(n)
    public int removeAt(int idx) throws Exception {
        if (this.isEmpty())
            throw new Exception("List is empty, nothing to remove");

        if (idx < 0 || idx >= this.size())
            throw new Exception("Invalid index");

        if(idx == 0) {
            return this.removeFirst();
        } else if (idx == this.size() - 1) {
            return this.removeLast();
        } else {
            Node prev = this.getNodeAt(idx - 1); // get the node at idx-1
            Node curr = prev.next; //get node to be removed
            prev.next = curr.next; // set next address field of (idx-1) / previous node to next address of current node
            size--; // reduce size

            return curr.data; // return the separated node
        }
    }

    // O(n^2) -  Reversing the linked list by swapping data
    public void reverseDataIterative() throws Exception{
        int leftIndex = 0, rightIndex = this.size() - 1; //get indices o start and end

        while(leftIndex <= rightIndex) { // continue until leftIndex and rightIndex cross each other
            Node left = getNodeAt(leftIndex); // get node at leftIndex
            Node right = getNodeAt(rightIndex); // get node at rightIndex
            // Swap the data of both nodes
            int temp = left.data;
            left.data = right.data;
            right.data = left.data;

            leftIndex++; // move one step right
            rightIndex--; // move one step left
        }
    }

    // O(n) - Reversing the Linked List by changing pointers
    public void reversePointerIteravtive() {
        Node prev = this.head;
        Node curr = prev.next;

        while(curr != null) { // loop till list reaches end
            Node tempPrev = prev;
            Node tempCurr = curr;

            prev = curr; // move to next node
            curr = curr.next; // move to next node

            tempCurr.next = tempPrev; // set next address field of tempCurr as tempPrev
        }
        // Swap head and tail
        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;
        // handle tail
        this.tail.next = null; // set next pointer of new tail to null
    }

    // O(n) -  Reverse the linked list by reversing pointers recursively
    public void reversePointerRecursive() {
        this.reversePointerRecursive(this.head);

        // Swap head and tail
        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;

        //Handle new Tail's next address field
        this.tail.next = null;
    }

    private void reversePointerRecursive(Node node) {
        if(node == this.tail) { // floor Condition: When you reach the end
            return;
        }

        reversePointerRecursive(node.next); // recursive call to reverse the remaining list
        node.next.next = node; // actual statement the reverses the pointer
    }


    // O(n) - Reversing Linked List by swapping data recursively
    public void reverseDataRecursive() {
        HeapMover left = new HeapMover(this.head);
        this.reverseDataRecursive(left, this.head, 0);
    }

    private void reverseDataRecursive(HeapMover left, Node right, int floor) {
        if (right == null) { //hit floor value, when right == null
            return;
        }
        // recursive call shifting right by one node and increasing floor by 1
        reverseDataRecursive(left, right.next, floor + 1);

        if(floor >= this.size() / 2) { // Start Swapping left and right when in the middle of the list
            int temp = left.node.data; // Swap
            left.node.data = right.data; // Swap
            right.data = temp; // Swap

            left.node = left.node.next;
        }

    }

    // Helper class for ReverseDataRecursive
    private class HeapMover{
        Node node;

        public HeapMover(Node node) {
            this.node = node;
        }
    }

    public int mid() {
        return midNode().data;
    }

    // returns middle node in single iteration, without knowing the size of the List
    private Node midNode() {
        Node slow = this.head; // this will reach mid, when
        Node fast = this.head; // this will reach the end

        while (fast!= null) {
            slow = slow.next; // proceed by 1 node
            fast = fast.next.next; // proceed by 2 nodes
        }

        return slow;
    }

}
