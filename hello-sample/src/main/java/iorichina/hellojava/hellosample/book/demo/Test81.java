package iorichina.hellojava.hellosample.book.demo;

public class Test81 {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory()/1024/1024);
        byte[] placeholder = new byte[1024 * 1024 * 64];
        System.out.println();
        System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory()/1024/1024);
        System.gc();
        System.out.println();
        System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory()/1024/1024);
        System.gc();
        System.out.println();
        System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory()/1024/1024);
        // 到这里可以回收了
        placeholder = null;
        System.out.println();
        System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory()/1024/1024);
        System.gc();
        System.out.println();
        System.out.println("Runtime.getRuntime().totalMemory() = " + Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().freeMemory() = " + Runtime.getRuntime().freeMemory()/1024/1024);
        System.out.println("Runtime.getRuntime().maxMemory() = " + Runtime.getRuntime().maxMemory()/1024/1024);
    }
}
