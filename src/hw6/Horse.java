package hw6;

import java.util.Objects;

public class Horse extends Animal{
    private int speed;

    public Horse(String food, String location) {
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
        return "Конь";
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Horse horse = (Horse) o;
            result = speed == horse.speed;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(speed);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
