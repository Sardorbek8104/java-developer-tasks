import java.util.Random;

public class Fish implements Runnable {
    private static final Random RANDOM = new Random();
    private static final int MAX_X = 4;
    private static final int MAX_Y = 4;

    private final int lifespan;
    private int age = 0;
    private int x;
    private int y;
    private final Gender gender;
    private final Aquarium aquarium;
    private boolean alive = true;

    public enum Gender { MALE, FEMALE }

    public Fish(Gender gender, Aquarium aquarium) {
        this.lifespan = RANDOM.nextInt(5, 11);
        this.x = RANDOM.nextInt(0, MAX_X + 1);
        this.y = RANDOM.nextInt(0, MAX_Y + 1);
        this.gender = gender;
        this.aquarium = aquarium;
    }

    @Override
    public void run() {
        while (alive && age < lifespan && !aquarium.isOverpopulated()) {
            try {
                Thread.sleep(1000);
                age++;
                move();
                checkBreeding();
                System.out.println("\uD83D\uDC20 " + this + " moved.");
            } catch (InterruptedException e) {
                break;
            }
        }
        alive = false;
        aquarium.removeFish(this);
        System.out.println("\uD83D\uDC80 " + this + " died.");
    }

    private void move() {
        x = Math.max(0, Math.min(MAX_X, x + RANDOM.nextInt(3) - 1));
        y = Math.max(0, Math.min(MAX_Y, y + RANDOM.nextInt(3) - 1));
    }

    private void checkBreeding() {
        for (Fish other : aquarium.getFishes()) {
            if (other != this && other.alive && this.gender != other.gender && this.x == other.x && this.y == other.y) {
                Fish.Gender babyGender = RANDOM.nextBoolean() ? Gender.MALE : Gender.FEMALE;
                aquarium.addFish(new Fish(babyGender, aquarium));
                System.out.println("\uD83D\uDC76 " + this + " bred with " + other);
                break;
            }
        }
    }

    public boolean isAlive() { return alive; }
    public Gender getGender() { return gender; }
    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public String toString() {
        return "Fish{" + gender + ", age=" + age + "/" + lifespan + ", pos=(" + x + "," + y + ")}";
    }
}