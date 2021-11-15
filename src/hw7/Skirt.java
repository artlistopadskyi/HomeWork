package hw7;

public class Skirt extends Clothes implements WomenClothes{
    public Skirt(Size size, Double cost, String color) {
        this.size = size;
        this.cost = cost;
        this.color = color;
    }

    @Override
    public void dressWomen() {
        String result = "Одеть на женщину юбку (Размер: "
                + this.size.getEuroSize() + " - " + this.size.getDescription()
                + "; цвет: " + this.color
                + "; цена: " + this.cost
                + ")";
        System.out.println(result);
    }
}
