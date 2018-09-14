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
        Node node = new Node(data, null); // make a new node object with data
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

    // O(1)
    public int getFirst() throws Exception {
        if(this.isEmpty())
            throw new Exception("Linked list is empty");

        return this.head.data; // return the data of the first / head node
    }

    // O(1)
    public int getLast() throws Exception {
        if (this.isEmpty())
            throw new Exception("Linked list is empty");

        return this.tail.data; // return the data of the last / tail node
    }

    // O(n)
    public int getAt(int idx) throws Exception {
        if(this.isEmpty())
            throw new Exception("Linked list is empty");

        if(idx < 0 || idx >= this.size()) // invalid index
            throw new Exception("Index out of bounds");

        if (idx == 0) {
            return this.getFirst();
        } else if (idx == this.size() - 1) {
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
            right.data = temp;

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

    // function to find if a list is a palindrome
    public boolean isPalindrome() {
        HeapMover left = new HeapMover(this.head);
        return this.isPalindrome(left, this.head);
    }

    private boolean isPalindrome(HeapMover left, Node right) {
        if (right == null) { // take right to the end, then compare when it starts to return
            return true;
        }

        boolean isPalin = this.isPalindrome(left, right.next);

        if (isPalin == false) { // if in any case it becomes false
            return false;  // we shall return false without thinking
        } else { // if it is true, we will compare for every step
            if (left.node.data == right.data) {  // compare data of the (left) node inside HeapMover object with
                                                 // the data of right as it falls down the recursion stack
                left.node = left.node.next;      // then move HeapMover to right and right node will move to left itself
                return true;                     // return true for this comparison
            } else {
                return false;                    // if they aren't equal, return false and then we will return false
            }                                    // for all other cases without even checking
        }
    }

    public int mid() {
        return midNode().data;
    }

    // returns middle node in single iteration, without knowing the size of the List
    private Node midNode() {
        Node slow = this.head; // this will reach mid, when
        Node fast = this.head; // this will reach the end

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next; // proceed by 1 node
            fast = fast.next.next; // proceed by 2 nodes
        }

        return slow;
    }

    // this method will take a linked list like 10 -> 20 -> 30 -> 40 -> 50
    // and fold it to make it look like 10 -> 50 -> 20 -> 40 -> 30
    public void fold() {
        HeapMover left = new HeapMover(this.head);
        this.fold(left, this.head, 0);
    }

    private void fold(HeapMover left, Node right, int floor) {
        if (right == null) { // when right hits null, fall back and do work
            return;
        }

        this.fold(left, right.next, floor + 1);
        // at this point, left is at head, right is at tail
        if (floor > this.size() / 2) {  // so while we do not reach the center of the list
            Node origLeftNext = left.node.next; // we preserve the original left next address
            left.node.next = right;             // then we set the next of left node to right
            right.next = origLeftNext;          // then we set the next of right to the original next of left

            left.node = origLeftNext;           // moved ahead by setting the value of left to its original next
        } else if (floor == this.size() / 2) {  // when at the center of the linked list
            this.tail = right;                  // the node at right pointer becomes the tail
            this.tail.next = null;              // and its next pointer is set to null
        }

    }

    public int kthFromEnd(int k) {
        return kthNodeFromEnd(k).data;
    }

    private Node kthNodeFromEnd(int k) {
        Node slow = this.head; // slow pointer
        Node fast = this.head; // fast pointer

        for (int i = 0; i < k; i++) {
            fast = fast.next; // fast pointer goes 'k' nodes ahead
        }

        while (fast!= null) {
            slow = slow.next; //then move the pointers
            fast = fast.next; // till fast reaches the tail / end
        }
        return slow; // this time slow pointer will be at kth position from end
    }

    public int find(int key) {
        int rv = this.find(key, this.head);
        return rv;
    }

    // method to search the linked list, recursive
    private int find(int key, Node node) {
        if (node == null){ // floor Condition, end of list reached
            return -1; // -1 indicates not found
        }
        if (node.data == key){ //if found at head / position 0, return 0
            return 0;
        } else {
            int rv = find(key, node.next); // recursive call
            if (rv == -1) { // not found
                return rv;
            } else { //found
                return rv + 1; //add 1 for every pop from recursion stack
            }
        }
    }

    public MyLinkedList merge(MyLinkedList other) {
        MyLinkedList returnList = new MyLinkedList();

        Node thisTemp = this.head;
        Node otherTemp = other.head;
        while (thisTemp != null && otherTemp != null){ // if either list ends, end loop
            if (thisTemp.data < otherTemp.data) { // if this list has smaller element
                returnList.addLast(thisTemp.data); // add to new list
                thisTemp = thisTemp.next;         // move to next node
            } else {                              // if this list has smaller node
                returnList.addLast(otherTemp.data); // add to new list
                otherTemp = otherTemp.next;         // move to next node
            }
        }

        // only one of the while loops below will work because one list will be exhausted in the above method
        // add the remaining elements of the list
        while (thisTemp != null) {
            returnList.addLast(thisTemp.data);
            thisTemp = thisTemp.next;
        }
        // add the remaining elements of the list
        while (otherTemp != null) {
            returnList.addLast(otherTemp.data);
            otherTemp = otherTemp.next;
        }

        // now the list is sorted and merged
        return returnList;
    }

    // perform mergeSort on the list
    public void mergeSort() {
        MyLinkedList sorted = this.mergeSortHelper();

        this.head = sorted.head;
        this.tail = sorted.tail;
    }


    private MyLinkedList mergeSortHelper() {
        if (this.size() == 1) { // floor condition, when there is only 1 item, return
            return this;
        }
        Node mid = this.midNode(); // we find this to break it into two halves
        Node midNext = mid.next;   // and this too

        MyLinkedList firstHalf = new MyLinkedList();
        firstHalf.head = this.head; // head is the head of original list
        firstHalf.tail = mid;       // tail is the mid of original list
        firstHalf.tail.next = null; // set the next field of tail to null
        firstHalf.size = (this.size + 1)/2; // size of this half is half of original size

        MyLinkedList secondHalf = new MyLinkedList();
        secondHalf.head = midNext; // head is the node next to middle node (midnext)
        secondHalf.tail = this.tail; // tail of this half is original tail
        secondHalf.size = this.size() / 2; // size is half of original size

        firstHalf = firstHalf.mergeSortHelper();   // recursive call to sort first half
        secondHalf = secondHalf.mergeSortHelper(); // recursive call to sort second half

        MyLinkedList sorted = firstHalf.merge(secondHalf); // join the final two halves
        return sorted;
    }

    // method to change the the order of elements in a list to contain all odd elements first, followed by even elements
    public void oddEven() throws Exception {
        MyLinkedList oddList = new MyLinkedList();   // we will make two new LinkedList objects
        MyLinkedList evenList = new MyLinkedList();  // to keep odd and even elements

        // run a loop on original list (this)
        while(this.size != 0) { // while it still has elements
            int removed = this.removeFirst(); // remove first and check if its dd or even
            if (removed % 2 == 1) {           // if odd, add to oddList
                oddList.addLast(removed);
            } else {                          // if even, add to evenList
                evenList.addLast(removed);
            }
        }

        // if any one of the lists stands to be empty,
        if (oddList.size == 0) {
            this.head = evenList.head;  // then head, tail, size of the changed list will come from the non-empty list
            this.tail = evenList.tail;
            this.size = evenList.size;
        } else if (evenList.size == 0) {
            this.head = oddList.head;
            this.tail = oddList.tail;
            this.size = oddList.size;
        } else {                       // else
            this.head = oddList.head;  // head of changed list is head of odd list
            this.tail = evenList.tail; // tail of original list is tail of even list
            this.size = oddList.size + evenList.size; // and size is sum of both their sizes
            oddList.tail.next = evenList.head;  // now we connect the end of odd list to start of even list to make it one list
        }
    }

    // remove duplicates from a sorted list
    public void removeDuplicates() throws Exception {
        MyLinkedList newList = new MyLinkedList(); // make new LinkedList

        int removed = this.removeFirst();          // remove and add the first item to new list
        newList.addLast(removed);
        while (!this.isEmpty()) {                  // then while this is not empty
            removed = this.removeFirst();          // remove an element, check
            if (newList.tail.data != removed) {    // if this is different from the data in the tail of new list
                newList.addLast(removed);          // only then, add
            }
        }

        this.head = newList.head;                  // set all List summary pointers to new List
        this.tail = newList.tail;
        this.size = newList.size;
    }

    public void kReverse(int k) throws Exception {
        MyLinkedList changedList = new MyLinkedList();
        MyLinkedList tempList = new MyLinkedList();

        // the for loop inside the while loop looks like O(n^2) but isn't
        // because the inner loop runs 'k' times but the outer loop only runs 'n/k' times, so (n/k) * k = n
        while (!this.isEmpty()) {
            for (int i = 0; i < k; i++) {           // k times
                int removed = this.removeFirst();   // remove first from this
                tempList.addFirst(removed);         // addFirst to tempList
            }                                       // this process will add k elements in reverse order to the tempList

            if (changedList.isEmpty()) {            // if this is the first time, make changedList to tempList
                changedList = tempList;
                tempList = new MyLinkedList();      // empty the tempList
            } else {                                // else
                changedList.tail.next = tempList.head;     // connect tail of changedList to tempList
                changedList.tail = tempList.tail;          // set new tail of changedList to tail of tempList
                changedList.size += tempList.size;         // increase its size

                tempList = new MyLinkedList();             // empty the tempList
            }
        }
        changedList.tail.next = null;

        this.head = changedList.head;
        this.tail = changedList.tail;
        this.size = changedList.size;
    }

    //Function to delete m nodes after n nodes
    public void delMAfterN(int m, int n) throws Exception {
        if (this.size() < (m + n)) {
            throw new Exception("Insufficient sized list to delete the nodes");
        }

        MyLinkedList newList = new MyLinkedList();
        for (int i = 0; i < n; i++) {  // save the first 'n' nodes in a new list
            int removed = this.removeFirst();  // O(1)
            newList.addLast(removed);          // O(1)
        }

        for (int i = 0; i < m; i++) {          // remove the next m nodes
            this.removeFirst();                // O(1)
        }

        // if it is empty, then the list that (this) points to is our answer, else
        if (!newList.isEmpty()) {
            // now get the remaining part of the list in a temporary list
            MyLinkedList remainingList = new MyLinkedList();
            remainingList.head = this.head;
            remainingList.tail = this.tail;
            remainingList.size = this.size;

            // and join this to the end of our new list
            newList.tail.next = remainingList.head;
            newList.tail = remainingList.tail;
            newList.size += remainingList.size;

            this.head = newList.head;
            this.tail  = newList.tail;
            this.size = newList.size;
        }
    }

    // delete 'm' nodes after skipping 'n' nodes
    public void delMAfterN2(int m, int n) throws Exception {
        if (m+n > this.size()) {
            throw new Exception("List too small!");
        }

        Node nthNode = this.head;
        Node mthNode = this.head;

        for (int i = 0; i < (m + n); i++) {
            if (i < n-1) { // run this n-1 times to bring nthNode to (n-1)th node
                nthNode = nthNode.next;
            }
            mthNode = mthNode.next; // to take it to mthNode
        }

        // connect the next of nthNode to mthNode
        nthNode.next = mthNode;
        if (n == 0) { // if n is not zero, then no need to change head, else set head as nthNode
           this.head = mthNode;
        }
        this.size -= m; // reduce the nodes removed from size
    }

    public void removeEveryKthNode(int k) throws Exception {
        MyLinkedList finalList = new MyLinkedList();  // make a new LinkedList
        int discard = 1;                              // make an int 'discard' to determine whether to keep or delete a node
        while (!this.isEmpty()) {
            int removed = this.removeFirst();
            if (discard % k != 0) { // if it is not a multiple of K, add to new list
                finalList.addLast(removed);
            }

            discard++;              // increase the value of discard
        }

        this.head = finalList.head;
        this.tail = finalList.tail;
        this.size = finalList.size;
    }

    // Method to sort a list of 0s, 1s and 2s by changing links
    public void sort012Links() throws Exception {
        MyLinkedList zeroList = new MyLinkedList();
        MyLinkedList oneList = new MyLinkedList();
        MyLinkedList twoList = new MyLinkedList();

        while (!this.isEmpty()) {       // remove from this list and add to one of the 3 lists according to value
            int removedNumber = this.removeFirst();
            if (removedNumber == 0) {
                zeroList.addLast(removedNumber);
            } else if (removedNumber == 1) {
                oneList.addLast(removedNumber);
            } else {
                twoList.addLast(removedNumber);
            }
        }

        // for simplicity, we shall assume that neither of the three lists will be empty
        this.head = zeroList.head;
        this.tail = twoList.tail;
        oneList.tail.next = twoList.head;
        zeroList.tail.next = oneList.head;
    }

    // this method adds 1 to a linked list representation of a number
    public void addOne() {
        int carry = this.addOne(this.head);
        if (carry == 1) {            // if the carry for the number is 1, add a new node to the start
            this.addFirst(carry);  // this will only run if the number is like 9, 99, 999, 9999 and so on
        }
    }

    private int addOne(Node node) {
        if (node == null) {   // when you hit the rightmost place
            return 1;         // return carry of unit digit as 1, so it will be added to the number
        }

        int carry = this.addOne(node.next); // this method call gets the carry from the previous power of 10.

        node.data += carry;     // carry is either 0 or 1
        int myCarry = node.data / 10;   // calculate my carry that I shall return
        node.data %= 10;        // just in case the number exceeds 10
        return myCarry;         // return the calculated carry for the next power of 10s place
    }

    // edit this function to make a loop inside a Linked List, according to your preferences
    public void makeLoop() {
        this.tail.next = this.head.next.next;
    }

    // This function finds and removes loops in a LinkedList
    public void findAndRemoveLoop() {
        LoopPair loopPair = this.hasLoop(); // check if this List has a loop
        if (loopPair.hasLoop) {             // if there is a loop
            System.out.println("Loop found!");  // print loop found
            this.removeLoop(loopPair.nodeAtK);  // remove the loop
        } else {                            // Else print No Loop Found
            System.out.println("No loop found!");
        }
    }

    // Helper class to find and remove loops
    private class LoopPair {
        Node nodeAtK;     // this will store the position where fast and slow pointers meet to confirm there is a loop
        boolean hasLoop;  // this boolean variable stores whether or not the LinkedList has a loop
    }

    private LoopPair hasLoop() {// returns an object of LoopPair so that we can also get the node at K from start of loop
        Node slow = this.head;  // initialize both slow and fast pointers with this.head
        Node fast = this.head;

        LoopPair myPair = new LoopPair(); // make a LoopPair object

        while (fast.next != null && fast.next.next != null) {   // if either hits null, it means the LinkedList ends and there's no loop
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {         // if they meet, it means fast never hit null and there is a loop
                myPair.hasLoop = true;  // set the boolean to true
                myPair.nodeAtK = slow;  // return the position of either of fast/slow as this is at K distance from the start of loop
                return myPair;          // return the object
            }
        }

        myPair.hasLoop = false;         // if loop ends, it means a pointer hit null, so there is no loop
        return myPair;                  // set the boolean to false and return the object
    }

    // if 'm' is the distance from head to start of loop, 'n' is the length of the loop, 'k' is the distance from start
    // of the loop, to the point where fast and slow pointers meet.
    // we start two pointers, one from the head, another from 'k', and run a loop till they meet, they will always meet
    // at the last node of the loop, because  (m + k) is a multiple of 'n'
    private void removeLoop(Node nodeAtK) {
        Node tempNode = this.head;
        while (tempNode.next != nodeAtK.next) {
            tempNode = tempNode.next;
            nodeAtK = nodeAtK.next;
        }
        // because they will meet at the last Node of the loop, set the next pointer of this node as null
        nodeAtK.next = null;
        System.out.println("Loop removed!");
        this.tail = nodeAtK;
    }
}
