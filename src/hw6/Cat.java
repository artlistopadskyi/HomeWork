package hw6;

import java.util.Objects;

public class Cat extends Animal {
    public int dexterity;

    public Cat(String food, String location) {
        super(food, location);
    }

    @Override
    public void makeNoise() {
        super.makeNoise();
    }

    @Override
    public void eat() {
        super.eat();
    }

    @Override
    public void sleep() {
        super.sleep();
    }

    @Override
    public boolean equals() {
        return super.equals();
    }

    @Override
    public String toString() {
        return "Кот";
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Cat cat = (Cat) o;
            result = dexterity == cat.dexterity;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dexterity);
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
}
