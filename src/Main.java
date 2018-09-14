public class Main {

    public static void main(String[] args) throws Exception {
        MyLinkedList list1 = new MyLinkedList();
        list1.addFirst(25);
        list1.addLast(24);
        list1.addLast(13);
        list1.addLast(14);
        list1.addLast(24);
        list1.addLast(29);
        list1.addLast(25);
        list1.addLast(24);
        list1.addLast(13);


        list1.display();
//        list1.oddEven();
//        list1.display();
//        list1.fold();
//        list1.display();
//        list1.reverseDataRecursive();
//        list1.display();

        list1.reverseDataIterative();
        list1.display();

        list1.reversePointerRecursive();
        list1.display();

        list1.reversePointerIteravtive();
        list1.display();

//        list1.mergeSort();
//        list1.display();
//        list1.removeDuplicates();
//        list1.display();
//        System.out.println(list1.isPalindrome());
//        list1.display();
//        list1.kReverse(3);
//        list1.display();

        list1.delMAfterN2(2, 0);
//        list1.removeEveryKthNode(3);
        list1.display();

        MyLinkedList numberList = new MyLinkedList();
        numberList.addLast(9);
        numberList.addLast(9);
        numberList.addLast(9);
        numberList.addLast(9);
        numberList.display();
        numberList.addOne();
        numberList.display();

        MyLinkedList list012 = new MyLinkedList();
        list012.addLast(2);
        list012.addLast(1);
        list012.addLast(2);
        list012.addLast(2);
        list012.addLast(2);
        list012.addLast(0);
        list012.addLast(1);
        list012.addLast(1);
        list012.addLast(0);
        list012.addLast(0);
        list012.addLast(0);
        list012.addLast(1);
        list012.addLast(1);
        list012.addLast(2);
        list012.display();
        list012.sort012Links();
        list012.display();

//        list012.makeLoop();
//        list012.findAndRemoveLoop();
//        list012.display();
    }
}