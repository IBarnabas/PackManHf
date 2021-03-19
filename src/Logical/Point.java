package Logical;

import Visualization.PacManFrame;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Point extends Points{
    public Point(double x, double y, double unit_s) {
        super(x, y, unit_s);
    }

    @Override
    public boolean isRed() {
        return false;
    }

    public void CollisonPacman(PacMan pac){
        try {
            PacManFrame.playSound("src\\Logical\\Sound\\eat2.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        pac.setPoints(pac.getPoints()+1);
        Game.game.remove_points(this);
    }
}
