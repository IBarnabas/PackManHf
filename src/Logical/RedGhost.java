package Logical;

import java.util.ArrayList;

public class RedGhost extends Ghost{
    public RedGhost( double x, double y, double unit_s) {
        super( x, y, unit_s);
        this.color = GhostColor.RED;
    }

    @Override
    public void step() {
        ArrayList<Brick> bricks = Game.game.getBricks_list();
        if (stepWhenCollison(bricks)){
            new_direction();
            return;
        }

        step_one();

        if(random_move()){
            new_direction();
        }
    }

}
