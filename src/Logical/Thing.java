package Logical;

import java.io.Serializable;

public abstract class Thing implements Serializable {
    protected double x, y;
    protected double unit_s;

    public Thing(double x, double y, double unit_s) {
        this.x = x;
        this.y = y;
        this.unit_s = unit_s;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getUnit_s() {
        return unit_s;
    }


}
