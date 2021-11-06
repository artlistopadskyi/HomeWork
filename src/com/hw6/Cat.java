package com.hw6;

import java.util.Objects;

public class Cat extends Animal {
    private int dexterity;

    public Cat(String food, String location) {
        super(food, location);
    }

    @Override
    public void makeNoise() {
        System.out.println("Животное " + this +  " издает звук");
        super.makeNoise();
    }

    @Override
    public void eat() {
        System.out.println("Животное " + this +  " ест");
        super.eat();
    }

    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public String toString() {
        return "Кот";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return dexterity == cat.dexterity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dexterity);
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
}
