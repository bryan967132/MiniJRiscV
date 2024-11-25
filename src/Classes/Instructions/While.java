package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class While extends Instruction {
    private Expression condition;
    private Block block;
    public While(int line, int column, Expression condition, Block block) {
        super(line, column, TypeInst.WHILE);
        this.condition = condition;
        this.block = block;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("=========== WHILE ===========");
            final String startWhileLabel = gen.getLabel();
            final String prevContinueLabel = env.continueLabel;
            env.continueLabel = startWhileLabel;

            final String endWhileLabel = gen.getLabel();
            final String prevBreakLabel = env.breakLabel;
            env.breakLabel = endWhileLabel;

            gen.addLabel(startWhileLabel);
            gen.addComment("========= CONDICION =========");
            this.condition.exec(env, gen);
            env.popObject();
            gen.pop();
            gen.addComment("======= FIN CONDICION =======");
            gen.beq(R.T0.n, R.Z.n, endWhileLabel);
            gen.addComment("======= CUERPO WHILE ========");
            this.block.exec(env, gen);
            gen.j(startWhileLabel);
            gen.addLabel(endWhileLabel);

            env.continueLabel = prevContinueLabel;
            env.breakLabel = prevBreakLabel;
            gen.addComment("========= FIN WHILE =========");
        } else {
            this.block.exec(env, gen);
        }
    }
}