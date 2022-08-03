package practice.zuosbook.chapter1;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * Hint:
 * Create a holder class that tracks the order of insertion.
 * When pollAll(), check the head of both queue, find the one with a smaller tracker
 */
public class CatDogQueue {
    private static abstract class Pet {
        private final String name;
        private final String type;

        protected Pet(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

    private static class Cat extends Pet {

        protected Cat(String name) {
            super(name, "cat");
        }
    }

    private static class Dog extends Pet {

        protected Dog(String name) {
            super(name, "dog");
        }
    }

    private static class TrackedPet {
        private final Pet pet;
        private final int id;

        private TrackedPet(Pet pet, int id) {
            this.pet = pet;
            this.id = id;
        }

        public Pet getPet() {
            return pet;
        }

        public int getId() {
            return id;
        }
    }

    private final Queue<TrackedPet> cats = new LinkedList<>();
    private final Queue<TrackedPet> dogs = new LinkedList<>();
    private int idBase;

    public void add(Pet pet) {
        if (pet instanceof Dog) {
            dogs.add(new TrackedPet(pet, idBase++));
        } else if (pet instanceof Cat) {
            cats.add(new TrackedPet(pet, idBase++));
        }
    }

    public Pet pollCat() {
        return Optional.ofNullable(cats.poll()).map(TrackedPet::getPet).orElse(null);
    }

    public Pet pollDog() {
        return Optional.ofNullable(dogs.poll()).map(TrackedPet::getPet).orElse(null);
    }

    public Pet pollAll() {
        if (cats.isEmpty() && dogs.isEmpty()) {
            return null;
        } else if (cats.isEmpty()) {
            return dogs.poll().getPet();
        } else if (dogs.isEmpty()) {
            return cats.poll().getPet();
        } else {
            TrackedPet tCat = cats.peek();
            TrackedPet tDog = dogs.peek();
            return tCat.id <= tDog.id ? cats.poll().getPet() : dogs.poll().getPet();
        }
    }

    public static void main(String[] args) {
        CatDogQueue queue = new CatDogQueue();
        queue.add(new Cat("a"));
        queue.add(new Cat("b"));
        queue.add(new Dog("c"));
        queue.add(new Cat("d"));
        queue.add(new Dog("e"));
        queue.add(new Dog("f"));
        queue.add(new Cat("g"));
        assert "a".equals(queue.pollAll().getName());
        assert "b".equals(queue.pollAll().getName());
        assert "c".equals(queue.pollAll().getName());
        assert "d".equals(queue.pollAll().getName());
        assert "e".equals(queue.pollAll().getName());
        assert "f".equals(queue.pollAll().getName());
        assert "g".equals(queue.pollAll().getName());

        queue.add(new Cat("a"));
        queue.add(new Cat("b"));
        queue.add(new Dog("c"));
        queue.add(new Cat("d"));
        queue.add(new Dog("e"));
        queue.add(new Dog("f"));
        queue.add(new Cat("g"));
        assert "a".equals(queue.pollCat().getName());
        assert "b".equals(queue.pollCat().getName());
        assert "d".equals(queue.pollCat().getName());
        assert "g".equals(queue.pollCat().getName());
        assert null == queue.pollCat();
        queue.pollDog();
        queue.pollDog();
        queue.pollDog();

        queue.add(new Cat("a"));
        queue.add(new Cat("b"));
        queue.add(new Dog("c"));
        queue.add(new Cat("d"));
        queue.add(new Dog("e"));
        queue.add(new Dog("f"));
        queue.add(new Cat("g"));
        assert "c".equals(queue.pollDog().getName());
        assert "e".equals(queue.pollDog().getName());
        assert "f".equals(queue.pollDog().getName());
        assert null == queue.pollDog();
    }
}
