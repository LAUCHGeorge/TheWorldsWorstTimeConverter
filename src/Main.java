/*
    Author: LAUCHGeorge

    This is a time converter to convert seconds into other units.
    I am doing this however the fuck I wanna do this and (most likely) absolute not do it properly.

    I am actually quite proud of this.
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    // Constant array for the amount that seconds have to be divided with to get the desired Unit.
    // Constant array for unit names.
    static final int[] UNIT_SECONDS = {31536000,86400,3600,60,1};
    static final String[] UNIT_NAMES = {"Year","Day","Hour","Minute","Second"};

    // Getting the maximum index for UNIT_SECONDS.
    static int unitMaxIndex = UNIT_SECONDS.length-1;

    public static void main(String[] args) {

        // Checking if UNIT_SECONDS and UNIT_NAMES are the same length.
        if (UNIT_SECONDS.length != UNIT_NAMES.length && args.length == 0) {
            System.out.println("[ERROR] Configuration error. The program will quit.");
            System.exit(420);}

        // Formatted title.
        System.out.printf("%s\n%s\n%s\n",
                "***********************************",
                "* The worlds worst Time-Converter *",
                "***********************************");

        // Checking for arguments, and if arguments aren't empty, skipping the getInput phase.
        if (args.length != 0) {
            System.out.println();

            if (args.length < 2 ) { // In case first argument only is being used.
                try {outputResult(calculate(Integer.parseInt(args[0])),Integer.parseInt(args[0]));
                } catch(NumberFormatException e) {inputError();}
            }

            if (args.length > 1) {  // In case second argument is being used.
                try {System.out.printf("%d%s%d%s%d\n",Integer.parseInt(args[0])," seconds, divided by ",Integer.parseInt(args[1])," are:\n",Integer.parseInt(args[0])/Integer.parseInt(args[1]));
                } catch(NumberFormatException e) {inputError();}
            }
            
            System.exit(69);
        }

        // Main loop for program.
        while(true) {int input = getInput("Please enter seconds to convert"); outputResult(calculate(input),input);}
    }

    static int getInput(String prompt) {    // Getting the users input in a safe way.
        Scanner scanner = new Scanner(System.in);

        while(true) {               // Catching invalid user input.
            try {
                System.out.printf("\n%s: ",prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                inputError();
                scanner.next();}    // Skipping next "scan", otherwise the program would get stuck in a loop.
        }
    }

    static void inputError() {
        System.out.println("\nPlease consider:\nA - Entering a valid number.\nB - Entering a valid number.\nC - Entering a valid number.\n");   // My best error message so far :3
    }

    static int[] calculate(int input) {     // Creating calculated array, using input and UNIT_SECONDS
        int[] unit_calculated = new int[UNIT_SECONDS.length];

        for(int i = 0; i <= unitMaxIndex; i++) {
            unit_calculated[i] = input / UNIT_SECONDS[i];

            try {input = input % UNIT_SECONDS[i];}
            catch (ArithmeticException e) {unit_calculated[i] = 0;}
        }

        return unit_calculated;
    }

    static void outputResult(int[] unit_calculated, int input) {    // Fancy output using printf in a for loop.
        System.out.printf("%d%s\n",input," seconds are:\n");
        for(int i = 0; i <= unitMaxIndex; i++) {
            if (unit_calculated[i] != 0) {

                // Setting boolean, to check if index is last index.
                boolean last = false;

                if      (i == unitMaxIndex)             {last = true;}
                else if (unit_calculated[i+1] == 0)     {last = true;}

                // Adding extra grammar features, for the learning effect of it. (even though it looks kinda bad actually)
                String extras = "";
                if      (unit_calculated[i] != 1)       {extras = extras+"s";}
                if      (!last && i != unitMaxIndex-1)                         {extras = extras+",";}
                if      (i == unitMaxIndex-1 && !last)  {extras = extras+" and";}

                // Printing in a formatted way.
                System.out.printf(
                        "%-10d %s\n",
                        unit_calculated[i],UNIT_NAMES[i]+extras);
            }
        }
    }
}