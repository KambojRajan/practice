package impl;

import java.util.Random;

public class Dice {
    public static int getRun() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
