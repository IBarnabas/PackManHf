package Logical;

public abstract class Points extends Thing{
    public Points(double x, double y, double unit_s) {
        super(x, y, unit_s);
    }

    public abstract boolean isRed();
    public abstract void CollisonPacman(PacMan pac);
}
