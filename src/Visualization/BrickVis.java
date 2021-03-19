package Visualization;
import Logical.Brick;
import java.awt.*;

public class BrickVis implements Drawable {
    private final Brick brick;
    private final double size;

    public BrickVis(Brick brick) {
        this.brick = brick;
        size = brick.getUnit_s() - 1;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        double x = brick.getX() * brick.getUnit_s();
        double y = brick.getY() * brick.getUnit_s();

        g.drawRect((int)x,(int)y,(int) size, (int)size);
    }
}
