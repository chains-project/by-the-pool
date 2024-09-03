import java.lang.reflect.Method;

public class A {
    private Method m1;
    private Method m2;
    private Method m3;

    @Override
    public boolean equals(Object obj) {
        try {
            return (boolean) m1.invoke(this, obj);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        try {
            return (int) m2.invoke(this);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        try {
            return (String) m3.invoke(this);
        } catch (Exception e) {
            return "";
        }
    }
}
