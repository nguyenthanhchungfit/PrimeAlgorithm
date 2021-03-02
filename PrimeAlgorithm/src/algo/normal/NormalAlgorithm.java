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
public class NormalAlgorithm implements IPrimeNumberChecker {

    @Override
    public PrimeResult isPrime(BigInteger number) {
        PrimeResult primeResult = new PrimeResult();
        BigInteger sqrtNum = BigIntegerUtils.sqrt(number);
        BigInteger idx = new BigInteger("2");
        boolean isPrime = true;
        for (; idx.compareTo(sqrtNum) <= 0; idx = idx.add(BigInteger.ONE)) {
            if (number.mod(idx).compareTo(BigInteger.ZERO) == 0) {
                isPrime = false;
                break;
            }
        }
        primeResult.setIsPrime(isPrime);
        return primeResult;
    }

    @Override
    public String getName() {
        return "Normal";
    }
}
