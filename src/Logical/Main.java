package Logical;

import Visualization.PacManFrame;
import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String [] args){
        PacManFrame pacManFrame = new PacManFrame();
        try {
            pacManFrame.playSound("src\\Logical\\Sound\\start.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }

        java.util.Timer pac_timer = new Timer();
        pac_timer.schedule(new TimerTask() {
            @Override
            public void run(){
                 if(Game.game.getState() == GameState.GAME){
                     Game.game.step();
                 }
                pacManFrame.repaint();
            }
        }, 5000, 1000/120);

    }

}
