package lsv.core.terms;

import lsv.core.graphics.Turtle;

public class TurtleTerm extends Term {
    private final Turtle turtle;

    @FunctionalInterface
    public interface TurtleConsumer {
        void accept(Term term, Turtle turtle);
    }

    public TurtleTerm(String value, Turtle turtle, TurtleConsumer turtleConsumer) {
        super(value);
        this.turtle = turtle;
        this.action = term -> turtleConsumer.accept(term, turtle);
    }
}