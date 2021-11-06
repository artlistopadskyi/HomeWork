package hw6;

public class Veterinarian {
    public void treatAnimal(Animal animal) {
        String name = animal.toString();
        String output = "Животное " + name + " ест: " + animal.food + "\nЖивотное " + name + " живет: " + animal.location;
        System.out.println(output);
    }
}
