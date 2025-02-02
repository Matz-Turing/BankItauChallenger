package com.itau.transaction.model;

public class Statistics {
    private long count;
    private double sum;
    private double avg;
    private double min;
    private double max;

    public Statistics() {
        this.count = 0;
        this.sum = 0.0;
        this.avg = 0.0;
        this.min = 0.0;
        this.max = 0.0;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
