package Logical;
import Visualization.PacManFrame;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;
import static java.lang.Math.abs;


public class PacMan extends Thing{
    private final double original_x, original_y;
    private boolean attack = false;
    private int points = 0;
    private int life = 3;
    private Direction direction = Direction.RIGHT;
    private Direction next_direction = Direction.RIGHT;
    private int t = 0;

    public PacMan(double x, double y, double unit_s) {
        super(x, y, unit_s);
        this.original_x = x;
        this.original_y = y;
    }


    public double getOriginal_x() {
        return original_x;
    }

    public double getOriginal_y() {
        return original_y;
    }

    public boolean isAttack() {
        return attack;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setNext_direction(Direction next_direction) {
        this.next_direction = next_direction;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    public void move(){
        eat_points();
        coll_Ghost();

        if (x % unit_s == 0 && next_direction == Direction.UP) {
            if (Collision(Game.game.getBricks_list(), -1, 0))return;
        }

        if (y % unit_s == 0 && next_direction == Direction.RIGHT) {
            if (Collision(Game.game.getBricks_list(), 0, 1)) return;
        }

        if (x % unit_s == 0 && next_direction == Direction.DOWN) {
            if (Collision(Game.game.getBricks_list(), 1, 0)) return;
        }

        if (y % unit_s == 0 && next_direction == Direction.LEFT) {
            if (Collision(Game.game.getBricks_list(), 0, -1)) return;
        }


        int speed = Game.game.getSpeed();
        switch(direction){
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }

        if(attack){
            t++;
            if(t >= 18*30){
                attack = false;
                t = 0;
                Game.game.addRandomRedPoint();
            }
        }

    }

    public boolean Collision(ArrayList<Brick> bricks, int plus_x, int plus_y) {
        for (Brick brick : bricks) {
            if (brick.getY() == (y / unit_s) + plus_x && brick.getX() == (x/unit_s) + plus_y) {
                next_direction = direction;
                return true;
            }
        }
        direction = next_direction;
        return false;
    }

    public void eat_points(){
        ArrayList<Points> points = Game.game.getPoints_list();
        if (x % unit_s == 0) {
            for (Points point : points) {
                if (point.getY() == (y / unit_s) && point.getX() == x/unit_s) {
                    point.CollisonPacman(this);
                    return;
                }
            }
        }

    }

    public void coll_Ghost(){
        ArrayList<Ghost> ghosts = Game.game.getGhosts_list();
        for (Ghost ghost : ghosts) {
            if ((abs(ghost.getY() - this.y) < unit_s && ghost.getX() == this.x) || (abs(ghost.getX() - this.x) < unit_s && ghost.getY() == this.y)){
                if(attack) {
                    try {
                        PacManFrame.playSound("src\\Logical\\Sound\\ghostdeath.wav");
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    Game.game.remove_ghost(ghosts.indexOf(ghost));
                    this.points += 10;
                    this.attack = false;
                    t = 0;
                    Game.game.addRandomRedPoint();
                }
                else{
                    this.life--;
                    try {
                        PacManFrame.playSound("src\\Logical\\Sound\\death.wav");
                    } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                        e.printStackTrace();
                    }
                    Game.game.setNewgame(true);
                }
                return;
            }
        }
    }
}
