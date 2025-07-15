package iorichina.hellojava.hellosample.test;

public class TestSample {
    public static void main(String[] args) {
        int i = 0b000010110110;
        int leadingZeros = Integer.numberOfTrailingZeros(i);
        System.out.println(leadingZeros); // 输出：4
    }
}
