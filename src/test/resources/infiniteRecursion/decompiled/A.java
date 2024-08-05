// Decompiled original/A.class using procyon decompiler 0.6.0

public class A
{
    public static void main(final String[] array) {
        applyRules("Hello, World!", new StringBuffer());
    }

    private static <B extends Appendable> B applyRules(final String s, final B b) {
        return b;
    }

    protected static StringBuffer applyRules(final String s, final StringBuffer sb) {
        return applyRules(s, sb);
    }
}
