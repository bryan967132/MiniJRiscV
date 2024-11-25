package Classes.Instructions;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class Break extends Instruction {
    public Break(int line, int column) {
        super(line, column, TypeInst.BREAK);
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.j(env.breakLabel);
        }
    }
}