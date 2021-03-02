/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

/**
 *
 * @author chungnt
 */
public class PrimeResult {

    private boolean isPrime;
    private long execTime; // in miliseconds
    private long startTime;
    private long endTime;

    public PrimeResult(boolean isPrime, long execTime) {
        this.isPrime = isPrime;
        this.execTime = execTime;
    }

    public PrimeResult() {
        startTime = System.currentTimeMillis();
    }

    public void setIsPrime(boolean isPrime) {
        this.isPrime = isPrime;
        this.endTime = System.currentTimeMillis();
        this.execTime = this.endTime - this.startTime;
    }

    public boolean isIsPrime() {
        return isPrime;
    }

    public long getExecTime() {
        return execTime;
    }

    @Override
    public String toString() {
        return "PrimeResult{" + "isPrime=" + isPrime + ", execTime=" + execTime + '}';
    }
}
