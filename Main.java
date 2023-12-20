import java.util.LinkedList;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static LinkedList<Double> averageInputs = new LinkedList<>();

    public static void main(String[] args) {
        label:
        while(true) {
            println("choose: get average[1], converting time to time[2]");
            String input = scan.nextLine();
            switch (input) {
                case "1":
                    println("insert all numbers one at a time, enter [f] to finish and [r] to remove the last one entered");
                    getAverage();
                    break;
                case "2":
                    convertTime();
                    break;
                case "break":
                    break label;
                default:
                    println("bro you have to choose 1 or 2");
                    break;
            }
        }
    }

    /**
     * prints a statement without the need for System.out.
     * @param statement is the statement being printed
     */
    public static void println(String statement){
        System.out.println(statement);
    }
    public static void print(String statement){
        System.out.print(statement);
    }
    public static void getAverage(){
        while (true) {
            String userInput = null;
            try {
                userInput = scan.nextLine();
                averageInputs.add(Double.parseDouble(userInput));
            } catch (NumberFormatException exception) {
                assert userInput != null;
                if (userInput.equals("f") || userInput.equals("F")) {
                    print("ok your average is: ");
                    break;
                } else if (userInput.equals("r") || userInput.equals("R")) {
                    println("ok we removed that last one");
                    averageInputs.remove(averageInputs.size() - 1);
                }
                else {
                    println("bro, that ain't a number. try again.");
                }
            }
        }
        double average = 0.0;
        for (Double averageInput : averageInputs) {
            average = average + averageInput;
        }
        average = average / averageInputs.size();
        println(String.valueOf(average));
        while(true){
            try {
                averageInputs.remove(0);
            }
            catch(IndexOutOfBoundsException exception){
                break;
            }
        }
    }
    public static void convertTime(){
        while (true) {
            try {
                println("insert amount of hours");
                int hours = Integer.parseInt(scan.nextLine());
                println("insert amount of minutes");
                int minutes = Integer.parseInt(scan.nextLine());
                println("insert amount of seconds");
                int seconds = Integer.parseInt(scan.nextLine());
                println("cool. what would you like to convert them into");
                println("seconds[1], minutes[2], or hours[3]");
                int choice = Integer.parseInt(scan.nextLine());
                if (choice < 1 || choice > 3) {
                    println("bro choose a number from 1-3 not whatever you put in");
                }
                switch (choice) {
                    case 1 -> {
                        minutes = minutes + (hours * 60);
                        seconds = seconds + (minutes * 60);
                        println("you have " + seconds + " seconds total, bro.");
                    }
                    case 2 -> {
                        if (seconds > 30) {
                            minutes = minutes + 1;
                        }
                        minutes = minutes + (hours * 60);
                        println("you got approx " + minutes + " minutes, bro.");
                    }
                    case 3 -> {
                        if (seconds > 30) {
                            minutes = minutes + 1;
                        }
                        if (minutes > 30 && minutes < 60) {
                            minutes = 60;
                        }
                        hours = hours + (minutes / 60);
                        println("you got approx " + hours + " hours, bro.");
                    }
                }
                break;
            } catch (NumberFormatException exception) {
                println("bro, does this look like a number to you? try again.");
            }
        }
    }
}