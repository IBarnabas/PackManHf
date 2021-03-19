package Logical;

import java.util.ArrayList;

public class BlueGhost extends Ghost {
    public BlueGhost( double x, double y, double unit_s) {
        super( x, y, unit_s);
        this.color = GhostColor.BLUE;
    }

    @Override
    public void step() {
        ArrayList<Brick> bricks = Game.game.getBricks_list();
        if (stepWhenCollison(bricks)){
            new_direction2();
            return;
        }

        step_one();
    }
}

