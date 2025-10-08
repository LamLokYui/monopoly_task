package cardeffecttest;

import application.modal.board.Board;
import application.modal.board.squares.OwnableSquare;
import application.modal.board.squares.RailroadSquare;
import application.modal.board.squares.Squares;
import application.modal.board.squares.UtilitySquare;
import application.modal.dice.Dice;
import application.modal.player.Player;
import application.modal.cardeffect.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardEffectTest {
    private Board board;
    @BeforeEach
    void setUp() {
    	board = Board.getInstance();
    }

    // MoveToPositionEffect Tests
    @Test
    void testMoveToPositionEffectValid() {
    	Player player1 = new Player("TestPlayer1");
        MoveToPositionEffect effect = new MoveToPositionEffect(5);
        effect.execute(player1);
        assertEquals(5, player1.getPosition(), "Player should move to position 5");
        assertEquals(5, player1.getPosition(), "getPosition should return 5");
    }

    @Test
    void testMoveToPositionEffectBoundaryZero() {
    	Player player1 = new Player("TestPlayer1");
        MoveToPositionEffect effect = new MoveToPositionEffect(0);
        effect.execute(player1);
        assertEquals(0, player1.getPosition(), "Player should move to position 0");
        assertEquals(0, effect.getPosition());
    }

    @Test
    void testMoveToPositionEffectBoundaryMax() {
    	Player player1 = new Player("TestPlayer1");
        MoveToPositionEffect effect = new MoveToPositionEffect(39);
        effect.execute(player1);
        assertEquals(39, player1.getPosition(), "Player should move to position 39");
    }



    // AddAmountEffect Tests
    @Test
    void testAddAmountEffectPositive() {
    	Player player1 = new Player("TestPlayer1");
        AddAmountEffect effect = new AddAmountEffect(100);
        effect.execute(player1);
        assertEquals(1600, player1.getCapital(), "Capital should increase by 100");
        assertEquals(100, effect.getAmount(), "getAmount should return 100");
    }

    @Test
    void testAddAmountEffectZero() {
    	Player player1 = new Player("TestPlayer1");
        AddAmountEffect effect = new AddAmountEffect(0);
        effect.execute(player1);
        assertEquals(1500, player1.getCapital(), "Capital should be unchanged");
    }

    // PayAmountEffect Tests
    @Test
    void testPayAmountEffectSufficientFunds() {
    	Player player1 = new Player("TestPlayer1");
        PayAmountEffect effect = new PayAmountEffect(100);
        effect.execute(player1);
        assertEquals(1400, player1.getCapital(), "Capital should decrease by 100");
        assertEquals(100, effect.getAmount(), "getAmount should return 100");
    }

    @Test
    void testPayAmountEffectInsufficientFunds() {
    	Player player1 = new Player("TestPlayer1");
        PayAmountEffect effect = new PayAmountEffect(2000);
        effect.execute(player1);
        assertEquals(-500, player1.getCapital(), "Capital should be -500");
        assertEquals(true, player1.isBankrupt(), "Player should be bankrupt");
    }

    @Test
    void testPayAmountEffectZero() {
    	Player player1 = new Player("TestPlayer1");
        PayAmountEffect effect = new PayAmountEffect(0);
        effect.execute(player1);
        assertEquals(1500, player1.getCapital(), "Capital should be unchanged");
    }


    // MoveToNearestUtilityEffect Tests
    @Test
    void testMoveToNearestUtilityUnowned() {
    	Player player1 = new Player("TestPlayer1");
    	player1.setPosition(5);
        MoveToNearestUtilityEffect effect = new MoveToNearestUtilityEffect();
        effect.execute(player1);
        assertEquals(12, player1.getPosition(), "Should move to Electric Company (12)");
        assertEquals(1500, player1.getCapital(), "No rent paid for unowned utility");
    }
    
    
    @Test
    void testMoveToNearestUtilityOwned() {
    	Player player1 = new Player("TestPlayer1");
    	Player player2 = new Player("TestPlayer2");
    	player1.setPosition(5);
        OwnableSquare utility = (OwnableSquare) board.getSquare(12);
        utility.setOwner(player2);
        player2.addProperty(utility);
        MoveToNearestUtilityEffect effect = new MoveToNearestUtilityEffect();
        effect.execute(player1);
        assertEquals(12, player1.getPosition(), "Should move to Electric Company (12)");
        player2.removeAllPropty();
        utility.setOwner(null);
        assertEquals(1430, player1.getCapital(), "Should pay 10 * 7 = 70 rent");
        assertEquals(1570, player2.getCapital(), "Owner should receive 70 rent");
    }

    @Test
    void testMoveToNearestUtilityPassGo() {
    	Player player1 = new Player("TestPlayer1");
    	player1.setPosition(29);
        MoveToNearestUtilityEffect effect = new MoveToNearestUtilityEffect();
        System.out.println(player1.getNumOfOwnedUtilities());
        effect.execute(player1);
        assertEquals(12, player1.getPosition(), "Should move to Electric Company (12)");
        assertEquals(1700, player1.getCapital(), "Should collect $200 for passing Go");
    }


    // MoveToNearestRailroadEffect Tests
    @Test
    void testMoveToNearestRailroadUnowned() {
    	Player player1 = new Player("TestPlayer1");
    	player1.setPosition(3);
        MoveToNearestRailroadEffect effect = new MoveToNearestRailroadEffect();
        effect.execute(player1);
        assertEquals(5, player1.getPosition(), "Should move to Reading Railroad (5)");
        assertEquals(1500, player1.getCapital(), "No rent paid for unowned railroad");
    }

    @Test
    void testMoveToNearestRailroadOwned() {
    	Player player1 = new Player("TestPlayer1");
    	Player player2 = new Player("TestPlayer2");
    	player1.setPosition(3);
        OwnableSquare railroad = (OwnableSquare) board.getSquare(5);
        railroad.setOwner(player2);
        player2.addProperty(railroad);
        MoveToNearestRailroadEffect effect = new MoveToNearestRailroadEffect();
        effect.execute(player1);
        assertEquals(5, player1.getPosition(), "Should move to Reading Railroad (5)");
        assertEquals(1450, player1.getCapital(), "Should pay double rent (25 * 2 = 50)");
        assertEquals(1550, player2.getCapital(), "Owner should receive 50 rent");
        player2.removeAllPropty();
        railroad.setOwner(null);
    }

    @Test
    void testMoveToNearestRailroadPassGo() {
    	Player player1 = new Player("TestPlayer1");
    	player1.setPosition(36);
        MoveToNearestRailroadEffect effect = new MoveToNearestRailroadEffect();
        effect.execute(player1);
        assertEquals(5, player1.getPosition(), "Should move to Reading Railroad (5)");
        assertEquals(1700, player1.getCapital(), "Should collect $200 for passing Go");
    }


    // GoToJailEffect Tests
    @Test
    void testGoToJailEffect() {
    	Player player1 = new Player("TestPlayer1");
        GoToJailEffect effect = new GoToJailEffect();
        player1.setPosition(5);
        player1.setJail(false);
        effect.execute(player1);
        assertEquals(10, player1.getPosition(), "Player should be at jail position (10)");
        assertEquals(true, player1.isJail(), "Player should be in jail");
    }



    // GetOutOfJailEffect Tests
    @Test
    void testGetOutOfJailEffectInJail() {
    	Player player1 = new Player("TestPlayer1");
        GetOutOfJailEffect effect = new GetOutOfJailEffect();
        player1.setPosition(10);
        player1.setJail(true);
        effect.execute(player1);
        assertEquals(10, player1.getPosition(), "Player position should not change");
        assertEquals(false, player1.isJail(), "Player should be out of jail");
    }



}