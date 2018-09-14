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
        list1.oddEven();
        System.out.println("************** Displaying after OddEven Rearrangement *************");
        list1.display();
        list1.fold();
        System.out.println("************** Displaying after Folding ***************************");
        list1.display();
        list1.reverseDataRecursive();
        System.out.println("************** Displaying after Reversing data recursively ********");
        list1.display();

        list1.reverseDataIterative();
        System.out.println("************** Displaying after Reversing data iteratively ********");
        list1.display();

        list1.reversePointerRecursive();
        System.out.println("************** Displaying after Reversing pointer recursively *****");
        list1.display();

        list1.reversePointerIteravtive();
        System.out.println("************** Displaying after Reversing pointer iteratively *****");
        list1.display();

        list1.mergeSort();
        System.out.println("************** Displaying after performing merge sort *************");
        list1.display();
        list1.removeDuplicates();
        System.out.println("************** Displaying after removing duplicates ***************");
        list1.display();
        System.out.println("Is the list a palindrome: " + list1.isPalindrome());
        list1.display();
        // don't call kReverse before uncommenting remove duplicates
//        list1.kReverse(3);
        System.out.println("************** Displaying after k-reversing, with k=3 *************");
        list1.display();

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

        list012.makeLoop();
        list012.findAndRemoveLoop();
        list012.display();
    }
}