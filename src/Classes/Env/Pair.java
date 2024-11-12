package Classes.Env;
import Classes.Utils.StackValue;
public class Pair {
    public int byteOffset;
    public StackValue object;
    public Pair(int byteOffset, StackValue object) {
        this.byteOffset = byteOffset;
        this.object = object;
    }
}