public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Bird bird = new Bird();
        Horse horse = new Horse();
        Animal[] arrAnim = {cat, dog, bird, horse};

        for (int i = 0; i < arrAnim.length; i++) {
            String animalClass;
            if (arrAnim[i] instanceof Cat) {
                animalClass = "Cat ";
            } else if (arrAnim[i] instanceof Dog) {
                animalClass = " Dog ";
            } else if (arrAnim[i] instanceof Bird) {
                animalClass = "Bird ";
            } else if (arrAnim[i] instanceof Horse) {
                animalClass = "Horse ";
            } else {
                animalClass = "Unknown animal ";
            }
            System.out.println(animalClass + arrAnim[i].getInfo());

            if (arrAnim[i].run(100)) {
                System.out.println(animalClass + "ran!");
            } else {
                System.out.println(animalClass + "not ran!");
            }

            if (arrAnim[i].jump(1.5)) {
                System.out.println(animalClass + "jumped!");
            } else {
                System.out.println(animalClass + "not jumped!");
            }

            if (arrAnim[i].swim(50)) {
                System.out.println(animalClass + "swam!");
            } else {
                System.out.println(animalClass + "not swam!");
            }
        }
    }
}
