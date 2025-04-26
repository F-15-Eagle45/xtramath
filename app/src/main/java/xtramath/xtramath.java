package main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class xtramath {
    public static void main(String[] args) {
        // Operation
        String operation;
        InputTaker opThread;
        // Buffer for below section
        String[] bufferArray = new String[2];
        // variables defined by user
        int min;
        int max;
        int terms;
        int time;
        // variables from InputTakerThread
        int questions;
        int correct;
        List<Integer> wrong = new ArrayList<Integer>();
        // IO objects
        Scanner scanner = new Scanner(System.in);
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateTime = timeNow.format(formatter);
        // boolean for do while loop
        boolean cont;

        // Takes input and prompts user
        System.out.println(
                "Xtramath is a program that lets you practice your math skills and logs all the results in a file.");

        do {
            System.out.println(
                    "\nChoose an operation: (Addition, Subtraction, Multiplication, Tables, Division, Division Tables, Squaring, Square Root, Cubing, Cube Root)");
            System.out.print("Operation: ");
            operation = scanner.nextLine();
            System.out.println("Enter the range (min - max)");
            System.out.print("Range: ");
            bufferArray = scanner.nextLine().split("-");
            min = Integer.parseInt((bufferArray[0].trim()));
            max = Integer.parseInt((bufferArray[1].trim()));
            if (min > max) {
                scanner.close();
                throw new IllegalArgumentException(
                        "Invalid Range: " + min + " - " + max + "; Min must be less than max");
            }
            // Number of terms not allowed for tables, cb, sqr, cbrt, or sqrt
            switch (operation) {
                case "tables":
                case "Tables":
                    terms = 2;
                    break;
                case "sqr":
                case "squaring":
                case "Sqr":
                case "Square":
                case "Squaring":
                case "sqrt":
                case "Sqrt":
                case "square root":
                case "Square root":
                case "Square Root":
                case "cubing":
                case "cb":
                case "Cb":
                case "cubes":
                case "Cubes":
                case "Cube":
                case "cube":
                case "Cubing":
                case "cbrt":
                case "Cbrt":
                case "cube root":
                case "Cube root":
                case "Cube Root":
                    terms = 1;
                    break;
                default:
                    System.out.print("Number of terms: ");
                    terms = Integer.parseInt(scanner.nextLine());
                    if (terms < 1) {
                        scanner.close();
                        throw new IllegalArgumentException("Invalid number of terms: " + terms);
                    }
                    break;
            }

            System.out.print("Time (in seconds): ");
            time = Integer.parseInt(scanner.nextLine());

            // Countdown to start of test
            System.out.println("Starting in 3 seconds...");
            for (int i = 3; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i + "... ");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("GO!");

            // Creates the thread based on the operation
            switch (operation) {
                case "addition":
                case "Adding":
                case "add":
                case "adding":
                case "Add":
                case "Addition":
                    opThread = new InputTakers.AddInputTaker(min, max, terms);
                    break;
                case "subtraction":
                case "Subtracting":
                case "subtract":
                case "subtracting":
                case "Subtract":
                case "Subtraction":
                    opThread = new InputTakers.SubInputTaker(min, max, terms);
                    break;
                case "multiplication":
                case "multiply":
                case "Multiply":
                case "Multiplication":
                    opThread = new InputTakers.MultInputTaker(min, max, terms);
                    break;
                case "tables":
                case "Tables":
                    opThread = new InputTakers.TabmInputTaker(min, max, terms);
                    break;
                case "division":
                case "divide":
                case "Divide":
                case "Division":
                    opThread = new InputTakers.DivInputTaker(min, max, terms);
                    break;
                case "division tables":
                case "divide tables":
                case "Divide tables":
                case "Division tables":
                case "division Tables":
                case "divide Tables":
                case "Divide Tables":
                case "Division Tables":
                    opThread = new InputTakers.TabdInputTaker(min, max, terms);
                    break;
                case "sqr":
                case "squaring":
                case "Sqr":
                case "Square":
                case "Squaring":
                    opThread = new InputTakers.sqrInputTaker(min, max, terms);
                    break;
                case "sqrt":
                case "Sqrt":
                case "square root":
                case "Square root":
                case "Square Root":
                    opThread = new InputTakers.SqrtInputTaker(min, max, terms);
                    break;
                case "cubing":
                case "cb":
                case "Cb":
                case "cubes":
                case "Cubes":
                case "Cube":
                case "cube":
                case "Cubing":
                    opThread = new InputTakers.CubeInputTaker(min, max, terms);
                    break;
                case "cbrt":
                case "Cbrt":
                case "cube root":
                case "Cube root":
                case "Cube Root":
                    opThread = new InputTakers.CbrtInputTaker(min, max, terms);
                    break;
                default:
                    scanner.close();
                    throw new UnsupportedOperationException("Unsupported operation: " + operation);
            }
            // Timer
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                scanner.close();
                return;
            }
            // copies the values and stops the thread
            questions = opThread.questions;
            correct = opThread.correct;
            wrong = opThread.wrong;
            opThread.running = false;

            // Prints the results
            System.out.println("\nTIME'S UP!");
            System.out.println("Score: " + correct + "/" + questions + "\n");
            if (wrong.size() > 0) {
                System.out.println("Questions you got wrong:");
                for (int i = 0; i < wrong.size(); i++) {
                    System.out.println("Question #: " + (wrong.get(i) + 1));
                }
            }

            // Writes score to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"))) {
                writer.write(dateTime + "\n");
                writer.write("Operation: " + operation + "\n");
                writer.write("Range: " + min + " - " + max + "\n");
                writer.write("Number of terms: " + terms + "\n");
                writer.write("Time: " + time + " seconds\n");
                writer.write("Score: " + correct + "/" + questions + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Waits for the operation thread to finish
            try {
                opThread.t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Asks if the user wants to try again
            System.out.print("Try again? (y/n): ");
            cont = scanner.nextLine().equals("y");
        } while (cont);
        // Closes the scanner and finishes the program.
        scanner.close();
    }
}
