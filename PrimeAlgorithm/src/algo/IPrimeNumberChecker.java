/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.math.BigInteger;

/**
 *
 * @author chungnt
 */
public interface IPrimeNumberChecker {

    PrimeResult isPrime(BigInteger number);
    
    String getName();
}
