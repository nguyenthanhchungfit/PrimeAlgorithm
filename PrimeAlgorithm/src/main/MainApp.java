/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import algo.IPrimeNumberChecker;
import algo.PrimeResult;
import algo.aks.AksAlgorithm;
import algo.normal.NormalAlgorithm;
import algo.normal.SieveEratosthenesAlgorithm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.NumberUtils;

/**
 *
 * @author chungnt
 */
public class MainApp {

    public static void main(String[] args) {

        String algo = System.getProperty("algo", "1");
        String inputFile = System.getProperty("inputFile", "");
        String outputFile = System.getProperty("outputFile", "");
        String checkNum = System.getProperty("checkNum", "");

        if (inputFile.isEmpty() && checkNum.isEmpty()) {
            System.out.println("Empty InputFile and empty checkNum");
            showUsage();
            return;
        }
        IPrimeNumberChecker checker = null;
        switch (algo) {
            case "2":
                checker = new NormalAlgorithm();
                break;
            case "3":
                checker = new SieveEratosthenesAlgorithm(10000, true);
                break;
            default:
                checker = new AksAlgorithm();
        }

        System.out.println("Using Algo: " + checker.getName());

        if (!checkNum.isEmpty()) {
            BigInteger number = new BigInteger(checkNum);
            PrimeResult result = checker.isPrime(number);
            String numType = result.isIsPrime() ? "prime" : "not prime";
            System.out.println(String.format("%s is %s, execTime: %d (ms)", number, numType, result.getExecTime()));
        } else {
            processInputFileTest(inputFile, outputFile, checker);
        }

    }

    private static void processInputFileTest(String inputFile, String outputFile, IPrimeNumberChecker checker) {
        File fileInput = new File(inputFile);
        if (fileInput.exists()) {
            if (outputFile.isEmpty()) {
                outputFile = "outputResult";
                outputFile += "-" + checker.getName();
            }

            File fileOutput = new File(outputFile);
            if (fileOutput.exists()) {
                fileOutput.delete();
            }

            // read line 
            BufferedReader reader = null;
            BufferedWriter writer = null;
            try {
                reader = new BufferedReader(new FileReader(
                    fileInput));
                fileOutput.createNewFile();
                writer = new BufferedWriter(new FileWriter(outputFile, true));
                writer.append(String.format("%90sNumber\tActual\tExpect\tExecTime(ms)\n", ""));
                String line = reader.readLine();
                while (line != null) {
//                    System.out.println(line);

                    String[] arrParams = line.split("\t");
                    if (arrParams != null && arrParams.length >= 2) {
                        BigInteger number = new BigInteger(arrParams[0]);
                        System.out.print("checking: " + number + "...");
                        int expectedPrime = NumberUtils.parseInt(arrParams[1], -1);
                        PrimeResult isPrimeResult = checker.isPrime(number);
                        int actualIsPrime = isPrimeResult.isIsPrime() ? 1 : 0;
                        String numType = isPrimeResult.isIsPrime() ? "prime" : "not prime";
                        System.out.println("-> is " + numType + ", execTime: " + isPrimeResult.getExecTime() + " (ms)");
                        String checkReport = String.format("%100s\t%d\t%d\t%,d\n", number.toString(),
                            actualIsPrime, expectedPrime, isPrimeResult.getExecTime());
                        writer.append(checkReport);
                    }
                    // read next line
                    line = reader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            System.out.println(String.format("File %s is not exist!!!", inputFile));
        }
    }

    private static void showUsage() {
        System.out.println("---------------------------------------------------\n");
        System.out.println("S1: $ java -jar -DcheckNum=10  PrimeAlgorithm.jar\t: To check 10 is prime or not?");
        System.out.println("S2: $ java -jar -DinputFile=inputResult -DoutputFile=outputResult  PrimeAlgorithm.jar\t: "
            + "To read input contain prime numbers test from file and export output test");
        System.out.println("\n---------------------------------------------------");
    }
}
