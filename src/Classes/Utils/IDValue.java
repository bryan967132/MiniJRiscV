package Classes.Utils;
import Classes.Abstracts.Expression;
public class IDValue {
    public int line;
    public int column;
    public String id;
    public Expression value;
    public IDValue(int line, int column, String id, Expression value) {
        this.line = line;
        this.column = column;
        this.id = id;
        this.value = value;
    }
    public String toString() {
        return "IDValue(ID: " + id + ", Value: " + value + ")";
    }
}