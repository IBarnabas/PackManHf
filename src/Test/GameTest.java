package Test;

import Logical.Game;
import Logical.GameState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GameTest {
    private final Game game = Game.game;

    @Before
    public void setUp() {
    }


    //Játék vége tesztelés
    @Test
    public void step() {
        game.getPacman().setLife(0);
        Assert.assertEquals(game.getState(), GameState.MENU);
    }


    @Test
    public void remove_points() {
        int list_size = game.getPoints_list().size();
        game.remove_points(game.getPoints_list().get(0));
        Assert.assertEquals(list_size - 1, game.getPoints_list().size());
    }


    @Test
    public void addRandomRedPoint() {
        int list_size = game.getPoints_list().size();
        game.addRandomRedPoint();
        Assert.assertEquals(list_size + 1, game.getPoints_list().size());
    }

}