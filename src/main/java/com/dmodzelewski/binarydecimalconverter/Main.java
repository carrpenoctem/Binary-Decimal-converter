package com.dmodzelewski.binarydecimalconverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
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
            System.out.println("Welcome to the Binary-Decimal converter. Please enter the following:");
            System.out.println(" 1 - Binary to decimal \n"
                    + " 2 - Decimal to binary \n"
                    + " 3 - Exit");
            int x = parseIntFixed();
            switch (x) {
                case 1:
                    System.out.println(CalculateDecimal());
                    break;
                case 2:
                    System.out.println(calculateBinary());
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    continue;
            }
        }
    }

    /**
     * A method converting decimal to binary
     *
     * @return binary number as a String
     */
    public static String calculateBinary() {

        System.out.println("Enter a decimal number");
        int x = parseIntFixed();

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

    /**
     * A method converting binary to decimal
     *
     * @return a decimal int
     */
    private static int CalculateDecimal() {

        /* a list to store our binary digits */
        List<Integer> binaryDigits = new ArrayList<>();
        /* a boolean to check if number is positive */
        boolean positive = true;

        /* boolean based loop for validating input (should be 1 and 0 only) */
        boolean isProperDigit = false;
        while (isProperDigit == false) {

            System.out.println("Enter a binary number. \n"
                    + "Please take a note that the first digit will be used ONLY to distinguish \n"
                    + "wether the number is positive or negative as following: 1 - negative, 0 - positive");
            String x = input.nextLine();

            /* turning our String of digits into a char array */
            char[] digits = x.toCharArray();

            /* validation */
            isProperDigit = true;
            for (int i = 0; i < digits.length; i++) {

                /* checking each digit is correct */
                if (digits[i] == '1' || digits[i] == '0') {

                    /* the first digit determines if number is positive or not */
                    if (i == 0) {
                        if (digits[i] == '1') {
                            positive = false;
                        } else {
                            positive = true;
                        }
                    } else {
                        binaryDigits.add(Character.getNumericValue(digits[i]));
                    }
                } else {
                    System.out.println("Wrong input!");
                    isProperDigit = false;  // so we can try to enter data again
                    binaryDigits.clear();   // so we get rid of unwanted input
                    break;  // so we don't receive multiple "Wrong input" messages
                }
            }
        }

        /* preparing output */
        int decimalOutput = 0;
        Collections.reverse(binaryDigits);
        for (int i = binaryDigits.size() - 1; i > -1; i--) {
            decimalOutput += Math.pow(2, i) * binaryDigits.get(i);
        }

        if (positive) {
            return decimalOutput;
        } else {
            return -decimalOutput;
        }
    }

    /**
     * This method causes a loop which ends when an input can be parsed to int
     *
     * @return parsed int value
     */
    private static int parseIntFixed() {
        boolean running = true;
        int out = 0;
        while (running) {
            String in = input.nextLine();
            try {
                out = Integer.parseInt(in);
                running = false;
            } catch (NumberFormatException e) {
                classLogger.log(Level.WARNING, e.getMessage() + " (should be int)");
            }
        }
        return out;
    }
}
