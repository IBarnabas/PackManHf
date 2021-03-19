package Logical;

import java.io.IOException;
import java.io.Serializable;

public class Menu implements Serializable {
   private MenuState state = MenuState.MAIN;

    public MenuState getState() {
        return state;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public void menu_step(){
        switch(state){
            case START_GAME -> {
                Game.game.resetGame();
                Game.game.setState(GameState.GAME);
                Game.game.getMenu().setState(MenuState.MAIN);
            }
            case LOAD_GAME -> {
                try {
                    Game.game.loadField();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Game.game.setState(GameState.GAME);
                Game.game.getMenu().setState(MenuState.MAIN);
            }
        }

    }
}
