package Visualization;

import Logical.PacMan;

import java.awt.*;

public class PacManVis implements Drawable{
    private final PacMan pacman;
    private final double size;

    public PacManVis(PacMan pacman) {
        this.pacman = pacman;
        this.size = pacman.getUnit_s() - 1;
    }

    @Override
    public void draw(Graphics2D g) {
        int startAngle = 20;

        switch(pacman.getDirection()){
            case RIGHT -> startAngle = 20;
            case LEFT -> startAngle = 200;
            case DOWN -> startAngle = 290;
            case UP -> startAngle = 110;
        }

        g.setColor(Color.YELLOW);
        g.fillArc((int)pacman.getX(), (int)pacman.getY(), (int)size, (int)size, 0, 360);
        g.setColor(Color.BLACK);
        g.fillArc((int)pacman.getX(), (int)pacman.getY(),(int)size,(int)size, startAngle, -40);
    }
}
