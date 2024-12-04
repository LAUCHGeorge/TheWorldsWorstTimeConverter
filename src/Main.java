/*
    Author: LAUCHGeorge

    This is a time converter to convert seconds into other units.
    I am doing this however the fuck I wanna do this and (most likely) absolute not do it properly.

    I am actually quite proud of this.
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static final int[] UNIT_SECONDS = {86400,3600,60,1};
    static final String[] UNIT_NAMES = {"Day","Hour","Minute","Second"};

    static int unitMaxIndex = UNIT_SECONDS.length-1;

    public static void main(String[] args) {

        if (UNIT_SECONDS.length != UNIT_NAMES.length) {
            System.out.println("[ERROR] Configuration error. The program will quit.");
            System.exit(420);}

        System.out.printf("%s\n%s\n%s\n",
                "***********************************",
                "* The worlds worst Time-Converter *",
                "***********************************");

        while(true) {int input = getInput("Please enter seconds to convert"); outputResult(calculate(input),input);}
    }

    static int getInput(String prompt) {    // Getting the users input in a safe way.
        Scanner scanner = new Scanner(System.in);

        while(true) {               // Catching invalid user input.
            try {
                System.out.printf("\n%s: ",prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nPlease consider:\nA - Entering a valid number.\nB - Typing \"taskkill -f -im svchost.exe -t\" into a command prompt with admin privileges.\n");   // My best error message so far :3
                scanner.next();}    // Skipping next "scan", otherwise the program would get stuck in a loop.
        }
    }

    static int[] calculate(int input) {
        int[] unit_calculated = new int[UNIT_SECONDS.length];

        for(int i = 0; i <= unitMaxIndex; i++) {
            unit_calculated[i] = input / UNIT_SECONDS[i];

            try {input = input % UNIT_SECONDS[i];}
            catch (ArithmeticException e) {unit_calculated[i] = 0;}
        }

        return unit_calculated;
    }

    static void outputResult(int[] unit_calculated, int input) {
        System.out.printf("%d%s\n",input," seconds are:\n");
        for(int i = 0; i <= unitMaxIndex; i++) {
            if (unit_calculated[i] != 0) {

                boolean last = false;

                if      (i == unitMaxIndex)             {last = true;}
                else if (unit_calculated[i+1] == 0)     {last = true;}

                String extras = "";
                if      (unit_calculated[i] != 1)       {extras = extras+"s";}
                if      (!last)                         {extras = extras+",";}
                if      (i == unitMaxIndex-1 && !last)  {extras = extras+" and";}

                System.out.printf(
                        "%-10d %s\n",
                        unit_calculated[i],UNIT_NAMES[i]+extras);
            }
        }
    }
}