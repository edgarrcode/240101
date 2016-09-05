/*
Author: Edgar E. Rodriguez
TA: Khandoker A Rahad
Professor: Mahmud Hossain
LMD: 8/30/16
*/

import java.util.*;
import java.io.*;

public class fuelExpensesJagged {
    //get gas records from file
    public static double[][] readFile (String fileName) throws IOException {
        String line;
        FileReader reader = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(reader);
        buffer.mark(1000); //here we assume that the file won't be longer than 1000 lines
        
        //count lines
        int numOfLines = 0;
        while ((line = buffer.readLine()) != null) {
            numOfLines++;
        }

        double[][] gasRecords = new double[numOfLines][];//create empty double 2d array

        //assign lines to 2d array
        buffer.reset();
        for (int i = 0; i < numOfLines; i++) {
            String[] eachLineStringArray = buffer.readLine().split(" "); //"1", "2", "3" an array of strings per line
            double[] doubleArray = new double[eachLineStringArray.length]; //new double array, length of the string array
            for (int j = 0; j < eachLineStringArray.length; j++) {
                doubleArray[j] = Double.parseDouble(eachLineStringArray[j]); //assign each string array item to double arrays as a double
            }
            gasRecords[i] = doubleArray; //assign 1d array to 2d array
        }


        /*File gasFile = new File(fileName);
        try {
            Scanner scnr = new Scanner(gasFile);
            if (scnr.hasNext()) {
                System.out.print("hello");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        return gasRecords;
    }

    //sum double 1d array values
    public static double doubleArraySum (double[] d) {
        double doubleArraySum = 0;
        for (int i = 0; i < d.length; i++) {
            doubleArraySum = doubleArraySum + d[i];
        }

        return doubleArraySum;
    }

    //total gallons of gas pumped into each vehicle
    public static double[] totalGasGallonsPerCar (double[][] gasRecords) {
        double[] totalGasGallonsPerCar = new double[gasRecords.length]; //create empty 1d double array

        for (int i = 0; i < gasRecords.length; i++) {
            totalGasGallonsPerCar[i] = doubleArraySum(gasRecords[i]); //assign sum of gas records to totalGasGallonsPerCar
        }
        return totalGasGallonsPerCar;
    }

    //average amount of gas pumped during a fueling for each vehicle
    public static double[] avgGasGallonsPerCar (double[][] gasRecords) {
        double[] avgGasGallonsPerCar = new double[gasRecords.length]; //create empty 1d double array

        for (int i = 0; i < gasRecords.length; i++) {
            avgGasGallonsPerCar[i] = doubleArraySum(gasRecords[i]) / gasRecords[i].length; //assign avg of gas records to avgGasGallonsPerCar
        }
        return avgGasGallonsPerCar;
    }

    //get largest row length in 2d array
    public static int largestRowLength (double[][] gasRecords) {
        int largestRowLength = 0;
        for (int i = 0; i < gasRecords.length; i++) {
            if (largestRowLength < gasRecords[i].length) {
                largestRowLength = gasRecords[i].length;
            }
        }
        return largestRowLength;
    }

    //average gas amount pumped during the first, second, third, …, nth fueling of the week
    public static double[] avgGasGallonPerWeek (double[][] gasRecords) {
        int largestRowLength = largestRowLength(gasRecords); //get largest row length
        double[] avgGasGallonPerWeek = new double[largestRowLength];//array to store averages per week
        double[] weekArrayTemp = new double[gasRecords.length]; //array to store temp col values
        for (int i = 0; i < largestRowLength; i++) {
            for (int j = 0; j < gasRecords.length; j++) {
                weekArrayTemp[j] = 0; //reset temp to 0
                if (gasRecords[j].length > i) {
                    weekArrayTemp[j] = gasRecords[j][i]; // creating temporary 1d double array

                }
            }
            avgGasGallonPerWeek[i] = doubleArraySum(weekArrayTemp) / gasRecords.length; //adding weekly values to array. Result of dividing column sum divided by weeks amount
        }

        return avgGasGallonPerWeek;
    }

    //maximum amount of gas pumped into each vehicle
    public static double[] maxGasPerCar (double[][] gasRecords) {
        double[] maxGasPerCar = new double[gasRecords.length];
        for (int i = 0; i < gasRecords.length; i++) {
            double maxTemp = gasRecords[i][0];
            for (int j = 1; j < gasRecords[i].length; j++) {
                if (maxTemp < gasRecords[i][j]) {
                    maxTemp = gasRecords[i][j];
                }
            }
            maxGasPerCar[i] = maxTemp;
        }

        return maxGasPerCar;
    }

    //minimum amount of gas pumped into each vehicle
    public static double[] minGasPerCar (double[][] gasRecords) {
        double[] minGasPerCar = new double[gasRecords.length];
        for (int i = 0; i < gasRecords.length; i++) {
            double minTemp = gasRecords[i][0];
            for (int j = 1; j < gasRecords[i].length; j++) {
                if (minTemp > gasRecords[i][j]) {
                    minTemp = gasRecords[i][j];
                }
            }
            minGasPerCar[i] = minTemp;
        }

        return minGasPerCar;
    }


    public static void main (String[] args) throws IOException {
        String fileName = "";
        Scanner scnr = new Scanner(System.in);
        System.out.println ("What is the name of the file?");
        double[][] gasRecords = null;
        try {
            fileName = scnr.nextLine();
            gasRecords = readFile(fileName); //get 2d array from file
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            System.exit(1);
        }

        //total gallons of gas pumped into each vehicle
        double[] totalGasGallonsPerCar = totalGasGallonsPerCar(gasRecords); //get double array, each array item is the total of gallons pumped per car
        for (int i = 0; i < totalGasGallonsPerCar.length; i++) {
            System.out.println("Car Number " + (i + 1) + " pumped a total of " + totalGasGallonsPerCar[i] + " gallons.");
        }

        //average amount of gas pumped during a fueling for each vehicle
        double[] avgGasGallonsPerCar = avgGasGallonsPerCar(gasRecords); //get double array, each array item is the avg of gallons pumped per car
        for (int i = 0; i < avgGasGallonsPerCar.length; i++) {
            System.out.println("Car Number " + (i + 1) + " pumped an average of " + avgGasGallonsPerCar[i] + " per fueling.");
        }

        //average gas amount pumped during the first, second, third, …, nth fueling of the week
        double[] avgGasGallonPerWeek = avgGasGallonPerWeek(gasRecords); //get double array, each array item is the avg of gallons pumped per week
        for (int i = 0; i < avgGasGallonPerWeek.length; i++) {
            System.out.println("Week " + (i + 1) + " average is " + avgGasGallonPerWeek[i]);
        }

        //maximum amount of gas pumped into each vehicle
        double[] maxGasPerCar = maxGasPerCar(gasRecords); //get double array, each array item is the max amount of gas pumped by a car in all per weeks
        for (int i = 0; i < maxGasPerCar.length; i++) {
            System.out.println("The maximum amount of gas pumped by car " + (i + 1) + " in a week was " + maxGasPerCar[i]);
        }

        //minimum amount of gas pumped into each vehicle
        double[] minGasPerCar = minGasPerCar(gasRecords); //get double array, each array item is the max amount of gas pumped by a car in all per weeks
        for (int i = 0; i < minGasPerCar.length; i++) {
            System.out.println("The minimum amount of gas pumped by car " + (i + 1) + " in a week was " + minGasPerCar[i]);
        }

    }
}