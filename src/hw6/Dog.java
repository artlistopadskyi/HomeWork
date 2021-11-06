package hw6;

import java.util.Objects;

public class Dog extends Animal {
    private int force;

    public Dog(String food, String location) {
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
        return "Собака";
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Dog dog = (Dog) o;
            result = force == dog.force;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(force);
    }

    public void setForce(int force) {
        this.force = force;
    }
}
