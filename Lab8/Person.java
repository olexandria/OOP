package com.dypko;

import java.io.Serializable;

public class Person implements Serializable {
    private static int counter = 0;
    private int index;
    private String name;
    private String address;
    private Integer number;
    private Integer dateOfBirth;
    private Integer dateOfEditing;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBitrh(Integer dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getDateOfEditing() {
        return dateOfEditing;
    }

    public void setDateOfEditing(Integer dateOfEditing) {
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