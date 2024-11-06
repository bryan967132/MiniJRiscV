package Classes.Utils;
import java.util.ArrayList;
import java.util.stream.Collectors;
public class Outs {
    public static ArrayList<Error> errors = new ArrayList<>();
    public static String getStringOuts() {
        String out = "";
        if(errors.size() > 0) {
            if(!out.equals("")) {
                out += "\n\n↳ ERRORES\n";
            } else {
                out += "↳ ERRORES\n";
            }
            out += errors.stream().map(Error::toString).collect(Collectors.joining("\n"));
        }
        return out;
    }
    public static Error newError(int line, int column, TypeError type, String description) {
        return new Error(line, column, type, description);
    }
    public static void resetOuts() {
        errors.clear();
    }
}