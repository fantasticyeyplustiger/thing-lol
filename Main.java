import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static Random rand = new Random();
    public static LinkedList<Double> averageInputs = new LinkedList<>();

    public static void main(String[] args) {
        label:
        // main loop
        while(true) {
            println("choose: get average[1], converting time to time[2], get random number[3], pythagorean theorem[4]," +
                    "law of cosine[5]");
            String input = scan.nextLine();
            switch (input) {
                case "1", "get average", "average", "Get average":
                    println("insert all numbers one at a time, enter [f] to finish and [r] to remove the last one entered");
                    getAverage();
                    break;
                case "2", "convert", "converting time to time", "Converting time to time":
                    convertTime();
                    break;
                case "3", "get random number", "Get random number":
                    getRandomNumber();
                    break;
                case "4", "pythagorean theorem", "Pythagorean Theorem", "pythagorean":
                    pythagoreanTheorem();
                    break;
                case "5", "law of cosine":
                    lawOfCosine();
                    break;
                case "break", "quit":
                    break label;
                default:
                    println("bro you have to choose one of them");
                    break;
            }
        }
    }

    /** println()
     * prints a given string on another line
     * @param statement is the string being printed
     */
    public static void println(String statement){
        System.out.println(statement);
    }

    /** print()
     * prints a given string
     * @param statement is the string being printed
     */
    public static void print(String statement){
        System.out.print(statement);
    }

    /** getAverage()
     *  gets a list of numbers from user and then finds the average of it
     */
    public static void getAverage(){
        //region gets inputs
        while (true) {
            String userInput = null;
            try {
                userInput = scan.nextLine();
                averageInputs.add(Double.parseDouble(userInput));
            } catch (NumberFormatException exception) {
                assert userInput != null; //make sure they can't simply enter a blank
                if (userInput.equals("f") || userInput.equals("F")) { //finishes the loop
                    print("ok your average is: ");
                    break;
                } else if (userInput.equals("r") || userInput.equals("R")) { //removes last number inputted
                    println("ok we removed that last one");
                    averageInputs.remove(averageInputs.size() - 1);
                }
                else {
                    println("that ain't a number. try again.");
                }
            }
        }
        //endregion

        //region gets average
        double average = 0.0;
        for (Double averageInput : averageInputs) {
            //adds up everything in averageInputs to average
            average = average + averageInput;
        }
        //gets the final average
        average = average / averageInputs.size();
        println(String.valueOf(average));
        //this makes sure averageInputs won't keep its list when used again
        while(true){
            try {
                averageInputs.remove(0);
            }
            catch(IndexOutOfBoundsException exception){
                break;
            }
        }
        //endregion
    }

    /** convertTime()
     * converts a select amount of hours, minutes, and seconds of time into another unit of time chosen
     */
    public static void convertTime(){
        //region asks and gets time input (integers only)
        int hours = getInteger("insert amount of hours");
        int minutes = getInteger("insert amount of minutes");
        int seconds = getInteger("insert amount of seconds");
        //endregion

        println("cool. what would you like to convert them into");
        int choice = getInteger("seconds[1], minutes[2], or hours[3]");
        //makes sure player chooses a viable option
        if (choice < 1 || choice > 3) {
            println("choose a numerical digit from 1-3 not whatever you put in");
        }
        switch (choice) {
            case 1 -> { //convert total time to seconds
                minutes = minutes + (hours * 60);
                seconds = seconds + (minutes * 60);
                println("you have " + seconds + " seconds total.");
            }
            case 2 -> { //attempt to convert total time to minutes
                if (seconds > 30) {
                    minutes = minutes + 1;
                }
                minutes = minutes + (hours * 60);
                println("you got approx " + minutes + " minutes.");
            }
            case 3 -> { //attempt to convert total time to hours
                if (seconds > 30) {
                    minutes = minutes + 1;
                }
                if (minutes > 30 && minutes < 60) {
                    minutes = 60;
                }
                hours = hours + (minutes / 60);
                println("you got approx " + hours + " hours.");
            }
        }

    }

    /**
     * gets two numbers from user and gets a random number between them
     */
    public static void getRandomNumber(){
        int firstBound = getInteger("input an integer");
        int secondBound = getInteger("now input another");

        //this will make firstBound less than secondBound
        if(firstBound > secondBound){
            int tempFBound = firstBound;
            firstBound = secondBound;
            secondBound = tempFBound;
        }
        int randomNumber = rand.nextInt(firstBound, secondBound);
        System.out.println(randomNumber);
    }

    /**
     * gets a valid integer from the user
     * @param prompt the prompt that asks the user what integer to put in
     */
    public static int getInteger(String prompt){
        println(prompt);
        while(true) {
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException exception) {
                println("that's not a valid integer. no decimals and no letters.");
            }
        }
    }

    /**
     * gets a valid double from the user
     * @param prompt the prompt that asks the user what integer to put in
     */
    public static double getDouble(String prompt){
        println(prompt);
        while(true) {
            try {
                return Double.parseDouble(scan.nextLine());
            } catch (NumberFormatException exception) {
                println("that's not a valid double. no symbols and no letters.");
            }
        }
    }

    /**
     * finds a side length of a right triangle using the Pythagorean Theorem (a^2 + b^2 = c^2)
     */
    public static void pythagoreanTheorem(){
        int choice = 0;
        while(true) {
            choice = getInteger("are you trying to find the hypotenuse[1] or a legs side length[2] or quit[3]");
            if(choice != 1 && choice != 2){
                println("1 or 2 bro");
                continue;
            }
            break;
        }
        if(choice == 1){
            double sideLength = getDouble("please put in known side length");
            double hypotenuse = getDouble("please put in known hypotenuse length");

            double sideLSquared = sideLength * sideLength;
            double hypoSquared = hypotenuse * hypotenuse;

            double otherSideLSquared = hypoSquared - sideLSquared;
            double otherSideLength = Math.sqrt(otherSideLSquared);
            System.out.println("your unknown side length is " + otherSideLength);
        }
        else{
            double sideLength = getDouble("please put in known side length");
            double otherSideLength = getDouble("please put in other known side length");

            double sideLSquared = sideLength * sideLength;
            double otherSideLSquared = otherSideLength * otherSideLength;

            double hypoSquared = sideLSquared + otherSideLSquared;
            double hypotenuse = Math.sqrt(hypoSquared);

            System.out.println("your hypotenuse length is " + hypotenuse);
        }
    }

    /**
     * uses law of cosine to find an angle or a side of a triangle
     */
    public static void lawOfCosine() {
        int choice = getInteger("are you trying to find an angle[1] or a side length[2] or quit[3]");

        //makes sure choice isn't out of bounds
        while (choice != 1 && choice != 2) {
            if (choice == 3) {
                println("ok");
                return;
            }
            println("1, 2, or 3 bro");
            choice = getInteger("are you trying to find an angle[1] or a side length[2] or quit[3]");
        }

        if (choice == 1) {
            println("note: the angle being found is the angle opposite of side length C");
            double sideLengthA = getDouble("enter side length of a");
            double sideLengthB = getDouble("enter side length of b");
            double sideLengthC = getDouble("enter side length of c");

            //the formula used in here finds the radians of an angle in a triangle using 3 side lengths
            double formulaRadians = (sideLengthA * sideLengthA) + (sideLengthB * sideLengthB) - (sideLengthC * sideLengthC);
            formulaRadians = formulaRadians / (2 * sideLengthA * sideLengthB);
            //acos is the inverse of cosine but will only give radians
            formulaRadians = Math.acos(formulaRadians);

            //this converts the radians of formulaRadians into degrees
            double formulaDegrees = formulaRadians * (180 / Math.PI);

            double roundedFormulaRadians = getRoundedNumber(formulaRadians);
            double roundedFormulaDegrees = getRoundedNumber(formulaDegrees);

            System.out.println("your angle is " + roundedFormulaDegrees + " degrees and " + roundedFormulaRadians + " radians");
        }
        else {
            println("note: the side length being found is side C");
            double sideLengthA = getDouble("enter side length of a");
            double sideLengthB = getDouble("enter side length of b");
            double angleC = getDouble("enter angle C");

            //this makes sure the angle of C is less than 180 and greater than 0; otherwise, Math.cos will not work
            while (angleC >= 180 || angleC <= 0) {
                println("the angle has to be less than 180 or greater than 0 n00b");
                angleC = getDouble("enter angle C");
            }

            //the formula used in here finds a side length of a triangle using 2 other known side lengths and one angle
            double formulaSideLength = (sideLengthA * sideLengthA) + (sideLengthB * sideLengthB);
            formulaSideLength = formulaSideLength - (2 * sideLengthA * sideLengthB) * Math.cos(angleC);
            formulaSideLength = Math.sqrt(formulaSideLength);

            double roundedSideLength = getRoundedNumber(formulaSideLength);


            System.out.println("your unknown side length is " + roundedSideLength + " long");
        }
    }

    /**
     * this function gets a valid integer that will round another number to the Xth decimal place
     */
    public static double getRoundedNumber(double numberBeingRounded){
        while(true) {
            int choice = getInteger("do you want to round your number[1] or no[2]");
            if (choice == 1) {
                println("note: it wont go on forever if you ask to round to 10000000th decimal place");
                int decimalPlace = getInteger("anyways. to how many decimal places?");

                //this keeps on adding 0 to roundingNumber until it reaches amount in decimalPlace
                String roundingNumber = "1" + "0".repeat(Math.max(0, decimalPlace));
                int finalRoundingNumber = Integer.parseInt(roundingNumber);

                //this rounds the number to the X amount of decimal places
                numberBeingRounded = (double) Math.round(numberBeingRounded * finalRoundingNumber) / finalRoundingNumber;

                println("ok");
                return numberBeingRounded;
            }
            else if (choice == 2) { //this will send it without being rounded
                return Math.round(numberBeingRounded);
            }
            else {
                println("choose 1 or 2 you fool");
            }
        }
    }
}