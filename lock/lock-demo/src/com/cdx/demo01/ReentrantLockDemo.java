package com.cdx.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    Lock lock = new ReentrantLock();

    public void lock(String name) {
        // 获取锁
        lock.lock();
        try {
            // 手动让线程等待1秒，不然的话有时候CPU的执行速度过快，执行的先后顺序很难看出来。
            Thread.sleep(1000);
            System.out.println(name + " get the lock");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
            System.out.println(name + " release the lock");
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo demo = new ReentrantLockDemo();
        new Thread(() -> demo.lock("A")).start();
        new Thread(() -> demo.lock("B")).start();
    }
}
