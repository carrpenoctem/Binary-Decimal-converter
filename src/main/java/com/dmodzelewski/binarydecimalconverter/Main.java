package com.dmodzelewski.binarydecimalconverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * The purpose of this app is to convert binary numbers to decimal and the
 * opposite.
 *
 * @author D.Modzelewski
 */
public class Main {

    /* Logger, for logging errors */
    private static final Logger classLogger = Logger.getLogger(Main.class.getCanonicalName());
    /* A convenient way to receive input from the user */
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;
        while (running) {
            System.out.println(calculateBinary());
        }

    }

    /**
     * A method converting decimal to binary
     * @return binary number as a String
     */
    public static String calculateBinary() {

        System.out.println("Enter a decimal number");
        int x = input.nextInt();

        /* This declaration will be used to determine the first digit */
        boolean positive;
        if (x == 0) {
            return "0";
        } else if (x > 0) {
            positive = true;
        } else {
            x = x * (-1);
            positive = false;
        }

        /* Populating list with our bits */
        List<Integer> binary = new ArrayList<>();
        while (x != 0) {
            binary.add(x % 2);
            x = x / 2;
        }

        /* Setting up the first digit. 0 - positive; 1 - negative */
        if (positive) {
            binary.add(0);
        } else {
            binary.add(1);
        }
        
        /* Reversing the list to provide desired order */
        Collections.reverse(binary);

        /* Creating an actual string from our bits */
        StringBuilder sb = new StringBuilder();
        for (Integer i : binary) {
            sb.append(i.toString());
        }

        return sb.toString();
    }

}
