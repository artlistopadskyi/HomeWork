package hw7;

public class Tshirt extends Clothes implements ManClothes, WomenClothes{
    public Tshirt(Size size, Double cost, String color) {
        this.size = size;
        this.cost = cost;
        this.color = color;
    }

    @Override
    public void dressMan() {
        String result = "Одеть на мужчину футболку (Размер: "
                + this.size.getEuroSize() + " - " + this.size.getDescription()
                + "; цвет: " + this.color
                + "; цена: " + this.cost
                + ")";
        System.out.println(result);
    }

    @Override
    public void dressWomen() {
        String result = "Одеть на женщину футболку (Размер: "
                + this.size.getEuroSize() + " - " + this.size.getDescription()
                + "; цвет: " + this.color
                + "; цена: " + this.cost
                + ")";
        System.out.println(result);
    }
}
