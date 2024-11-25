package Classes.Instructions;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class Continue extends Instruction {
    public Continue(int line, int column) {
        super(line, column, TypeInst.CONTINUE);
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.j(env.continueLabel);
        }
    }
}