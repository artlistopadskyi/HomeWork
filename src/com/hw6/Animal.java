package com.hw6;

public class Animal {
    public String food;
    public String location;

    public Animal(String food, String location) {
        this.food = food;
        this.location = location;
    }

    public void makeNoise() {
        System.out.println("Такое-то животное издает звук");
    }

    public void eat() {
        System.out.println("Такое-то животное ест");
    }

    public void sleep() {
        System.out.println("Такое-то животное спит");
    }
}
