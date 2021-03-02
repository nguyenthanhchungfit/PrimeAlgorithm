/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author chungnt
 */
public class BigIntegerUtils {

    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength() / 2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for (;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2)) {
                return y;
            }
            div2 = div;
            div = y;
        }
    }

    public static double log10BigNum(BigInteger number) {
        String str = "." + number.toString();
        int length = str.length() - 1;
        double doubleNum = Double.parseDouble(str);
        return Math.log10(doubleNum) + length;
    }

    public static int log2BigNum(BigInteger number) {
        return number.bitLength() - 1;
    }

    // check if bigNum = b ^ val
    public static boolean findPowerOf(BigInteger bigNum, int val) {
        BigInteger low = new BigInteger("10");
        BigInteger high = new BigInteger("10");
        BigInteger mid, res;
        BigInteger two = new BigInteger("2");
        int len = (int) Math.ceil((bigNum.toString().length()) / val);
        low = low.pow(len - 1);
        high = high.pow(len).subtract(BigInteger.ONE);
        while (low.compareTo(high) <= 0) {
            mid = low.add(high);
            mid = mid.divide(two);
            res = mid.pow(val);
            int cmpToBNum = res.compareTo(bigNum);

            if (cmpToBNum < 0) {
                low = mid.add(BigInteger.ONE);
            } else if (cmpToBNum > 0) {
                high = mid.subtract(BigInteger.ONE);
            } else { // = 0
                return true;
            }
        }
        return false;
    }

    // find the number x, if has y such that: num = y ^ x
    public static int findPower(BigInteger number, int log10) {
        for (int i = 2; i < log10; i++) {
            if (findPowerOf(number, i)) {
                return i;
            }
        }
        return -1;
    }

    // calculate x^y mod num 
    //  (Ferma 3.6, but it is Pseudo-prime Numbers)
    public static BigInteger modPower(BigInteger x, BigInteger y, BigInteger num) {
        BigInteger two = new BigInteger("2");
        BigInteger m = y;
        BigInteger p = BigInteger.ONE;
        BigInteger z = x;

        while (m.compareTo(BigInteger.ZERO) > 0) {
            while (m.mod(two).compareTo(BigInteger.ZERO) == 0) {
                m = m.divide(two);
                z = z.multiply(z).mod(num);
            }
            m = m.subtract(BigInteger.ONE);
            p = p.multiply(z.mod(num));
        }
        return p;
    }

    public static void main(String[] args) {
        BigInteger big = new BigInteger("1024");
        System.out.println("log2(big) = " + log2BigNum(big));
//        System.out.println("sqrt: " + sqrt(big));
//        System.out.println("mod: " + big.mod(BigInteger.TEN));
    }
}
