package com.dypko;

import java.io.Serializable;

public class Person implements Serializable {
    private static int counter = 0;
    private int index;
    private String name;
    private String address;
    private String number;
    private String dateOfBirth;
    private String dateOfEditing;

    public Person() {
        index = counter++;
    }

    public static void cleanPerson() {
        counter = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String Number() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String DateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBitrh(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String DateOfEditing() {
        return dateOfEditing;
    }

    public void setDateOfEditing(String dateOfEditing) {
        this.dateOfEditing = dateOfEditing;
    }

    public int getIndex() {
        return index;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("-----------------------------------------\n");
        str.append("П.І.Б: ").append(name).append("\n");
        str.append("Індекс: ").append(index).append("\n");
        str.append("Адреса: ").append(address).append("\n");
        str.append("Номер телефону: ").append(number).append("\n");
        str.append("Дата народження: ").append(dateOfBirth).append("\n");
        str.append("Дата редагування:").append(dateOfEditing).append("\n");
        str.append("-----------------------------------------");
        return str.toString();
    }
}