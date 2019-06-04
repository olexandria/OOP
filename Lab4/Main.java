package com.company;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Person persFind(int index, ListContainer<Person> pers) {
        Iterator<Person> myItr = pers.iterator();
        while (myItr.hasNext()) {
            Person t = myItr.next();
            if (t.getIndex() == index) {
                return t;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        boolean autoMode = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-auto")) {
                autoMode = true;
            }
        }
        ListContainer<Person> personList = new ListContainer<>();
        Scanner scan = new Scanner(System.in);
        if (autoMode) {
            try {
                scan = new Scanner(new BufferedInputStream(new FileInputStream("C:\\Users\\911\\IdeaProjects\\automode.txt")));
            } catch (FileNotFoundException ex) {
                System.err.println("Автоматичний режим не запущено!");
                scan = new Scanner(System.in);
            }
        }
        int index;
        while (true) {
            if (autoMode) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
            }
            System.out.println("1 - додати людину");
            System.out.println("2 - видалити людину");
            System.out.println("3 - переглянути список людей");
            System.out.println("4 - інформація про людину");
            System.out.println("5 - записати список людей у файл");
            System.out.println("6 - витягти список людей з файлу");
            System.out.println("7 - знайти людину за індексом");
            System.out.println("8 - вийти");
            byte command = scan.nextByte();
            switch (command) {
                case 1:
                    System.out.println("Введіть П.І.Б людини:");
                    scan.nextLine();
                    Person pers = new Person();
                    pers.setName(scan.nextLine());
                    personList.add(pers);
                    System.out.println("Людину '" + pers.getName() + "' додано з індексом: " + pers.getIndex());
                    break;
                case 2:
                    System.out.println("Введіть індекс людини, щоб видалити:");
                    index = scan.nextInt();
                    if (!personList.remove(index)) {
                        System.err.println("Такої людини не знайдено!");
                    } else {
                        System.out.println("Людину видалено із списку!");
                    }
                    break;
                case 3:
                    Iterator<Person> myItr1 = personList.iterator();
                    while (myItr1.hasNext()) {
                        Person t = myItr1.next();
                        System.out.println(t.toString());
                    }
                    break;
                case 4:
                    System.out.println("Введіть індекс людини, щоб додати інформацію про неї: ");
                    index = scan.nextInt();
                    Person temp = persFind(index, personList);
                    if (temp != null) {
                        System.out.println("Людину знайдено!");
                        boolean exit = false;
                        while (!exit) {
                            System.out.println(temp.toString());
                            System.out.println("1 - Змінити адресу");
                            System.out.println("2 - Змінити номер телефону");
                            System.out.println("3 - Змінити дату народження");
                            System.out.println("4 - Змінити дату редагування інформації");
                            System.out.println("5 - Повернутись до гол. меню");
                            command = scan.nextByte();
                            switch (command) {
                                case 1:
                                    System.out.println("Введіть нову адресу:");
                                    scan.nextLine();
                                    temp.setAddress(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 2:
                                    System.out.println("Введіть новий номер телефону:");
                                    scan.nextLine();
                                    temp.setNumber(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 3:
                                    System.out.println("Введіть нову дату народження:");
                                    scan.nextLine();
                                    temp.setDateOfBitrh(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 4:
                                    System.out.println("Введіть нову дату редагування:");
                                    scan.nextLine();
                                    temp.setDateOfEditing(scan.nextLine());
                                    System.out.println("Успішно змінено!");
                                    break;
                                case 5:
                                    exit = true;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Людину не знайдено!");
                    }
                    break;
                case 5:
                    FileOutputStream fos;
                    System.out.println("----Введіть назву файлу:");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----Виберіть потрібну папку, щоб зберегти файл:");
                    String path = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(path)).exists()) {
                        File newFile = new File(path);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("Файл '" + file_name + "' створено!");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(path);
                    } catch (FileNotFoundException ex) {
                        System.err.println("Файл не знайдено!");
                        break;
                    }
                    XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
                    encoder.writeObject(personList.size());
                    for (Person one : personList) {
                        encoder.writeObject(one);
                    }
                    encoder.close();
                    System.out.println("Успішно записано!");
                    break;
                case 6:
                    System.out.println("1 - Створити новий список(не зберігається попередній)");
                    System.out.println("2 - Додати до поточного списку");
                    command = scan.nextByte();
                    switch (command) {
                        case 1:
                            System.out.println("----Виберіть файл:");
                            scan.nextLine();
                            String path_ = FileManager.selectFile(scan);
                            FileInputStream fis;
                            try {
                                fis = new FileInputStream(path_);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
                            Integer size = (Integer) decoder.readObject();
                            personList = new ListContainer<>();
                            Person.cleanPerson();
                            for (int i = 0; i < size; i++) {
                                personList.add((Person) decoder.readObject());
                            }
                            decoder.close();

                            break;
                        case 2:
                            System.out.println("----Виберіть файл:");
                            scan.nextLine();
                            String path__ = FileManager.selectFile(scan);
                            FileInputStream fis_;
                            try {
                                fis_ = new FileInputStream(path__);
                            } catch (FileNotFoundException ex) {
                                System.err.println("FileNotFound");
                                break;
                            }
                            XMLDecoder decoder_ = new XMLDecoder(new BufferedInputStream(fis_));
                            Integer size_ = (Integer) decoder_.readObject();
                            for (int i = 0; i < size_; i++) {
                                personList.add((Person) decoder_.readObject());
                            }
                            decoder_.close();
                            break;
                    }
                    break;
                case 7:
                    System.out.println("Введіть індекс людини:");
                    index = scan.nextInt();
                    Person tem = persFind(index, personList);
                    if (tem != null){
                        System.out.println("Людина з індексом  " + index + " - це\n" + tem); }
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        }
    }
}

