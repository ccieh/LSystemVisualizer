package lsv.core.graphics;

import javafx.geometry.Point2D;
import javafx.util.Pair;

import java.util.Stack;

public class SimpleTurtle implements Turtle {
    private Point2D position = new Point2D(0, 0); //x, y
    private Point2D direction = new Point2D(0, 1); //cos, sin
    private Stack<Pair<Point2D, Point2D>> saves = new Stack<>();
    private GraphicsContext context;
    private boolean isPenDown = false;

    public SimpleTurtle(GraphicsContext context) {
        this.context = context;
    }
    public SimpleTurtle(GraphicsContext context, double x0, double y0) {
        this.context = context;
        this.setPosition(new Point2D(x0,y0));
    }
    public void rotate(double radians) {
        Point2D direction = getDirection();
        double newSin = direction.getX() * Math.cos(radians) + direction.getY() * Math.sin(radians);
        double newCos = direction.getY() * Math.cos(radians) - direction.getX() * Math.sin(radians);
        setDirection(new Point2D(newSin, newCos));
    }

    @Override
    public void push() {
        saves.push(new Pair<>(getPosition(), getDirection()));
    }

    @Override
    public void pop() {
        Pair<Point2D, Point2D> peek = saves.peek();
        setDirection(peek.getValue());
        setPosition(peek.getKey());
        saves.pop();
    }

    public void move(double distance) {
        if (isPenDown())
            context.moveTo(position.getX(), position.getY());
        setPosition(position.add(direction.multiply(distance)));
        if (isPenDown()) {
            context.lineTo(position.getX(), position.getY());
            context.stroke();
        }

    }

    @Override
    public void penUp() {
        isPenDown = false;
    }

    @Override
    public void penDown() {
        isPenDown = true;
    }

    @Override
    public void setWidth(double width) {
        context.setLineWidth(width);
    }

    @Override
    public void setColor(double r, double g, double b) {
        context.setLineColor(r,g,b);
    }

    @Override
    public void reset() {
        setPosition(new Point2D(0, 0)); //x, y
        setDirection(new Point2D(0, 1)); //cos, sin
        saves = new Stack<>();
        isPenDown = false;
    }

    @Override
    public String toString() {
        return "SimpleTurtle{" +
                "position=" + getPosition() +
                ", direction=" + getDirection() +
                '}';
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getDirection() {
        return direction;
    }

    public void setDirection(Point2D direction) {
        this.direction = direction;
    }

    public GraphicsContext getContext() {
        return context;
    }

    public boolean isPenDown() {
        return isPenDown;
    }
}