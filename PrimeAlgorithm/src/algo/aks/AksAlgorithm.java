/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.aks;

import algo.IPrimeNumberChecker;
import algo.PrimeResult;
import java.math.BigInteger;
import utils.BigIntegerUtils;

/**
 *
 * @author chungnt
 */
public class AksAlgorithm implements IPrimeNumberChecker {

    private static SieveErator _sieveErator = new SieveErator();

    private int findLargestFactor(int num) {
        int i = num;
        if (i == 1) {
            return i;
        }
        while (i > 1) {
            while (_sieveErator.get(i)) {
                i--;
            }
            if (num % i == 0) {
                return i;
            }
            i--;
        }
        return num;
    }

    @Override
    public PrimeResult isPrime(BigInteger number) {
        PrimeResult primeResult = new PrimeResult();
        // B1: find power n, b^n = number
        int log10 = (int) BigIntegerUtils.log10BigNum(number);
//        int log2 = BigIntegerUtils.log2BigNum(number);
        if (BigIntegerUtils.findPower(number, log10) >= 2) {
            primeResult.setIsPrime(false); // COMPOSITE
            return primeResult;
        }

        BigInteger lowR = new BigInteger("2");
        BigInteger x = lowR;
        int totR = lowR.intValue();
        for (lowR = new BigInteger("2"); lowR.compareTo(number) < 0; lowR = lowR.add(BigInteger.ONE)) {
            if (lowR.gcd(number).compareTo(BigInteger.ONE) > 0) {
                primeResult.setIsPrime(false); // COMPOSITE
                return primeResult;
            }
            totR = lowR.intValue();
//            System.out.println("totR: " + totR + ", cmp: " + lowR.compareTo(number));
            if (_sieveErator.isSievePrime(totR)) {

                int quot = findLargestFactor(totR - 1);
                int divisor = (int) (totR - 1) / quot;
                int tm = (int) (4 * Math.sqrt(totR) * log10);
                BigInteger powerOf = BigIntegerUtils.modPower(number, new BigInteger("" + divisor), lowR);

                if (quot >= tm && (powerOf.compareTo(BigInteger.ONE)) != 0) {
//                    System.out.println("totR: " + totR + ", quot: " + quot + ", divisor: " + divisor + ", tm: " + tm + ", powerOf: " + powerOf);
                    break;
                }
            }
        }

        int aLimit = (int) (2 * Math.sqrt(totR) * log10);
        for (int aCounter = 1; aCounter < aLimit; aCounter++) {
            BigInteger aBigNum = new BigInteger("" + aCounter);
            BigInteger leftH = BigIntegerUtils.modPower(x.subtract(aBigNum), number, number).mod(number);
            BigInteger rightH = BigIntegerUtils.modPower(x, number, number).subtract(aBigNum).mod(number);
            if (leftH.compareTo(rightH) != 0) {
                primeResult.setIsPrime(false); // COMPOSITE
                return primeResult;
            }
        }
        primeResult.setIsPrime(true); // PRIME
        return primeResult;
    }

    @Override
    public String getName() {
        return "AKS";
    }

}

//
// *  * The algorithm is -
// * 1. l <- log n
//   2. for i<-2 to l
// *      a. if an is a power fo l
// *              return COMPOSITE
// * 3. r <- 2
// * 4. while r < n
// *      a. if gcd( r, n) != 1
// *              return COMPSITE
// *      b. if sieve marked n as PRIME
// *              q <- largest factor (r-1)
// *              o < - r-1 / q
// *              k <- 4*sqrt(r) * l
// *              if q > k and n <= r 
// *                      return PRIME
// *      c. x = 2
// *      d. for a <- 1 to k 
// *              if (x + a) ^n !=  x^n + mod (x^r - 1, n) 
// *                      return COMPOSITE
// *      e. return PRIME
