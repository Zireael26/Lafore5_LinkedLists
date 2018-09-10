public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        MyLinkedList list1 = new MyLinkedList();
        list1.addFirst(25);
        list1.addLast(24);
        list1.addLast(13);
        list1.addLast(13);
        list1.addLast(24);
        list1.addLast(25);

        list1.display();
        list1.fold();
        list1.display();

        System.out.println(list1.isPalindrome());
    }
}
