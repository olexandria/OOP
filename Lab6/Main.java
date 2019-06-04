package com.dypko;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int length =1000000;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the time the program is running:");
        (new Do_Something(in.nextInt())).start();
        ArrayList<Future<double[]>> arr=new ArrayList();
        ArrayList<double[]> arr1=new ArrayList();
        ExecutorService s = Executors.newFixedThreadPool(4);
        long start_time = System.currentTimeMillis();
        long time1,time2;
        for(int i=0;i<3;i++) {
            arr.add(s.submit(new MyCallable(length)));
        }

        s.shutdown();
        try {
            s.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        time1=System.currentTimeMillis()-start_time;
        start_time=System.currentTimeMillis();

        for(int i=0;i<3;i++) {
            arr1.add(new MyCallable(length).call());
        }
        time2=System.currentTimeMillis()-start_time;
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "Тест", "Час виконання","К-сть потоків","К-сть даних");
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#1", time1,3,length);
        System.out.format("|%-15s|%-15s|%-15s|%-15s|%n", "#2", time2,3,length);
        System.out.println("+---------------+---------------+---------------+---------------+");
        System.out.println("Результати:");
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");
        System.out.format("|%-15s|%-25s|%-25s|%-25s|%-25s|%-20s|%n", "Потік", "Min", "Max","Sum","Average","Час виконання");
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");
        for (int i = 0; i < 3; i++) {
            try {
                System.out.format("|%-15s|%-25s|%-25s|%-25s|%-25s|%-20s|%n", "Паралельний " + (i+1),arr.get(i).get()[1] , arr.get(i).get()[0],arr.get(i).get()[4],arr.get(i).get()[2],arr.get(i).get()[3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");
        for (int i = 0; i < 3; i++) {
            System.out.format("|%-15s|%-25s|%-25s|%-25s|%-25s|%-20s|%n", "Послідовний " + (i+1),arr1.get(i)[1] , arr1.get(i)[0],arr1.get(i)[4],arr1.get(i)[2],arr1.get(i)[3]);
        }
        System.out.println("+---------------+-------------------------+-------------------------+-------------------------+-------------------------+--------------------+");

    }
}