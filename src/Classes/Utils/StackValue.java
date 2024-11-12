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
    public StackValue(String id, Type type, int length, int offset) {
        this.type = type;
        this.length = length;
        this.offset = offset;
        this.id = id;
    }
    public StackValue(StackValue object, String id) {
        this.type = object.type;
        this.length = object.length;
        this.depth = object.depth;
        this.offset = object.offset;
        this.id = id;
    }
    public StackValue(StackValue object, int depth) {
        this.type = object.type;
        this.length = object.length;
        this.depth = depth;
        this.offset = object.offset;
        this.id = object.id;
    }
    public StackValue(StackValue object, int length, Type type) {
        this.type = type;
        this.length = length;
        this.depth = object.depth;
        this.offset = object.offset;
        this.id = object.id;
    }
    public StackValue(String id, int offset) {
        this.offset = offset;
        this.id = id;
    }
    public StackValue(Type type, int length) {
        this.type = type;
        this.length = length;
    }
}