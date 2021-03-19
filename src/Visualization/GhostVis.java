package Visualization;

import Logical.Ghost;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GhostVis implements Drawable{
    private String filename;
    private final Ghost ghost;

    public GhostVis(Ghost ghost) {
        this.ghost = ghost;
    }
    @Override
    public void draw(Graphics2D g) {
        switch (ghost.getColor()){
            case RED -> filename = "src\\Visualization\\Images\\red.png";
            case BLUE -> filename = "src\\Visualization\\Images\\blue.png";
            case ORANGE -> filename = "src\\Visualization\\Images\\orange.png";
        }

        BufferedImage im = null;
        try {
            im = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(im,(int)ghost.getX(), (int)ghost.getY(), (int) ghost.getUnit_s(), (int) ghost.getUnit_s(), null);
    }


}
