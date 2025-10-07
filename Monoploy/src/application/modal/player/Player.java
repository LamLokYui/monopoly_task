package application.modal.player;

import application.modal.board.squares.OwnableSquare;
import application.modal.board.squares.RailroadSquare;
import application.modal.board.squares.UtilitySquare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Player {
    private String name;
    private int position;
    private int capital;
    private final List<OwnableSquare> ownedSquares;
    private boolean isJail;
    private boolean isBankrupt;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.capital = 1500;
        this.ownedSquares = new ArrayList<>();
        this.isJail = false;
        this.isBankrupt = false;
    }

    public void move(int steps) {
        if (isBankrupt) {
            throw new IllegalStateException("Bankrupt player cannot move");
        }
        int newPosition = (position + steps) % 40;
        if (newPosition < position) {
            addMoney(200); // Pass Go
        }
        position = newPosition;
    }

    public void setPosition(int pos) {
        if (isBankrupt) {
            throw new IllegalStateException("Bankrupt player cannot set position");
        }
        position = pos % 40;
    }

    public int addMoney(int amount) {
        if (isBankrupt) {
            throw new IllegalStateException("Cannot modify money for a bankrupt player");
        }
        int newCapital = capital + amount;
        if (newCapital < 0) {
            int moneyNeeded = -newCapital;
            autoSellProperties(moneyNeeded);
            newCapital = capital;
        }
        if (newCapital < 0) {
            capital = 0;
            isBankrupt = true;
        } else {
            capital = newCapital;
        }
        return capital;
    }

    public boolean sellOwnedSquare(OwnableSquare square) {
        if (!ownedSquares.contains(square)) {
            return false;
        }
        addMoney(square.getPurchasePrice());
        square.setOwner(null);
        return ownedSquares.remove(square);
    }

    public void autoSellProperties(int capitalRequired) {
        int capitalReceived = 0;
        ownedSquares.sort(Comparator.comparingInt(OwnableSquare::getPurchasePrice));
        while (!ownedSquares.isEmpty() && capitalReceived < capitalRequired) {
            OwnableSquare square = ownedSquares.get(0);
            sellOwnedSquare(square);
            capitalReceived += square.getPurchasePrice();
        }
        if (capitalReceived < capitalRequired) {
            isBankrupt = true;
        }
    }

    public void addProperty(OwnableSquare square) {
        if (isBankrupt) {
            throw new IllegalStateException("Bankrupt player cannot acquire properties");
        }
        ownedSquares.add(square);
        square.setOwner(this);
    }

    public int getNumOfOwnedRails() {
        return (int) ownedSquares.stream()
                .filter(sq -> sq instanceof RailroadSquare)
                .count();
    }

    public int getNumOfOwnedUtilities() {
        return (int) ownedSquares.stream()
                .filter(sq -> sq instanceof UtilitySquare)
                .count();
    }

    public void setJail(boolean jail) {
        isJail = jail;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getCapital() {
        return capital;
    }

    public boolean isJail() {
        return isJail;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public List<OwnableSquare> getOwnedSquares() {
        return new ArrayList<>(ownedSquares);
    }

    @Override
    public String toString() {
        return name;
    }
}