/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.normal;

import algo.IPrimeNumberChecker;
import algo.PrimeResult;
import java.math.BigInteger;
import utils.BigIntegerUtils;

/**
 *
 * @author chungnt
 */
public class SieveEratosthenesAlgorithm implements IPrimeNumberChecker {

    private BigInteger[] PRIMES;
    private int limitSize;

    public SieveEratosthenesAlgorithm() {
        if (this.limitSize <= 0) {
            this.limitSize = 1000000; // 1.000.000 phần tử
        }
        initPrimes(true);
    }

    public SieveEratosthenesAlgorithm(int limitSize, boolean preInit) {
        this.limitSize = limitSize;
        initPrimes(preInit);
    }

    private void initPrimes(boolean preInit) {
        PRIMES = new BigInteger[limitSize];
        PRIMES[0] = new BigInteger("2");
        PRIMES[1] = new BigInteger("3");
        if (preInit) {
            preInitListPrimes(new BigInteger("" + limitSize));
        }
    }

    @Override
    public PrimeResult isPrime(BigInteger number) {
        PrimeResult primeResult = new PrimeResult();
        boolean isPrime = _isPrimeNum(number);
        primeResult.setIsPrime(isPrime);
        return primeResult;
    }

    private boolean _isPrimeNum(BigInteger number) { // "4 >>"
        try {
            int idx = 0;
            boolean isRemain = true;
            BigInteger sqrtNum = BigIntegerUtils.sqrt(number);
            do {
                if (idx >= limitSize) {
                    throw new Exception("Out of range sizeof(PRIMES)=" + limitSize);
                }
                if (PRIMES[idx] == null) {
                    if (PRIMES[idx - 1] != null) {
                        if (PRIMES[idx - 1].compareTo(sqrtNum) > 0) {
                            return true;
                        } else {
                            BigInteger nextNumber = PRIMES[idx - 1].add(BigInteger.ONE);
                            while (nextNumber.compareTo(sqrtNum) <= 0) {
                                boolean isPrime = _isPrimeNum(nextNumber);
                                if (isPrime) {
                                    PRIMES[idx] = nextNumber;
                                    if (number.mod(PRIMES[idx]).compareTo(BigInteger.ZERO) == 0) {
                                        return false;
                                    } else {
                                        break;
                                    }
                                }
                                nextNumber = nextNumber.add(BigInteger.ONE);
                            }
                            if (PRIMES[idx] != null) {
                                isRemain = PRIMES[idx].compareTo(sqrtNum) <= 0;
                            } else {
                                return true;
                            }
                        }
                    }
                } else {
                    if (number.mod(PRIMES[idx]).compareTo(BigInteger.ZERO) == 0) {
                        return false;
                    }
                    isRemain = PRIMES[idx].compareTo(sqrtNum) <= 0;
                }

                idx++;
            } while (isRemain);
            return true;
        } catch (Exception ex) {
            System.err.println("error: " + ex.getMessage());
            return false;
        }
    }

    public void preInitListPrimes(BigInteger limitNum) {
        BigInteger three = new BigInteger("3");
        if (limitNum.compareTo(three) > 0) {
            System.out.println("init list prime with values < " + limitNum.toString() + " ...");
            BigInteger number = new BigInteger("4");
            while (number.compareTo(limitNum) < 0) {
                _isPrimeNum(number);
                number = number.add(BigInteger.ONE);
            }
        }
        System.out.println("init list prime done!");
//        showcurListPrime();
    }

    public void showcurListPrime() {
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        boolean isFirst = false;
        while (idx < limitSize && PRIMES[idx] != null) {
            if (!isFirst) {
                isFirst = true;
                sb.append(PRIMES[idx]);
            } else {
                sb.append(", ").append(PRIMES[idx]);
            }
            idx++;
        }
        System.out.println("listPrimes: " + sb.toString());

    }

    @Override
    public String getName() {
        return "SieveEratosthenes";
    }
}
