package Visualization;

import Logical.Game;
import Logical.Direction;
import Logical.GameState;
import Logical.MenuState;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class PacManFrame extends JFrame {

    public PacManFrame(){
        super("Pac-Man");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int size = (int) Game.game.getPacman().getUnit_s();
        int line = Game.game.getLine();
        int column = Game.game.getCollumn();
        int width = (column + 4) * size + 16; //18+4
        int height = line * size + 39;

        setSize(width, height);
        setResizable(false);


        PacManPanel panel = new PacManPanel(size, column);
        add(panel);


        //irányítás
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (Game.game.getState()){
                    case GAME -> {
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            Game.game.getPacman().setNext_direction(Direction.DOWN);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            Game.game.getPacman().setNext_direction(Direction.DOWN);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            Game.game.getPacman().setNext_direction(Direction.UP);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            Game.game.getPacman().setNext_direction(Direction.LEFT);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            Game.game.getPacman().setNext_direction(Direction.RIGHT);
                        }

                        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                            Game.game.setState(GameState.MENU);
                            try {
                                Game.game.saveField();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                    case MENU -> {
                        switch (Game.game.getMenu().getState()){
                            case MAIN -> {
                                if(e.getKeyCode() == KeyEvent.VK_1){
                                    Game.game.getMenu().setState(MenuState.SET_LEVEL);
                                }
                                if(e.getKeyCode() == KeyEvent.VK_2){
                                    Game.game.getMenu().setState(MenuState.LOAD_GAME);
                                }
                                if(e.getKeyCode() == KeyEvent.VK_3) {
                                    Game.game.getMenu().setState(MenuState.START_GAME);
                                }
                            }
                            case SET_LEVEL-> {
                                if(e.getKeyCode() == KeyEvent.VK_1){
                                    Game.game.setSpeed(1);
                                    Game.game.getMenu().setState(MenuState.MAIN);
                                }
                                if(e.getKeyCode() == KeyEvent.VK_2){
                                    Game.game.setSpeed(2);
                                    Game.game.getMenu().setState(MenuState.MAIN);
                                }
                            }
                        }
                        Game.game.getMenu().menu_step();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        setVisible(true);

    }

    public static void playSound(String filename) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File file = new File(filename);
        AudioInputStream input = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(input);
        clip.start();
    }


}

