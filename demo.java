/*
Author: Edgar E. Rodriguez
TA: Khandoker A Rahad
Professor: Mahmud Hossain
LMD: 8/30/16
*/

import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class demo {
    public static void fileReader(String fileName) throws IOException {
        String line;
        FileReader reader = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(reader);
        while ((line = buffer.readLine()) != null) {
            System.out.println(line);
            //i might count here
        }
    }


    public static void main (String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println ("What is the name of the file?");
        try {
            fileReader(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } //finally {
            // finally happens no matter what
        //}
        
    }
}