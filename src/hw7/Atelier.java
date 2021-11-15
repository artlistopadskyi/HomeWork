package hw7;

public class Atelier {
    public static void main(String[] args) {
        Clothes[] clothes = {
                new Tshirt(Size.L, 10.67, "белый"),
                new Skirt(Size.XS, 7.30, "красный"),
                new Pants(Size.S, 9.50, "синий"),
                new Skirt(Size.M, 11.52, "фиолетовый"),
                new Tie(Size.XXS, 4.43, "черний"),
        };

        System.out.println("Одеваем мужчину: ");
        dressMan(clothes);
        System.out.println("Одеваем женчину: ");
        dressWomen(clothes);
    }

    private static void dressMan(Clothes[] clothes) {
        for (Clothes clothe: clothes) {
            if (clothe instanceof ManClothes) {
                ((ManClothes) clothe).dressMan();
            }
        }
    }

    private static void dressWomen(Clothes[] clothes) {
        for (Clothes clothe: clothes) {
            if (clothe instanceof WomenClothes) {
                ((WomenClothes) clothe).dressWomen();
            }
        }
    }
}
