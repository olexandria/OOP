package com.dypko;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int command;
        MyString  container = null;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("---MENU---");
            System.out.println("Уведіть '1' для створення контейнера");
            System.out.println("Уведіть '2' для заповнення контейнера");
            System.out.println("Уведіть '3' для очистки контейнера");
            System.out.println("Уведіть '4' для виведення контейнера");
            System.out.println("Уведіть '5' для виведення меню контейнера");
            System.out.println("Уведіть '6' для завершення роботи програми");
            command = scan.nextInt();
            switch(command){
                case 1:
                    System.out.println("Введіть довжину контейнера");
                    container = new MyString(scan.nextInt());
                    System.out.println("Контейнер створено.");
                    break;
                case 2:
                    if(container != null) {
                        String s;
                        scan.nextLine();
                        System.out.println("Введіть рядки. Напишіть 'end',щоб зупинитись.");
                        s = scan.nextLine();
                        while (!s.equals("end")) {
                            if (s.length() <= 0) {
                                System.out.println("Попробуйте ще раз!");
                            } else {
                                container.add(s);
                            }
                            s = scan.nextLine();
                        }
                    }
                    else{
                        System.out.println("Контейнер не створений!");
                    }
                    break;
                case 3:
                    if(container != null){
                        container.clear();
                        System.out.println("Контейнер почищено!");
                    }
                    else{
                        System.out.println("Контейнер не створений!");
                    }
                    break;
                case 4:
                    if(container != null){
                        System.out.println(container.toString());
                    }
                    else{
                        System.out.println("Контейнер не створений!");
                    }
                    break;
                case 5:
                    if(container == null){
                        System.out.println("Контейнер не створений!");
                        break;
                    }
                    System.out.println("Уведіть '1' для додавання елемента");
                    System.out.println("Уведіть '2' для видалення елемента");
                    System.out.println("Уведіть '3' для перетворення в масив та ітерації");
                    System.out.println("Уведіть '4' для відображення поточного розміру");
                    System.out.println("Уведіть '5' для показу максимального розміру");
                    System.out.println("Уведіть '6' для перевірки стрічки");
                    System.out.println("Уведіть '7' для додавання до файлу (serialize)");
                    System.out.println("Уведіть '8' для зчитування з файлу (deserialize)");
                    System.out.println("Уведіть '9' для отримання елемента за індексом");
                    System.out.println("Уведіть '10' для отримання індекса елемента");
                    System.out.println("Уведіть '11' для сортування по алфавіту");
                    System.out.println("Уведіть '12' для перебрання контейнера (foreach)");
                    System.out.println("Уведіть '13' для перебирання контейнера (while)");
                    System.out.println("Уведіть '14' для повернення до головного меню");
                    command = scan.nextInt();
                    switch(command){
                        case 1:
                            scan.nextLine();
                            System.out.println("Введіть рядкок, щоб додати:");
                            container.add(scan.nextLine());
                            break;
                        case 2:
                            scan.nextLine();
                            System.out.println("Введіть рядкок, для видалення:");
                            if(container.remove(scan.nextLine())){
                                System.out.println("Рядок видалено!");
                            }
                            else{
                                System.out.println("Такого рядка не знайдено!");
                            }
                            break;
                        case 3:
                            String arr[] = new String[container.size()];
                            arr = container.toArray();
                            for(String s : arr){
                                System.out.print(s + ",");
                            }
                            break;
                        case 4:
                            System.out.println("Розмір: " + container.size());
                            break;
                        case 5:
                            System.out.println("Розмір: " + container.max_size());
                            break;
                        case 6:
                            scan.nextLine();
                            System.out.println("Введіть рядок, який потрібно перевірити:");
                            if(container.contains(scan.nextLine())){
                                System.out.println("Такий елемент знайдено!");
                            }
                            else{
                                System.out.println("Немає такого елемента!");
                            }
                            break;
                        case 7:
                            scan.nextLine();
                            System.out.println("Введіть назву файлу для запису:");
                            if(container.serialize(scan.nextLine())){
                                System.out.println("Записано!");
                            }
                            else{
                                System.out.println("Не вдалий запис!");
                            }
                            break;
                        case 8:
                            scan.nextLine();
                            System.out.println("Введіть назву файлу, щоб зчитати:");
                            if(container.deserialize(scan.nextLine())){
                                System.out.println("Дані отримано!");
                            }
                            else{
                                System.out.println("Неможливо отримати дані!");
                            }
                            break;
                        case 9:
                            System.out.println("Введіть індекс:");
                            int s = scan.nextInt();
                            String str = container.get(s);
                            if(str != null){
                                System.out.println("Ось рядок: " + str);
                            }
                            else{
                                System.out.println("Не має такого рядка!");
                            }
                            break;
                        case 10:
                            scan.nextLine();
                            System.out.println("Введіть рядок:");
                            String str1 = scan.nextLine();
                            int id = container.get(str1);
                            if(id != -1){
                                System.out.println("Ось індекс: " + id);
                            }
                            else{
                                System.out.println("Немає такого рядка!");
                            }
                            break;
                        case 11:
                            container.sort();
                            System.out.println("Посортовано!");
                            break;
                        case 12:
                            for(String s1 : container.toArray()){
                                System.out.println(s1+ ", ");
                            }
                            break;
                        case 13:
                            Iterator<String> iter = container.iterator();
                            while(iter.hasNext()){
                                System.out.println(iter.next() + ", ");
                            }
                            break;
                        case 14:
                            break;
                    }

                    break;
                case 6:
                    System.exit(0);
                    break;

            }
        }
    }
}