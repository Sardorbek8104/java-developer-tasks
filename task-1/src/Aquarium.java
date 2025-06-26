import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Aquarium {
    private static final Random RANDOM = new Random();
    private static final int MAX_CAPACITY = 20;
    private final List<Fish> fishes = new CopyOnWriteArrayList<>();

    public void start(int maleCount, int femaleCount) {
        for (int i = 0; i < maleCount; i++) addFish(new Fish(Fish.Gender.MALE, this));
        for (int i = 0; i < femaleCount; i++) addFish(new Fish(Fish.Gender.FEMALE, this));

        System.out.println("\uD83C\uDF0A Simulation started with " + fishes.size() + " fishes.");
    }

    public void addFish(Fish fish) {
        if (fishes.size() >= MAX_CAPACITY) return;
        fishes.add(fish);
        new Thread(fish).start();
        System.out.println("➕ New " + fish.getGender() + " fish added: " + fish);
    }

    public void removeFish(Fish fish) {
        fishes.remove(fish);
        checkTermination();
    }

    public void checkTermination() {
        if (fishes.isEmpty()) {
            System.out.println("❌ All fishes died. Simulation ended.");
            System.exit(0);
        } else if (fishes.size() >= MAX_CAPACITY) {
            System.out.println("⚠️ Aquarium overpopulated with " + fishes.size() + " fishes. Simulation ended.");
            System.exit(0);
        }
    }

    public List<Fish> getFishes() {
        return fishes;
    }

    public boolean isOverpopulated() {
        return fishes.size() >= MAX_CAPACITY;
    }
}