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
            System.out.println();
            println("choose: get average[1], converting time to time[2], get random number[3], pythagorean theorem[4]," +
                    " law of cosine[5]");
            println("quadratic formula[6], find volume[7], find highest prime[8], find factors[9], quit[q]");
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
                case "5", "law of cosine", "loc":
                    lawOfCosine();
                    break;
                case "6", "quadratic formula":
                    quadraticFormula();
                    break;
                case "7", "find volume":
                    findVolume();
                    break;
                case "8", "find highest prime":
                    findHighestPrime();
                    break;
                case "9", "find factors":
                    findFactors();
                    break;
                case "10", "q" ,"break", "quit":
                    break label;
                default:
                    println("bro you have to choose one of them");
                    break;
            }
        }
    }

    //region functions that get a certain data type from user
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
                println("that's not a valid integer. no decimals and no letters, or ur number is too big.");
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
                println("that's not a valid double. no symbols and no letters, or ur number is too big.");
            }
        }
    }

    /**
     * gets a valid long from the user
     * @param prompt the prompt that asks the user what long to put in
     */
    public static long getLong(String prompt){
        println(prompt);
        while(true) {
            try {
                return Long.parseLong(scan.nextLine());
            } catch (NumberFormatException exception) {
                println("that's not a valid long integer. no decimals and no letters, or ur number is too big.");
            }
        }
    }

    /**
     * gets a boolean from the user
     * @param prompt the prompt that asks the user a yes or no question
     */
    public static boolean getBoolean(String prompt){
        println(prompt);
        String input = scan.nextLine();
        return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
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
                return numberBeingRounded;
            }
            else {
                println("choose 1 or 2 you fool");
            }
        }
    }
    //endregion

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
                    averageInputs.removeLast();
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
                averageInputs.removeFirst();
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
     * finds a side length of a right triangle using the Pythagorean Theorem (a^2 + b^2 = c^2)
     */
    public static void pythagoreanTheorem(){
        int choice;
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
     * uses quadratic formula to find the solutions of a quadratic equation, then prints said solutions
     */
    public static void quadraticFormula(){
        System.out.println("formula: (-b ± sqrt(b^2 - 4ac)) / 2a");
        System.out.println("in the form of ax^2 + bx + c, enter a, b, and c accordingly");

        double a = getDouble("a: ");
        double b = getDouble("b: ");
        double c = getDouble("c: ");
        double discriminant = (b * b) - (4 * a * c);
        boolean imaginaryNumber = false;

        //depending on discriminant, solution is either 2 complex, 1 real, or 2 real
        //if d < 0; imaginary
        //if d = 0; 1 real
        //if d > 0; 2 real

        //for complex solutions
        if(discriminant < 0){
            discriminant = Math.abs(discriminant); //switches negative to positive
            imaginaryNumber = true;
            //imaginary number is needed because you cannot square root a negative number
        }

        //simplifies the square root in the discriminant if possible
        String rationalizedDiscriminant = rationalizeSquareRoot(discriminant, imaginaryNumber);

        //for cases with more than 1 solution
        if (discriminant != 0){
            System.out.print("[x = (" + (-b) + " ± " + rationalizedDiscriminant + ") / " + (2 * a));
            System.out.print("] or [");
            //this one is in the event user wants the calculated square root even if it's a decimal
            System.out.println("x = (" + (-b) + " ± " + roundANumber(Math.sqrt(discriminant), 2) + ") / " + (2 * a) + "]");
        }
        //this is for single solution answers
        else {
            System.out.println("x = " + (-b / (2 * a)));
            System.out.println("1 real solution");
            return; //so it doesn't print 2 real or complex solutions afterward
        }

        //not included in discriminant != 0 to prevent unneeded nested if statements)
        if (imaginaryNumber){
            System.out.println("2 complex solutions");
        }
        else{
            System.out.println("2 real solutions");
        }
    }

    /**
     * this function rounds a number to the Xth decimal place
     * works like getRoundedNumber, but doesn't use user inputs
     * @param toDecimalPlace the Xth decimal place
     * @return returns the rounded number
     */
    public static double roundANumber(double numberBeingRounded, int toDecimalPlace){
        int rounder = toDecimalPlace * 10;
        numberBeingRounded = (double) Math.round(numberBeingRounded * rounder) / rounder;
        return numberBeingRounded;
    }

    /**
     * checks if the number entered is a whole number
     * @return returns true if it is a whole number
     */
    public static boolean checkForWhole(double numberBeingChecked){
        numberBeingChecked = (numberBeingChecked * 10) % 10;
        return numberBeingChecked == 0;
    }

    /**
     * rationalizes the square root inputted if possible (won't work with decimals)
     * @param squareRoot the square root being rationalized
     * @return returns the rationalized(?) square root
     */
    public static String rationalizeSquareRoot(double squareRoot, boolean hasImaginary){

        int sqRootMultiplier = 1;
        int sqRootFactor = 1;
        int root = (int) Math.sqrt(squareRoot); //for if squareRoot has a whole root

        // "i" starts at 2 because 0 and 1 can factor into any number, and so that "i" can increase
        for(int i = 2; i * i <= squareRoot; i++){

            double factorable = squareRoot % (i * i); //this checks if i^2 can divide into the sqRoot evenly
            double factorable2 = squareRoot / (i * i); //becomes square root factor (sqrt(12) = sqrt(3) * sqrt(4))
            double hasWholeRoot = squareRoot / (i); //checks if squareRoot is a perfect square

            if(factorable == 0) {
                sqRootMultiplier = i; //doesn't break immediately in case there is a bigger sqRootMultiplier
                sqRootFactor = (int) factorable2;
            }
            if(hasWholeRoot == i){ //if perfect square, just return the whole square root
                return String.valueOf(root);
            }

        }

        String returnRoot = ""; //so no error when adding

        if(hasImaginary){
            returnRoot = "i"; //answer is multiplied by i
        }

        String primeRoot = returnRoot + "sqrt(" + squareRoot + ")";
        returnRoot = "(" + returnRoot + sqRootMultiplier + " * sqrt(" + sqRootFactor + "))";

        if(hasImaginary){
            returnRoot += ")"; //a parenthese to close of the i(
        }

        if (sqRootMultiplier == 1){
            return primeRoot;
        }

        else{
            return returnRoot;
        }

    }

    /**
     * for a theory of application (2-20-2024)
     * takes a 2d array and an int, attempts to find that int within the 2d array
     * @return returns true if that specific int is in the 2d array
     */
    public static boolean theoryAppMoment(int[][] numberBoard, int locateInt){
        for (int[] arrayOfNumbers : numberBoard) {
            for (int i : arrayOfNumbers) {
                if (locateInt == i) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * asks user what they want to find the volume of and calculates it
     * options: cube, sq pyramid, sphere, cylinder, cone, triangular prism
     */
    public static void findVolume(){

        //declared variables so switch statement doesn't mess up
        double length; double width; double height; double volume; double roundedVolume;
        double radius; double volumeWOutPI; double roundedVolumeWOutPI; double area;

        int choice = getInteger("cube[1], square pyramid[2], sphere[3], cylinder[4], cone[5], triangular prism[6]");

        switch (choice){
            case 1: //cube: lwh
                length = getDouble("length:");
                width = getDouble("width:");
                height = getDouble("height:");

                volume = length * width * height;
                roundedVolume = getRoundedNumber(volume);

                System.out.println("volume of cube: " + roundedVolume);
                break;

            case 2: //square pyramid: (lwh) / 3
                length = getDouble("length:");
                width = getDouble("width:");
                height = getDouble("height:");

                volume = (length * width * height) / 3;
                roundedVolume = getRoundedNumber(volume);

                System.out.println("volume of square pyramid: " + roundedVolume);
                break;

            case 3: //sphere: (4/3)PI * r^3
                radius = getDouble("radius:");

                volume = ((4.0/3.0) * (radius * radius * radius));
                volumeWOutPI = ((4.0/3.0 * Math.PI)) * (radius * radius * radius);

                roundedVolume = getRoundedNumber(volume);
                roundedVolumeWOutPI = getRoundedNumber(volumeWOutPI);

                System.out.println("volume of sphere with PI:    " + roundedVolume);
                System.out.println("volume of sphere without PI: " + roundedVolumeWOutPI);
                break;

            case 4: //cylinder: (PI * r^2) * h
                radius = getDouble("radius:");
                height = getDouble("height:");

                volume = (radius * radius) * height;
                volumeWOutPI = (Math.PI * (radius * radius)) * height;

                roundedVolume = getRoundedNumber(volume);
                roundedVolumeWOutPI = getRoundedNumber(volumeWOutPI);

                System.out.println("volume of cylinder with PI:    " + roundedVolume);
                System.out.println("volume of cylinder without PI: " + roundedVolumeWOutPI);
                break;

            case 5: //cone: ((PI * r^2) * h)/3
                radius = getDouble("radius:");
                height = getDouble("height:");

                volume = ((radius * radius) * height) / 3;
                volumeWOutPI = ((Math.PI * (radius * radius)) * height) / 3;

                roundedVolume = getRoundedNumber(volume);
                roundedVolumeWOutPI = getRoundedNumber(volumeWOutPI);

                System.out.println("volume of cone with PI:    " + roundedVolume);
                System.out.println("volume of cone without PI: " + roundedVolumeWOutPI);
                break;

            case 6: //triangular prism: (bh/2) * h
                area = getDouble("base (area of triangle, (1/2(b*h)):");
                height = getDouble("height:");

                volume = area * height;
                roundedVolume = getRoundedNumber(volume);

                System.out.println("volume of triangular prism: " + roundedVolume);
        }
    }

    /**
     * gets a number from user and finds the highest prime number up until that number
     */
    public static void findHighestPrime(){
        int limit = getInteger("highest prime number up to what? integers only");
        int primeTester;

        //region specific cases where limit is negative, 0, or 1
        //these specific cases are needed because they result in cases where code cannot find highest prime
        if(limit < 0){
            System.out.println("no. that's now a positive cause u can't find a prime negative number. too bad too sad skrub");
            limit = Math.abs(limit);
        }
        if(limit == 0){ //(not an else if so limit == 1 can detect negative 1 in case limit < 0
            System.out.println("idoit");
            return;
        }
        else if(limit == 1){
            System.out.println("not composite or prime lol");
            return;
        }
        else if(limit > 100000000){
            System.out.println("my brethren in the lord almighty why did you put such a large number in");
            System.out.println("this might take a few seconds");
        }
        //endregion

        for(int potentialPrime = limit; potentialPrime > 1; potentialPrime--){
            //the reason why it starts at limit instead of 0 is in the event the limit is extremely high (i.e, 2 billion)
            //that way it can find the highest prime number far more easily instead of going through a billion loops

            for(int i = 2; i < limit; i++){

                //checks if potentialPrime can divide into itself evenly
                primeTester = potentialPrime % i;

                //if it has any other factors that can divide into itself evenly besides itself, break loop
                if(primeTester == 0 && potentialPrime != i){
                    break;
                }
                if(primeTester == 0){
                    System.out.println("highest prime number up to " + limit + " is: " + potentialPrime);
                    return; //used instead of "break" to break second loop as well, otherwise it will keep on printing prime numbers
                }
            }
        }
    }

    /**
     * finds the factors of a user inputted number
     */
    public static void findFactors(){

        LinkedList<Double> tempFactors = new LinkedList<>();
        LinkedList<Integer> wholeFactors = new LinkedList<>();

        println("note: this will not give u any negative factors. also, decimals will convert to whole numbers");
        double factorsOfWhat = getDouble("give me number you want to find factors for");
        System.out.println("ok");

        if(factorsOfWhat == 0 || factorsOfWhat == 1){
            int toWhole = (int) factorsOfWhat;
            System.out.println("factors are " + toWhole + ". how do you not know this");
            return;
        }
        else if(factorsOfWhat > 1000000000){
            println("...weirdo");
        }

        //makes factorsOfWhat a whole number if it is a decimal
        if(!checkForWhole(factorsOfWhat)){
            int temp = (int) factorsOfWhat;
            factorsOfWhat = (double) temp;
        }

        double potentialFactorOne;
        double potentialFactorTwo;

        //region find factors
        for(potentialFactorOne = 1; potentialFactorOne < (factorsOfWhat / 2); potentialFactorOne++) {

            //basically, if factorsOfWhat / x = y, then y * x must = factorsOfWhat
            //checking if y is a whole number makes sure if it's an actual factor (factors aren't decimals)

            potentialFactorTwo = factorsOfWhat / potentialFactorOne;
            boolean dividedEvenly = checkForWhole(potentialFactorTwo);

            //this prevents repeating pairs
            if(potentialFactorOne > potentialFactorTwo){
                break;
            }

            if(dividedEvenly){
                tempFactors.add(potentialFactorOne);
                tempFactors.add(potentialFactorTwo);
            }
        }
        //endregion

        //region BAD FOR LOOP
        /*
        BADDD FOR LOOP
        for (potentialFactorTwo = factorsOfWhat; potentialFactorTwo > 0; potentialFactorTwo--) {

                potentialProduct = potentialFactorOne * potentialFactorTwo;

                if(potentialFactorOne >= potentialFactorTwo){
                    potentialFactorOne = factorsOfWhat;
                }

                if(potentialProduct == factorsOfWhat){
                    factors.add(potentialFactorOne);
                    factors.add(potentialFactorTwo);
                    break;
                }
            }
         */
        //endregion

        // this initializes wholeFactors by turning the doubles in tempFactors to integers
        for (double tempDouble : tempFactors) {
            int toWhole = (int) tempDouble;
            wholeFactors.add(toWhole);
        }

        System.out.println("Factor Pairs:");

        //region print factors
        for(int i = 0; i + 1 < wholeFactors.size(); i += 2) {
            System.out.print("[" + wholeFactors.get(i) + ", " + wholeFactors.get(i + 1) + "] ");

            //every 10 factor pairs, go to a next line
            if(i % 10 == 0 && i > 0){
                System.out.println();
            }
        }
        //endregion
    }
}