package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Expressions.Primitive;
import Classes.Generator.F;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
public class Print extends Instruction {
    private Expression exp;
    private boolean newLine;
    public Print(int line, int column, Expression exp, boolean newLine) {
        super(line, column, TypeInst.PRINT);
        this.exp = exp;
        this.newLine = newLine;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("=========== PRINT ===========");
            this.exp.exec(env, gen);
            final StackValue value = env.popObject();
            if(value.type == Type.NULL) {
                new Primitive(0, 0, "null", Type.STRING).exec(env, gen);;
                gen.pop(R.A0.n);
                gen.printString();
            } else if(value.type == Type.DOUBLE) {
                gen.popFloat(F.FA0.n);
                gen.printFloat();
            } else {
                gen.pop(R.A0.n);
                if(value.type == Type.STRING) {
                    gen.printString();
                } else if(value.type == Type.CHAR) {
                    gen.printChar();
                } else if(value.type == Type.INT) {
                    gen.printInt();
                } else {
                    gen.callBuiltin("printBoolean");
                }
            }
            if(newLine) {
                new Primitive(0, 0, '\n', Type.CHAR).exec(env, gen);
                env.popObject();
                gen.pop(R.A0.n);
                gen.printChar();
            }
            gen.addComment("========= FIN PRINT =========");
        }
    }
}