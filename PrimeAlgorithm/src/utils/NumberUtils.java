/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author chungnt
 */
public class NumberUtils {

    public static int parseInt(String sNumber, int defaultVal) {
        int number = defaultVal;
        try {
            number = Integer.parseInt(sNumber);
        } catch (Exception ex) {
        }
        return number;
    }
}
