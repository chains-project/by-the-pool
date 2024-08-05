// Example reproduced using https://arxiv.org/abs/2005.11315
// 4.3.2 Down Cast error (Infinite Recursion)

public class A {
    public static void main(String[] args) {
        applyRules("Hello, World!", new StringBuffer());
    }

    private static <B extends Appendable> B applyRules(final String s, final B buf) {
        return buf;
    }

    protected static StringBuffer applyRules(final String s, final StringBuffer buf) {
        return (StringBuffer) applyRules(s, (Appendable) buf);
    }
}