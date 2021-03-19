package Logical;

import java.util.ArrayList;
import java.util.Random;


public abstract class Ghost extends Thing implements Stepable  {
    protected GhostColor color;
    private final double original_x, original_y;
    protected Direction direction = Direction.RIGHT;
    protected Direction next_direction = Direction.LEFT;
    protected int t = 0, time = 0;

    public Ghost( double x, double y, double unit_s) {
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

    public GhostColor getColor() {
        return color;
    }

    public Direction getDirection() {
        return direction;
    }

    public Direction getNext_direction() {
        return next_direction;
    }

    public void setNext_direction(Direction next_direction) {
        this.next_direction = next_direction;
    }

    public void step_one(){

        int speed = Game.game.getSpeed();

        switch (direction){
            case UP -> y -= speed ;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }
    }

    public boolean stepWhenCollison(ArrayList<Brick> bricks) {
        if (x % unit_s == 0 && next_direction == Direction.UP) {
            if (Collison(bricks, -1, 0)){
                return true;
            }
        }

        if (y % unit_s == 0 && next_direction == Direction.RIGHT) {
            if (Collison(bricks, 0, 1)){
                return true;
            }
        }

        if (x % unit_s == 0 && next_direction == Direction.DOWN) {
            if (Collison(bricks, 1, 0)){
                return true;
            }
        }

        return y % unit_s == 0 && next_direction == Direction.LEFT && (Collison(bricks, 0, -1));
    }

    public boolean Collison(ArrayList<Brick> bricks, double plus_y, double plus_x) {
        for (Brick brick : bricks) {
            if (brick.getY() == (y / unit_s) + plus_y && brick.getX() == x/unit_s + plus_x) {
                return true;
            }
        }
        direction = next_direction;
        return false;
    }

    public boolean countdown(){
        t++;
        if (t >= 22*60){
            t = 0;
            return true;
        }
        else{
            return false;
        }
    }


    public void new_direction(){
        boolean correct = false;
        Random rnd = new Random();
        int i;
        while(!correct) {
            i = rnd.nextInt(4);
            switch (i) {
                case 0 -> next_direction = Direction.RIGHT;
                case 1 -> next_direction = Direction.LEFT;
                case 2 -> next_direction = Direction.DOWN;
                case 3 -> next_direction = Direction.UP;
            }
            if(next_direction != direction){
                correct = true;
            }
        }
    }

    protected void new_direction2(){
        boolean correct = false;
        Direction prev_dericton = next_direction;
        Random rnd = new Random();
        int i;
        while(!correct) {
            i = rnd.nextInt(2);
            if (Game.game.getPacman().getX() - x < 0) {
                switch (i) {
                    case 0 -> new_directionUpOrDown();
                    case 1 -> next_direction = Direction.LEFT;
                }
            }
            else{
                switch (i) {
                    case 0 -> new_directionUpOrDown();
                    case 1 -> next_direction = Direction.RIGHT;
                }
            }
            if(next_direction != direction){
                correct = true;
            }
        }

        if(prev_dericton == next_direction){
            new_direction();
        }
    }

    public void new_directionUpOrDown() {
        if (Game.game.getPacman().getY() - y < 0) {
            next_direction = Direction.UP;
        } else {
            next_direction = Direction.DOWN;
        }
    }

    protected boolean random_move(){
        Random rnd = new Random();
        if(time++ > 200000000){
            time = 0;
        }
        int i = rnd.nextInt(17);
        int z = 0;
        switch (i) {
            case 0 -> z = 2;
            case 1 -> z = 3;
            case 2,3 -> z = 4;
            case 4,5 -> z = 5;
            case 6,7,8 -> z = 6;
            case 9,10,11-> z = 7;
            case 12,13 -> z = 8;
            case 14 -> z = 9;
            case 15 -> z = 10;
            case 16 -> z = 11;
        }

        if(time % (z*unit_s) == 0) {
            int j = rnd.nextInt(3);
            switch (j) {
                case 0 -> next_direction = direction;
                case 1, 2 -> {
                    return true;
                }
            }
        }
        return false;
    }

}
