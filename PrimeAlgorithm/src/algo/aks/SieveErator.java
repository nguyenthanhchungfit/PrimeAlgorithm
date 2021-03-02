/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo.aks;

/**
 *
 * @author chungnt
 */
public class SieveErator {

    private boolean _sieveArray[];
    private static int SIEVE_ERATOS_SIZE = 100000000;

    public SieveErator() {
        int i, j;
        _sieveArray = new boolean[SIEVE_ERATOS_SIZE + 1];
        _sieveArray[1] = true;
        for (i = 2; i * i <= SIEVE_ERATOS_SIZE; i++) {
//            long begTime = System.currentTimeMillis();
            if (_sieveArray[i] != true) {
                for (j = i * i; j <= SIEVE_ERATOS_SIZE; j += i) {
                    _sieveArray[j] = true;
                }
            }
//            long endTime = System.currentTimeMillis();
//            System.out.println("exec: " + (endTime - begTime));
//            break;
        }
    }

    public boolean isSievePrime(int val) {
        if (val < 0 || val > SIEVE_ERATOS_SIZE) {
            return false;
        }
        return !_sieveArray[val];
    }

    public boolean get(int idx) {
        return _sieveArray[idx];
    }
}
