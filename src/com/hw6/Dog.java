package com.hw6;

import java.util.Objects;

public class Dog extends Animal {
    private int force;

    public Dog(String food, String location) {
        super(food, location);
    }

    @Override
    public void makeNoise() {
        System.out.println("Животное " + this.toString() +  " издает звук");
        super.makeNoise();
    }

    @Override
    public void eat() {
        System.out.println("Животное " + this.toString() +  " ест");
        super.eat();
    }

    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public String toString() {
        return "Собака";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return force == dog.force;
    }

    @Override
    public int hashCode() {
        return Objects.hash(force);
    }

}
