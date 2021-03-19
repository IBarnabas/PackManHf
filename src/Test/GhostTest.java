package Test;

import Logical.Direction;
import Logical.Game;
import Logical.RedGhost;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GhostTest {
    private RedGhost ghost;

    @Before
    public void setUp() {
        ghost = new RedGhost(4*50, 50,50);
    }

    @Test
    public void step_one() {
        ghost.step_one();
        Assert.assertTrue((ghost.getX() != ghost.getOriginal_x())  || (ghost.getY() != ghost.getOriginal_y()));
    }

    @Test
    public void stepWhenCollison() {
        ghost.setNext_direction(Direction.UP);
        Assert.assertTrue(ghost.stepWhenCollison(Game.game.getBricks_list()));
    }

    @Test
    public void collison() {
        Direction dir = ghost.getDirection();
        ghost.setNext_direction(Direction.UP);
        if(ghost.Collison(Game.game.getBricks_list(), -1,0)){
            Assert.assertEquals(ghost.getDirection(), dir);
        }
        else{
            Assert.assertEquals(ghost.getDirection(),ghost.getNext_direction());
        }
    }

    @Test
    public void countdown() {
        int i;
        for (i = 1; i <= 22*60; i++){
            if(ghost.countdown()){
                break;
            }
        }
        Assert.assertEquals(22*60,i);
        System.out.println(i);
    }

    @Test
    public void new_direction() {
        ghost.new_direction();
        Assert.assertNotEquals(ghost.getDirection(), ghost.getNext_direction());
    }

}