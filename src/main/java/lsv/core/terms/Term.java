package lsv.core.terms;

import java.util.Hashtable;
import java.util.Optional;
import java.util.function.Consumer;

public class Term {
    protected Hashtable<String, Double> parameters;
    protected String value;
    protected Consumer<Term> action = null;

    public Term(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setAction(Consumer<Term> action) {
        this.action = action;
    }

    public void act() {
        if (this.action != null) {
            this.action.accept(this);
        }
    }

    public Optional<Double> getParameter(String key)  {
        lazyInit();
        if (parameters.containsKey(key))
            return Optional.of(parameters.get(key));
        return Optional.empty();
    }

    public void setParameter(String key, double value) {
        lazyInit();
        parameters.put(key, value);
    }

    private void lazyInit() {
        if (parameters == null) {
            parameters = new Hashtable<>();
        }
    }

    @Override
    public boolean equals(Object o) {
        Term term = (Term) o;
        return o instanceof Term && value.equals(term.value) &&
                (action != null ? action.equals(term.action) : term.action == null);
    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + (action != null ? action.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Term{" +
                "value='" + value + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    public void setParameters(Hashtable<String, Double> map) {
        this.parameters = (Hashtable<String, Double>) map.clone();
    }

    public Term clone() {
        Term result = new Term(value);
        lazyInit();
        result.setAction(this.action);
        result.setParameters(this.parameters);
        return result;
    }

    public boolean paramExists(String key) {
        return parameters != null && parameters.containsKey(key);
    }

}
