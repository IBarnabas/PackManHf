package Test;

import Logical.Direction;
import Logical.Game;
import Logical.PacMan;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PointTest {
    private PacMan pac;

    @Before
    public void setUp() {
        pac = new PacMan(3 * 50,  50, 50);
        pac.setNext_direction(Direction.RIGHT);
        Game.game.setSpeed(1);
    }

    @Test
    public void collisonPacman() {
        int before_points = pac.getPoints();
        pac.move();
        Assert.assertEquals(before_points + 1, pac.getPoints());
    }
}