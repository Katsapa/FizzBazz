package org.example;

import java.util.concurrent.CountDownLatch;

public class SimplyThread {
    private static Thread thread;
    private static final Object obj = new Object();
    private volatile static boolean conditions = false;
    private static final CountDownLatch ready = new CountDownLatch(1);

    private void startThread(){

        thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " работает");
            try{
                synchronized (obj){
                    ready.countDown();
                    System.out.println("Вошёл в synchronized");
                    while(!conditions){
                        obj.wait();
                    }
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
    }

    private void notifyThread(){
        try{
            ready.await();
            Thread.sleep(1000);
            System.out.println("the thread was sleep for 1000 mil");
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }
        synchronized (obj){
            conditions = true;
            obj.notify();
            System.out.println("Поток пробуждён");
        }
    }

    public static void main(String[] args) {
        SimplyThread testClass = new SimplyThread();
        testClass.startThread();
        testClass.notifyThread();
    }
}
