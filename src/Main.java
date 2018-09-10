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


//        list1.display();
//        list1.oddEven();
//        list1.display();
//        list1.fold();
//        list1.display();

//        list1.mergeSort();
//        list1.display();
//        list1.removeDuplicates();
//        list1.display();
//        System.out.println(list1.isPalindrome());
        list1.display();
        list1.kReverse(3);
        list1.display();

        list1.delMAfterN(2, 3);
        list1.display();
    }
}
