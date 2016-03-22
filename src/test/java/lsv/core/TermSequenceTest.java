package lsv.core;

import lsv.core.rules.Rule;
import lsv.core.rules.SimpleRule;
import lsv.core.terms.Term;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class TermSequenceTest {
    @Test
    public void testApplyRules(){
        Term a = new Term("A");
        Term b = new Term("B");
        TermSequence sequence = new TermSequence();
        sequence.setAxiom(a);
        Rule r = new SimpleRule(Collections.singletonList(a), Arrays.asList(b,a));
        sequence.applyRules(Collections.singletonList(r));
        Assert.assertThat(sequence.getTerms().get(0), CoreMatchers.is(b));
        Assert.assertThat(sequence.getTerms().get(1), CoreMatchers.is(a));
    }
}
