package com.dypko;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static Person persFind(int index, List<Person> pers){
        Iterator<Person> myItr = pers.iterator();
        while(myItr.hasNext()){
            Person t = myItr.next();
            if(t.getIndex() == index){
                return t;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        boolean autoMode = false;
        for(int i = 0;i < args.length;i++){
            if(args[i].equals("-auto")){
                autoMode = true;
            }
        }
        List<Person> personList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        if(autoMode){
            try {
                scan = new Scanner(new BufferedInputStream(new FileInputStream("C:\\Users\\911\\IdeaProjects\\Lab7\\automode.txt")));
            }catch(FileNotFoundException ex){
                System.err.println("Автоматичний режим не запущено!");
                scan = new Scanner(System.in);
            }
        }
        int command,index;
        while(true){
            if(autoMode){
                try {
                    TimeUnit.SECONDS.sleep(1);
                }catch(InterruptedException e){

                }
            }
            System.out.println("---------------|||---------------");
            System.out.println("1 - додати людину");
            System.out.println("2 - видалити людину");
            System.out.println("3 - переглянути список людей");
            System.out.println("4 - інформація про людину");
            System.out.println("5 - записати список людей в XML-файл");
            System.out.println("6 - витягти список людей з XML-файлу");
            System.out.println("7 - записати список людей в файл(TXT)");
            System.out.println("8 - витягти список людей з TXT-файлу");
            System.out.println("9 - вийти");
            System.out.println("---------------|||---------------");
            command = scan.nextInt();
            switch(command){
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
                    Iterator<Person> myItr = personList.iterator();
                    boolean isFound = false;
                    while(myItr.hasNext()){
                        Person t = myItr.next();
                        if(t.getIndex() == index){
                            myItr.remove();
                            isFound = true;
                            break;
                        }
                    }
                    if(!isFound){
                        System.err.println("Такої людини не знайдено!");
                    }
                    else{
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
                case 5: {
                    FileOutputStream fos;
                    System.out.println("----Введіть назву файлу:");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----Виберіть потрібну директорію, щоб зберегти файл:");
                    String path = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(path)).exists()) {
                        File newFile = new File(path);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("***Файл '" + file_name + "' було створено!");
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
                }
                break;
                case 6: {
                    System.out.println("1 - Створити новий список(не зберігається попередній)");
                    System.out.println("2 - Додати до поточного списку");
                    command = scan.nextInt();
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
                            personList = new ArrayList<>();
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
                }
                break;
                case 7: {
                    FileOutputStream fos;
                    System.out.println("----Введіть назву файлу(TXT):");
                    scan.nextLine();
                    String file_name = scan.nextLine();
                    System.out.println("----Виберіть потрібну директорію, щоб зберегти файл:");
                    String file = FileManager.selectDir(scan) + "\\" + file_name;
                    if (!(new File(file)).exists()) {
                        File newFile = new File(file);
                        try {
                            if (newFile.createNewFile())
                                System.out.println("New file '" + file_name + "' has been created");
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                            break;
                        }
                    }
                    try {
                        fos = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        break;
                    }
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(personList.size());
                        for (Person one : personList) {
                            oos.writeObject(one);
                        }
                        oos.flush();
                        oos.close();
                        fos.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        break;
                    }
                }
                break;
                case 8:
                {
                    System.out.println("----Виберіть файл:");
                    scan.nextLine();
                    String path = FileManager.selectFile(scan);
                    FileInputStream fis;
                    try {
                        fis = new FileInputStream(path);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found!");
                        break;
                    }
                    try {
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        Integer count = (Integer) ois.readObject();
                        for (int i = 0; i < count; i++) {
                            personList.add((Person) ois.readObject());
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage() + "dsds");
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println("Not Found!");
                        break;
                    }
                }
                break;
                case 9:
                    System.exit(0);
                    break;

            }
        }
    }

}