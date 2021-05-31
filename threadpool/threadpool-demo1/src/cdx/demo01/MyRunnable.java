package cdx.demo01;

import java.util.Date;

/**
 * 这是一个简单的Runnable类，需要大约五秒钟来执行其任务
 *
 * @author cdx
 * @date 2021年5月31日16:51:39
 */
public class MyRunnable implements Runnable {

    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + "End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.command;
    }
}
