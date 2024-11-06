package Classes.Utils;
public class Error {
    public int line;
    public int column;
    public TypeError type;
    public String description;
    public Error(int line, int column, TypeError type, String description) {
        this.line = line;
        this.column = column;
        this.type = type;
        this.description = description;
    }
    public String toString() {
        return "→ Error " + type.getValue() + ", " + this.description + ". " + line + ":" + column;
    }
}