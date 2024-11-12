package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class MainMethod extends Instruction {
    Expression method;
    public MainMethod(int line, int column, Expression method) {
        super(line, column, TypeInst.MAIN);
        this.method = method;
    }
    public void exec(Env env, RiscVGen gen) {
        this.method.exec(env, gen);
    }
}