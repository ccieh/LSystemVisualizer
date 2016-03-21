package lsv.core.terms;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class TermTest {
    @Test
    public void testClone(){
        Term a = new Term("A");
        a.setParameter("A", 1);
        Term b = a.clone();
        b.setParameter("A", 0);
        Assert.assertThat(a.getParameter("A").get(), CoreMatchers.not(b.getParameter("A").get()));
    }
}
