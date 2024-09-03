import java.lang.reflect.Method;

public class B {
    private Method m2;
    private Method m3;
    private Method m1;

    @Override
    public boolean equals(Object obj) {
        try {
            return (boolean) m2.invoke(this, obj);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        try {
            return (int) m3.invoke(this);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public String toString() {
        try {
            return (String) m1.invoke(this);
        } catch (Exception e) {
            return "";
        }
    }
}
