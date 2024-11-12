package Classes.Env;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;
import Classes.Utils.Metadata;
import Classes.Utils.StackValue;
public class Env {
    public Env previous;
    public String name;
    public int depth;
    public Stack<StackValue> stack;
    public String continueLabel;
    public String returnLabel;
    public String breakLabel;
    public Map<String, Metadata> functions;
    public boolean isInsideFunction;
    public int frameDclIndex;
    public ArrayList<StackValue> frame;
    public int localSize;
    public int baseOffset;
    public Env(Env previous, String name) {
        this.previous = previous;
        this.name = name;
        this.depth = 0;
        this.stack = new Stack<>();

        this.continueLabel = null;
        this.returnLabel = null;
        this.breakLabel = null;

        this.functions = new TreeMap<>();
        this.isInsideFunction = false;
        this.frameDclIndex = 0;

        // Frame Functions
        this.frame = new ArrayList<>();
        this.localSize = 0;
        this.baseOffset = 0;
    }
    public Env(Env previous, String name, int baseOffset) {
        this.previous = previous;
        this.name = name;
        this.depth = 0;
        this.stack = new Stack<>();

        this.continueLabel = null;
        this.returnLabel = null;
        this.breakLabel = null;

        this.functions = new TreeMap<>();
        this.isInsideFunction = false;
        this.frameDclIndex = 0;

        // Frame Functions
        this.frame = new ArrayList<>();
        this.localSize = 0;
        this.baseOffset = baseOffset;
    }
    public void pushObject(StackValue object) {
        this.stack.add(new StackValue(object, this.depth));
    }
    public void pushObjectFrame(StackValue object) {
        this.frame.add(new StackValue(object.id, this.baseOffset + this.localSize));
        this.localSize ++;
    }
    public StackValue popObject() {
        return this.stack.pop();
    }
    public StackValue getTopObject() {
        return this.stack.peek();
    }
    public void newScope() {
        this.depth ++;
    }
    public int endScope() {
        int byteOffset = 0;
        for(int i = this.stack.size() - 1; i >= 0; i --) {
            if(this.stack.get(i).depth == this.depth) {
                byteOffset += this.stack.get(i).length;
                this.stack.pop();
            } else {
                break;
            }
        }
        this.depth --;
        return byteOffset;
    }
    public void tagObject(String id) {
        this.stack.get(this.stack.size() - 1).id = id;
    }
    public Pair getObject(String id) {
        int byteOffset = 0;
        for(int i = this.stack.size() - 1; i >= 0; i --) {
            if(this.stack.get(i).id.equals(id)) {
                return new Pair(byteOffset, this.stack.get(i));
            }
            byteOffset += this.stack.get(i).length;
        }
        return null;
    }
    public StackValue getFrameLocal(int index) {
        return this.stack.stream()
            .filter(obj -> "local".equals(obj.type.getValue()))
            .collect(Collectors.toList()).get(index);
    }
}