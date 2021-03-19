package Logical;

import Visualization.PacManFrame;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class RedPoint extends Points{


    public RedPoint(double x, double y, double unit_s) {
        super(x, y, unit_s);
    }

    @Override
    public boolean isRed() {
        return true;
    }

    @Override
    public void CollisonPacman(PacMan pac) {
        if(!pac.isAttack()){
            try {
                PacManFrame.playSound("src\\Logical\\Sound\\apple.wav");
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
            Game.game.remove_points(this);
        }
        pac.setAttack(true);
    }
}
