package Visualization;

import Logical.Points;

import java.awt.*;

public class PointsVis implements Drawable{
    private final Points point;
    private final double size;

    public PointsVis(Points point) {
        this.point = point;
        this.size = point.getUnit_s();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);

        double x = point.getX() * size + (19 * (size/50)) ;
        double y = point.getY() * size + (19 * (size/50));

        if (point.isRed()){
            g.setColor(Color.RED);
        }
        g.fillArc((int)x,(int)y,12,12,0,360);
    }
}
