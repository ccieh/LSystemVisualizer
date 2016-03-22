package lsv.core.rules;

import lsv.core.terms.Term;

import java.util.Collection;
import java.util.ListIterator;

public interface Rule {
    Collection<Term> apply(ListIterator<Term> term);
}
