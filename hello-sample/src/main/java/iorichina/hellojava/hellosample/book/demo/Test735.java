package iorichina.hellojava.hellosample.book.demo;

public class Test735 {
    static {
        i = 2;
        // illegal forward reference
//        System.out.println(i);

        // the code will block here, and other code could not load this class
        /*if (true) {
            System.out.println("init class");
            while (true) {

            }
        }*/
    }

    static int i = 1;


    static class Parent {
        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
