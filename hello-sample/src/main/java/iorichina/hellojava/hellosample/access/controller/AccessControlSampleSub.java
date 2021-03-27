package iorichina.hellojava.hellosample.access.controller;

/**
 * Created by iorichina on 2017/7/21 0021.
 */
public class AccessControlSampleSub extends AccessControlSample {
    public static void main(String[] args){
        AccessControlSampleSub sub = new AccessControlSampleSub();

        System.out.println(sub.defaultAC);//same package can access
        System.out.println(sub.protectedAC);
    }
}
