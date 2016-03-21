package lsv.core.rules;

import lsv.core.terms.Term;

import java.util.*;

public class SimpleRule implements Rule {

    final List<Term> targetSequence;
    final List<Term> resultSequence;

    public SimpleRule(Iterable<Term> targetSequence, Iterable<Term> resultSequence) {
        this.resultSequence = new LinkedList<>();
        this.targetSequence = new LinkedList<>();
        targetSequence.forEach(term -> this.targetSequence.add(term.clone()));
        resultSequence.forEach(term -> this.resultSequence.add(term.clone()));
    }
    public SimpleRule(Term[] targets, Term[] results){
        this.resultSequence = new LinkedList<>();
        this.targetSequence = new LinkedList<>();
        setResult(results);
        setTargets(targets);
    }
    public SimpleRule(){
        this.resultSequence = new LinkedList<>();
        this.targetSequence = new LinkedList<>();
    }
    public void setTargets(Term... targets){
        targetSequence.clear();
        for (Term term : targets) {
            targetSequence.add(term.clone());
        }
    }
    public void setResult(Term... results){
        resultSequence.clear();
        for (Term term : results) {
            resultSequence.add(term.clone());
        }
    }
    protected boolean isApplicable(ListIterator<Term> termIterator){
        boolean applicable = true;
        for (Term target : targetSequence) {
            applicable &= target.equals(termIterator.next());
        }
        if ( !applicable ){
            targetSequence.forEach(term -> termIterator.previous());
        }
        return applicable;
    }
    @Override
    public Iterable<Term> apply(ListIterator<Term> term) {
        if ( isApplicable(term) ){
            List<Term> resultList = new LinkedList<>();
            resultSequence.forEach(t -> resultList.add(t.clone()));
            return resultList;
        }
        else{
            return Collections.emptyList();
        }
    }
}
