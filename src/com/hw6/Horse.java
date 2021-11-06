package com.hw6;

import java.util.Objects;

public class Horse extends Animal {
    private int speed;

    public Horse(String food, String location) {
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
        return "Конь";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return speed == horse.speed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed);
    }
}
