package iorichina.hellojava.hellosample.access.controller.sub;

import iorichina.hellojava.hellosample.access.controller.AccessControlSample;

/**
 * Created by iorichina on 2017/7/21 0021.
 */
public class AccessControlSampleSub extends AccessControlSample {
    public static void main(String[] args){
        AccessControlSampleSub sub = new AccessControlSampleSub();

//        System.out.println(sub.defaultAC);//different package can not access
        System.out.println(sub.protectedAC);
    }
}
