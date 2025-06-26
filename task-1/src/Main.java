import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int males = random.nextInt(2, 4);
        int females = random.nextInt(2, 4);

        Aquarium aquarium = new Aquarium();
        aquarium.start(males, females);
    }
}

