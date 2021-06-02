package com.cdx.demo01;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Queue {
    // 共享数据，只有一个线程能够写该数据，但可以有多个线程同时读该数据
    private Object data = null;

    ReadWriteLock lock = new ReentrantReadWriteLock();

    // 读数据
    public void get() {
        // 加读锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to read data!");
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + " have read data :" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.readLock().unlock();
        }
    }

    // 写数据
    public void put(Object data) {
        // 加写锁
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " be ready to write data!");
            Thread.sleep((long) (Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " have write data: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.writeLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        final Queue queue = new Queue();
        // 一共启动6个线程，3个读线程，3个写线程
        for (int i = 0; i < 3; i++) {
            // 启动一个读线程
            new Thread(() -> {
                while (true) {
                    queue.get();
                }
            }).start();
            // 启动一个写线程
            new Thread(() -> {
                while (true) {
                    queue.put(new Random().nextInt(10000));
                }
            }).start();
        }
    }
}
