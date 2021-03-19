package Logical;

import java.util.ArrayList;


public class OrangeGhost extends Ghost{
    public OrangeGhost( double x, double y, double unit_s) {
        super( x, y, unit_s);
        this.color = GhostColor.ORANGE;
    }

    @Override
    public void step() {
        ArrayList<Brick> bricks = Game.game.getBricks_list();
        if (stepWhenCollison(bricks)){
            new_direction2();
            return;
        }

        step_one();

        if(random_move()){
            new_direction2();
        }
    }
}
