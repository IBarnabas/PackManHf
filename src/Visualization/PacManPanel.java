package Visualization;

import Logical.Game;
import Logical.GameState;

import javax.swing.*;
import java.awt.*;

public class PacManPanel extends JPanel {
    int line, unit;
    String attack_out = "ATTACK";
    public PacManPanel(int unit, int line) {
        this.unit = unit;
        this.line = line;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D frame_pac = (Graphics2D) g;

        MenuVis menu = new MenuVis(Game.game.getMenu());
        PacManVis pacman = new PacManVis(Game.game.getPacman());
        Font font = new Font(Font.SERIF, Font.PLAIN, (int) (0.6 * unit));

        this.setBackground(Color.BLACK);

        if (Game.game.getState() == GameState.MENU){
            menu.draw(frame_pac);
            return;
        }

        Game.game.getBricks_list().stream().map(brick -> new BrickVis(brick)).forEach(brickVis -> brickVis.draw(frame_pac));
        if (Game.game.getNewgame()) {
            ((Graphics2D) g).setComposite(setTransparety(3));
        }
        else{
            ((Graphics2D) g).setComposite(setTransparety(10));
        }
        pacman.draw(frame_pac);

        if (Game.game.getDeath_ghosts_list().size() > 0) {
            ((Graphics2D) g).setComposite(setTransparety(3));
            Game.game.getDeath_ghosts_list().stream().map(ghost -> new GhostVis(ghost)).forEach(ghostVis -> ghostVis.draw(frame_pac));
            ((Graphics2D) g).setComposite(setTransparety(10));
        }

        Game.game.getPoints_list().stream().map(point -> new PointsVis(point)).forEach(pointVis -> pointVis.draw(frame_pac));
        Game.game.getGhosts_list().stream().map(ghost -> new GhostVis(ghost)).forEach(ghostVis -> ghostVis.draw(frame_pac));


        String points_out = "points: " + Game.game.getPacman().getPoints();
        String life_out = "life: " + Game.game.getPacman().getLife();

        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString(points_out, line*unit +16, (int) (0.8*unit)); //0,8*size //1020 *2/%
        g.setColor(Color.RED);
        g.drawString(life_out, line*unit + 16, (int) (1.8*unit)); //954

        if (Game.game.getPacman().isAttack()){
            g.drawString(attack_out, line*unit + 24, (int) (2.8*unit));
        }

    }
    public AlphaComposite setTransparety(int i){
        float alpha = i * 0.1f;
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }
}
