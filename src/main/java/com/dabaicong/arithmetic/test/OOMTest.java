package com.dabaicong.arithmetic.test;

import sun.tools.attach.HotSpotVirtualMachine;

public class OOMTest {

    public static void main(String[] args) {
        OOMTest test = new OOMTest();
        test.stackOOM();
    }

    public void stackOOM(){
        this.stackOOM();
    }

    public void stackOverLimit(){

    }



//    private class Test
}

