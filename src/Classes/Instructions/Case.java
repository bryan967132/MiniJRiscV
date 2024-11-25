package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.TypeInst;
public class Case extends Instruction {
    public Expression _case;
    public Block block;
    public StackValue caseEvaluate;
    public Case(int line, int column, Expression _case, Block block){
        super(line, column, TypeInst.CASE);
        this._case = _case;
        this.block = block;
    }
    public void exec(Env env, RiscVGen gen) {}
}