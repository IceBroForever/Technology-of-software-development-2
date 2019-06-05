package com.zulus.task1;

public class Consumer implements Runnable {
    private SmartNumbersBuffer buffer;
    private int sleepDelay;

    public Consumer(SmartNumbersBuffer buffer, int sleepDelay) {
        if (buffer == null) throw new IllegalArgumentException("Buffer cannot be null");
        if (sleepDelay < 0) throw new IllegalArgumentException("Sleep delay must be positive");
        new Thread(this, "Consumer").start();
        this.buffer = buffer;
        this.sleepDelay = sleepDelay;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.sleepDelay);
                System.out.println(buffer.getSumOfSquares());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
