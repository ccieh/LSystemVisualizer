package lsv.core.graphics;

public interface Turtle {
    void rotate(double radians);
    void push();
    void pop();
    void move(double distance);
    void penUp();
    void penDown();
    void setWidth(double width);
    void setColor(double r, double g, double b);
    void reset();
}
