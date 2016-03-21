package lsv.core.rules;

import lsv.core.terms.Term;

import java.util.ListIterator;

public interface Rule {
    Iterable<Term> apply(ListIterator<Term> term);
}
