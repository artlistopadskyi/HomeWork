package hw7;

public class Tie extends Clothes implements ManClothes {
    public Tie(Size size, Double cost, String color) {
        this.size = size;
        this.cost = cost;
        this.color = color;
    }

    @Override
    public void dressMan() {
        String result = "Одеть на мужчину галстук (Размер: "
                + this.size.getEuroSize() + " - " + this.size.getDescription()
                + "; цвет: " + this.color
                + "; цена: " + this.cost
                + ")";
        System.out.println(result);
    }
}
