package Classes.Env;
import Classes.Utils.StackValue;
public class Pair {
    public int offset;
    public StackValue object;
    public Pair(int offset, StackValue object) {
        this.offset = offset;
        this.object = object;
    }
}