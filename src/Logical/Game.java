package Logical;

import Visualization.PacManFrame;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Stepable, Serializable{
    private PacMan pacman = null;
    private final Menu menu = new Menu();
    private final ArrayList<Brick> bricks_list = new ArrayList();
    private final ArrayList<Points> points_list = new ArrayList();
    private final ArrayList<Ghost> ghosts_list = new ArrayList();
    private final ArrayList<Ghost> death_ghosts_list = new ArrayList();
    private GameState state = GameState.MENU;
    private int wait = 0, line = 0, collumn = 0, speed;
    private final double unit_size;
    private boolean newgame = false;

    //Singleton class
    public static Game game = new Game(50, 1);

    private Game(double unit_size, int speed){
        this.unit_size = unit_size;
        this.speed = speed;
        try {
            reading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PacMan getPacman() {
        return pacman;
    }

    public ArrayList<Ghost> getGhosts_list() {
        return ghosts_list;
    }

    public ArrayList<Ghost> getDeath_ghosts_list() {
        return death_ghosts_list;
    }

    public ArrayList<Brick> getBricks_list() {
        return bricks_list;
    }

    public ArrayList<Points> getPoints_list() {
        return points_list;
    }

    public int getLine() {
        return line;
    }

    public int getCollumn() {
        return collumn;
    }

    public boolean getNewgame() {
        return newgame;
    }

    public void setNewgame(boolean newgame) {
        this.newgame = newgame;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Menu getMenu() {
        return menu;
    }

    public double getUnit_size() {
        return unit_size;
    }

    @Override
    public void step() {
        if(pacman.getLife() == 0){
            game.state = GameState.MENU;
            return;
        }

        if (delayNewGame())
            return;

        EndRound();
        pacman.move();
        ghostStep();

    }

    public void reading() throws IOException{
        line = 0;
        collumn = 0;
        FileReader fr = new FileReader("src\\Logical\\field.txt");
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            String in = br.readLine();
            if (in == null) {
                break;
            }
            for(collumn = 0; collumn < in.length(); collumn++){
                char c = in.charAt(collumn);
                switch(c){
                    case 'F' -> bricks_list.add(new Brick(collumn, line, unit_size));
                    case 'P' -> {
                        pacman = new PacMan(collumn*unit_size,line*unit_size, unit_size);
                        points_list.add(new Point(collumn, line,unit_size));
                    }
                    case 'R' -> ghosts_list.add(new RedGhost( collumn * unit_size, line * unit_size, unit_size));
                    case 'B' -> ghosts_list.add(new BlueGhost(collumn*unit_size,line*unit_size, unit_size));
                    case 'O' -> ghosts_list.add(new OrangeGhost(collumn*unit_size,line*unit_size, unit_size));
                    case 'p' -> points_list.add(new Point(collumn, line, unit_size));
                    case 'x' -> points_list.add(new RedPoint(collumn, line,unit_size));
                }
            }
            line++;
        }
        br.close();
    }

    public void rebornGhost(){
        ghosts_list.add(death_ghosts_list.get(0));
        death_ghosts_list.remove(0);
    }

    public void remove_points(Points point){
        points_list.remove(point);
    }

    public void remove_ghost(int index){
        death_ghosts_list.add(death_ghosts_list.size(), ghosts_list.get(index));
        ghosts_list.remove(index);
    }

    public void addRandomRedPoint(){
        boolean correct = false;
        Random rnd = new Random();
        int x = 0, y = 0;

        while(!correct) {
            x = rnd.nextInt(collumn);
            y = rnd.nextInt(line);
            correct = true;
            for (Brick brick : bricks_list) {
                if (brick.getX() == x && brick.getY() == y) {
                    correct = false;
                    break;
                }
            }
            for (Points point : points_list) {
                if (point.getX() == x && point.getY() == y) {
                    correct = false;
                    break;
                }
            }
        }
        points_list.add(new RedPoint(x, y,unit_size));
    }

    public boolean delayNewGame() {
        if (newgame){
            wait++;
            if (wait >= 3*120){
                wait = 0;
                rebornPacman();
                newgame = false;
            }
            return true;
        }
        return false;
    }

    public void ghostStep() {
        for (Ghost ghost : ghosts_list) {
            ghost.step();
        }
        if (death_ghosts_list.size() > 0) {
            for (Ghost ghost : death_ghosts_list) {
                if (ghost.countdown()) {
                    rebornGhost();
                    return;
                }
            }
        }
    }

    public void rebornPacman() {
        pacman.setX(pacman.getOriginal_x());
        pacman.setY(pacman.getOriginal_y());

        if (death_ghosts_list.size() > 0) {
            for (int i = 0; i < death_ghosts_list.size(); i++) {
                rebornGhost();
            }
        }

        for (Ghost ghost : ghosts_list){
            ghost.setX(ghost.getOriginal_x());
            ghost.setY(ghost.getOriginal_y());
        }
    }

    public void EndRound(){
        int db = 0;
        for (Points point: points_list){
            if(!point.isRed()){
                db++;
            }
        }

        if(db == 0) {
            NewRound();
        }
    }

    public void NewRound() {
        try {
            PacManFrame.playSound("src\\Logical\\Sound\\newround.wav");
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }

        int point = pacman.getPoints();
        int life = pacman.getLife();

        bricks_list.clear();
        points_list.clear();
        ghosts_list.clear();
        death_ghosts_list.clear();

        try {
            reading();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pacman.setLife(life);
        pacman.setPoints(point);
        newgame = true;
    }

    public void resetGame(){
        bricks_list.clear();
        points_list.clear();
        ghosts_list.clear();
        death_ghosts_list.clear();
        pacman = null;

        try {
            reading();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadField() throws  IOException, ClassNotFoundException{
        final ObjectInputStream in = new ObjectInputStream(new FileInputStream("jatek"));
        game =(Game)in.readObject();
    }

    public void saveField() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("jatek"));
        out.writeObject(game);
    }
}
