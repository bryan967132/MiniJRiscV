package Classes.Utils;
public class StackValue {
    public Type type;
    public int length;
    public int depth;
    public int offset;
    public String id;
    public StackValue(Type type, int length, int depth) {
        this.type = type;
        this.length = length;
        this.depth = depth;
    }
    public StackValue(StackValue object, String id) {
        this.type = object.type;
        this.length = object.length;
        this.depth = object.depth;
        this.id = id;
    }
}