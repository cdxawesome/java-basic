package com.cdx.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的简单使用
 */
public class LockDemo {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        // 获取锁
        lock.lock();
        try {
            // 处理任务
            System.out.println("do something here...");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            // 释放锁
            lock.unlock();
        }
    }
}
