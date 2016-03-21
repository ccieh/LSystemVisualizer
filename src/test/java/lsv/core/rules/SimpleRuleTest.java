package lsv.core.rules;

import lsv.core.terms.Term;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

public class SimpleRuleTest {
    @Test
    public void testSingleApply(){
        Term a = new Term("a");
        LinkedList<Term> terms = new LinkedList<>();
        terms.add(a);
        SimpleRule r = new SimpleRule();
        a.setParameter("A", 0);
        r.setTargets(a);
        r.setResult(a);
        for (Term term : r.apply(terms.listIterator())) {
            Assert.assertTrue(term.equals(a));
            Assert.assertThat(term.getParameter("A"), CoreMatchers.not(Optional.<Double>empty()));
            term.setParameter("A", 1);
            Assert.assertThat(term.getParameter("A").get(), CoreMatchers.not(a.getParameter("A").get()));
        }
    }
    @Test
    public void testMultipleTargetsApply(){
        Term a = new Term("a");
        Term b = new Term("b");
        LinkedList<Term> terms = new LinkedList<>();
        terms.add(a);
        terms.add(b);
        SimpleRule r = new SimpleRule(new Term[]{a,b}, new Term[]{b,a});
        ListIterator<Term> iterator = terms.listIterator();
        List<Term> list = (List<Term>) r.apply(iterator);
        Assert.assertTrue(list.get(0).equals(b));
        Assert.assertTrue(list.get(1).equals(a));
        Assert.assertFalse(iterator.hasNext());
    }
}
