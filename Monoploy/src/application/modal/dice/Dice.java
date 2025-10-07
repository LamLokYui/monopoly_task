package application.modal.dice;

import java.util.Random;

/**
 * Utility class for simulating two six-sided dice rolls in Monopoly.
 * Supports rolling dice, retrieving individual die values, total roll, and checking for doubles.
 * Provides a seeded constructor for deterministic testing.
 */
public class Dice {
    private final Random random;
    private int die1;
    private int die2;

    public Dice() {
        this.random = new Random();
    }
    
    public Dice(long seed) {
        this.random = new Random(seed);
    }


    public int roll() {
        die1 = random.nextInt(6) + 1;
        die2 = random.nextInt(6) + 1;
        return getTotal();
    }


    public int getDie1() {
        if (die1 == 0) {
            throw new IllegalStateException("No roll has been made");
        }
        return die1;
    }

    public int getDie2() {
        if (die2 == 0) {
            throw new IllegalStateException("No roll has been made");
        }
        return die2;
    }


    public int getTotal() {
        if (die1 == 0 || die2 == 0) {
            throw new IllegalStateException("No roll has been made");
        }
        return die1 + die2;
    }

    /**
     * Optional: 
     * Checks if the last roll resulted in doubles (same value on both dice).
     * @return True if the last roll was doubles, false otherwise.
     * @throws IllegalStateException if called before rolling.
     */
//    public boolean isDoubles() {
//        if (die1 == 0 || die2 == 0) {
//            throw new IllegalStateException("No roll has been made");
//        }
//        return die1 == die2;
//    }
}