package lsv.core;

import lsv.core.rules.Rule;
import lsv.core.terms.Term;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;
public class TermSequence {
    LinkedList<Term> terms = new LinkedList<>();
    public void applyRules(Collection<Rule> rules){
        TermSequence sequence = new TermSequence();
        ListIterator<Term> termListIterator = terms.listIterator();
        while ( termListIterator.hasNext() ){
            int cur = termListIterator.nextIndex();
            for (Rule rule : rules) {
                sequence.add(rule.apply(termListIterator));
            }
            if (cur == termListIterator.nextIndex()) {
                sequence.add(termListIterator.next());
            }
        }
        this.terms = sequence.getTerms();
    }
    public void setAxiom(Term...terms){
        this.terms.clear();
        add(terms);
    }
    public void act(){
        terms.forEach(Term::act);
    }

    @Override
    public String toString() {
        return "TermSequence{" +
                "terms=" + terms +
                '}';
    }
    public LinkedList<Term> getTerms(){
        return terms;
    }

    public void add(Term...terms) {
        Collections.addAll(this.terms, terms);
    }
    public void add(Collection<Term> terms) {
        this.terms.addAll(terms);
    }
}
