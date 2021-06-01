package com.cdx.demo01;

public class VolatileDemo {
    // 声明一个volatile的变量
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag) {
                }
                System.out.println("线程停止");
            }
        });
        t.start();
        // 线程暂停5秒钟之后，修改volatile变量flag的值为true，则t线程感知到值的变化，循环结束，t线程结束。
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        flag = true;
    }
}
