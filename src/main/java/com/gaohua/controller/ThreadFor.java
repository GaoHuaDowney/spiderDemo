package com.gaohua.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFor {
	private static final int loopNum = 1*10;  
    
    public static void main(String args[]) throws InterruptedException {  
    	ThreadFor TestThreadPool = new ThreadFor();  
        long bt = System.currentTimeMillis();  
        List<String> list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        TestThreadPool.m1(list);  
        long et2 = System.currentTimeMillis();  
        System.out.println("[1]耗时:"+(et2 - bt)+ "ms");  
        Thread thread = new Thread();  
        long at = System.currentTimeMillis();  
        TestThreadPool.m2();
        long et3 = System.currentTimeMillis();
        System.out.println("[2]耗时:"+(et3 - at)+ "ms");
          
    }  
  
    public void m1( List<String> list) {
        ExecutorService pool = Executors.newCachedThreadPool();  
        for (int i = 0; i < list.size(); i++) {
        	String str = list.get(i);
        	System.out.println(list.get(i));
            Runnable run = new Runnable() {  
                public void run() {  
                    try {  
                        new Thread().sleep(1000);
                        //模拟耗时操作
                    	System.out.println("[1]" + Thread.currentThread().getName()+"----"+str);
                    } catch (Exception e) {  
                    }  
                }  
            }; 
            pool.execute(run);  
		
		}
        System.out.println("[1] done!");
        pool.shutdown();  
    }  
  
    public void m2() { 
    	AtomicInteger connectionIds = new AtomicInteger(0);
        for (int index = 0; index < loopNum; index++) {  
            try {  
                new Thread().sleep(1000);  //模拟耗时操作
                System.out.println("[2]" + Thread.currentThread().getName());
                
            } catch (Exception e) {  
                e.printStackTrace();  
            } 
        }  
        System.out.println("[2] done!");
    }  
}