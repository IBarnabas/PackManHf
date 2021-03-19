package Visualization;

import Logical.Game;
import Logical.Menu;

import java.awt.*;



public class MenuVis implements Drawable {
    private final Menu menu;
    private final double unit = Game.game.getUnit_size();
    private final int collumn = Game.game.getCollumn();

    public MenuVis(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void draw(Graphics2D g) {
        Font font = new Font(Font.SERIF, Font.PLAIN, (int) (0.6*unit));
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("PAC-MAN", (float) ((collumn /2)*unit +20), (int) (1.8*unit));
        g.setColor(Color.WHITE);

        switch (menu.getState()){
            case MAIN -> main_draw(g);
            case SET_LEVEL -> level_draw(g);
        }
    }

    private void level_draw(Graphics2D g) {
        g.drawString("Set Level", (float) ((collumn /2)*unit) + 25, (int) (3.3*unit));
        g.drawString("Normal (press 1)", (float) ((collumn /2)*unit -15), (int) (4.3*unit));
        g.drawString("Hard (press 2)", (float) ((collumn /2)*unit -3), (int) (5.3*unit));
    }

    private void main_draw(Graphics2D g) {

        g.drawString("Set Level (press 1)", (float) ((collumn /2)*unit - 14), (int) (3.3*unit));
        g.drawString("Load Game (press 2)", (float) ((collumn /2)*unit - 22), (int) (4.8*unit));
        g.drawString("Start Game (press 3)", (float) ((collumn /2)*unit -18), (int) (6.2*unit));
    }


}
