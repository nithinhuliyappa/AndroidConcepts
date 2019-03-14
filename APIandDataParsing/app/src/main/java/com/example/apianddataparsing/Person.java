package com.example.apianddataparsing;

public class Person {

    String name;
    int age;
    Long id;
    Address address;

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", address=" + address +
                '}';
    }


}
