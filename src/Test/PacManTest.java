package Test;

import Logical.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PacManTest {
    private PacMan pac;

    @Before
    public void setUp(){
        pac = new PacMan(3*50, 50,50);
        pac.setNext_direction(Direction.RIGHT);
        Game.game.setSpeed(1);
    }


    @Test
    public void move() {
        double before_x = pac.getX(), before_y = pac.getY();
        pac.move();
        Assert.assertEquals(before_x  + Game.game.getSpeed() ,pac.getX(),0);
        Assert.assertEquals(before_y,pac.getY(),0);
    }


    @Test
    public void collision() {
        pac.move();
        Assert.assertEquals(pac.getDirection(), Direction.RIGHT);
        pac.setNext_direction(Direction.UP);
        pac.move();
        Assert.assertEquals(pac.getDirection(), Direction.RIGHT);
    }



}