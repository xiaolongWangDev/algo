package tobeorganized.queue;

import java.util.LinkedList;
import java.util.Queue;

public class Problem_CatDogQueue {
    private static abstract class Pet {

        private final String type;

        private Pet(String type) {
            this.type = type;
        }

        private String getPetType() {
            return this.type;
        }
    }

    private static class Dog extends Pet {

        private Dog() {
            super("dog");
        }
    }

    private static class Cat extends Pet {

        private Cat() {
            super("cat");
        }
    }

    /*
    could also use counter for this problem.
     */
    private static class CatDogQueue {
        private final Queue<Cat> cats = new LinkedList<>();
        private final Queue<Dog> dogs = new LinkedList<>();
        private final Queue<Class<?>> all = new LinkedList<>();

        public void add(Pet pet) {
            if (pet instanceof Cat) {
                cats.add((Cat) pet);
            } else if (pet instanceof Dog) {
                dogs.add((Dog) pet);
            }
            all.add(pet.getClass());
        }

        public Pet poll() {
            if (!all.isEmpty()) {
                if (all.poll() == Cat.class) {
                    return cats.poll();
                } else {
                    return dogs.poll();
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        CatDogQueue structure = new CatDogQueue();
        structure.add(new Cat());
        structure.add(new Cat());
        structure.add(new Dog());
        structure.add(new Cat());
        structure.add(new Dog());
        structure.add(new Dog());
        for(int i = 0; i< 6; i++) {
            System.out.println(structure.poll().getPetType());
        }
    }
}
