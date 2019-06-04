package com.dypko;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        boolean debug = false;
        for (String i : args) {
            if (i.equals("-d") || i.equals("-debug")) {
                debug = true;
            }
            if (i.equals("-h") || i.equals("-help")) {
                System.out.println("Дипко Олександра\n" +
                        "Завдання: Ввести декілька рядків. Розбити на дві групи: рядки, довжина яких\n" +
                        "менша за середню; рядки, довжина яких більше середньої. Вивести\n" +
                        "рядки та їх довжину по групах.");
            }
        }
        Scanner scan = new Scanner(System.in);
        double aCounter = 0, lengthOfStrings = 0, averageStringLength = 0;
        String workingString, menuOption;
        ArrayList<String> userInput = new ArrayList<>();
        ArrayList<String> lowerThanAverage = new ArrayList<>();
        ArrayList<String> higherThanAverage = new ArrayList<>();
        Menu();

        do {
            menuOption = scan.next();
            if (debug) {
                System.out.println("You've chosen option: " + menuOption);
            }
            switch (menuOption) {
                case "a":
                    workingString = scan.next();
                    userInput.add(workingString);
                    lengthOfStrings += userInput.get((int) aCounter).length();
                    aCounter++;
                    if (debug) {
                        System.out.println(aCounter);
                        System.out.println("Довжина всіх втрічок: " + lengthOfStrings);
                    }
                    break;
                case "b":
                    System.out.println( "Стрічки: " + userInput);
                    System.out.println("Довжина всіх стрічок: "+ lengthOfStrings);
                    System.out.println("Середня довжина всіх стрічок: " + averageStringLength);
                    break;
                case "c":
                    averageStringLength =  Math.round(lengthOfStrings / aCounter);
                    if (debug) {
                        System.out.println("Середня довжина стрічка: " + averageStringLength);
                    }
                    for (int i = 0; i < aCounter; i++) {
                        if (userInput.get(i).length() < averageStringLength) {
                            lowerThanAverage.add(userInput.get(i));
                        } else {
                            higherThanAverage.add(userInput.get(i));
                        }
                    }
                    break;
                case "d":
                    for (int i = 0; i < lowerThanAverage.size(); i++) {
                        System.out.println("Стрічка (" + lowerThanAverage.get(i)
                                + ") має довжину: "+ lowerThanAverage.get(i).length());
                    }
                    System.out.println("-----------------------------------------------------");
                    for (int i = 0; i < higherThanAverage.size(); i++) {
                        System.out.println("Стрічка (" + higherThanAverage.get(i)
                                + ") має довжину: "+ higherThanAverage.get(i).length());
                    }
                    break;
                case "e":
                    exit(0);
                    break;
                case "Menu":
                    Menu();
                    break;
                default:
                    System.out.println("Помилка!");
            }
        } while(true);
    }
    private static void Menu() {
        System.out.println( "Main Menu");
        System.out.println("Уведіть 'a' для введення даних");
        System.out.println("Уведіть 'b' для перегляду даних");
        System.out.println("Уведіть 'c' для виконання обчислень");
        System.out.println("Уведіть 'd' для відображення результату");
        System.out.println("Уведіть 'e' для завершення роботи програми");
        System.out.println("Уведіть '-h' або '-help' для відображення інформації про автора програми," +
                " призначення, детальний опис режимів роботи");
        System.out.println("Уведіть '-d' або '-debug' для відображення додаткових даних");
        System.out.println("Якщо ви хочете повернутися до меню, уведіть 'Menu' " );
    }
}

