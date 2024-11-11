package Classes.Abstracts;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
import Classes.Utils.TypeSent;
public abstract class Instruction extends Statement {
    public TypeInst typeInst;
    public Instruction(int line, int column, TypeInst typeInst) {
        super(line, column, TypeSent.INSTRUCTION);
        this.typeInst = typeInst;
    }
    public abstract void exec(Env env, RiscVGen gen);
}