package com.zulus.task1;

public class Generator implements Runnable {
    private SmartNumbersBuffer buffer;
    private double leftGenerationBound;
    private double generationRange;

    public Generator(SmartNumbersBuffer buffer, double leftGenerationBound, double rigthGenerationBound) {
        if (buffer == null) throw new IllegalArgumentException("Argument buffer cannot be null");
        this.buffer = buffer;
        this.generationRange = rigthGenerationBound - leftGenerationBound;
        this.leftGenerationBound = leftGenerationBound;
        new Thread(this, "Generator").start();
    }

    @Override
    public void run() {
        while (true) {
            this.buffer.append(this.leftGenerationBound + Math.random() * generationRange);
        }
    }
}
