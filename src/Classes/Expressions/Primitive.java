package Classes.Expressions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Primitive extends Expression {
    public Object value;
    Type type;
    public Primitive(int line, int column, Object value, Type type) {
        super(line, column, TypeExp.PRIMITIVE);
        this.value = value;
        this.type = type;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("--------- Primitivo ---------");
            switch (this.type) {
                case INT:
                    gen.li(R.T0.n, this.value.toString());
                    gen.push();
                    env.pushObject(new StackValue(this.type, 4, env.depth));
                    break;
                case DOUBLE:
                    final String ieee754 = Classes.Generator.Utils.numberToF32(Float.parseFloat(this.value.toString()));
                    gen.li(R.T0.n, ieee754);
                    gen.push();
                    env.pushObject(new StackValue(this.type, 4, env.depth));
                    break;
                case BOOLEAN:
                    gen.li(R.T0.n, this.value.equals("true") ? 1 : 0);
                    gen.push();
                    env.pushObject(new StackValue(this.type, 4, env.depth));
                    break;
                case CHAR:
                    gen.li(R.T0.n, (int) getValueStr().charAt(0));
                    gen.push();
                    env.pushObject(new StackValue(this.type, 4, env.depth));
                    break;
                case STRING:
                    final ArrayList<Integer> stringArray = Classes.Generator.Utils.stringTo1ByteArray(getValueStr());
                    gen.push(R.T6.n);
                    stringArray.forEach(charCode -> {
                        gen.li(R.T0.n, charCode);
                        gen.sb(R.T0.n, R.T6.n);
                        gen.addi(R.T6.n, R.T6.n, 1);
                    });
                    env.pushObject(new StackValue(this.type, 4, env.depth));
                    break;
                default:
                    env.pushObject(new StackValue(Type.NULL, 4, env.depth));
                    break;
            }
            gen.addComment("------- Fin Primitivo -------");
        }
    }
    public String getValueStr() {
        return value.toString()
            .replace("\\n", "\n")
            .replace("\\t", "\t")
            .replace("\\\"", "\"")
            .replace("\\'", "\'")
            .replace("\\\\", "\\");
    }
    public String toString() {
        return "{" + "line: " + line + ", column: " + column + ", value: " + value + ", type: " + type + "}";
    }
}