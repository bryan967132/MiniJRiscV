package Classes.Env;
import Classes.Utils.Type;
public class Symbol {
    String id;
    public Type type;
    Type arrayType;
    public int position;
    public boolean isGlobal;
    public boolean isTrue;
    public Symbol(String id, Type type, Type arrayType, int position, boolean isGlobal, boolean isTrue) {
        this.id = id;
        this.type = type;
        this.arrayType = arrayType;
        this.position = position;
        this.isGlobal = isGlobal;
        this.isTrue = isTrue;
    }
}