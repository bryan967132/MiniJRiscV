package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Statement;
import Classes.Env.Env;
import Classes.Expressions.Primitive;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.InitializeFor;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class For extends Instruction {
    private InitializeFor inits;
    private Expression condition;
    private ArrayList<Statement> updates;
    private Block instructions;
    public For(int line, int column, InitializeFor inits, Expression condition, ArrayList<Statement> updates, Block instructions) {
        super(line, column, TypeInst.FOR);
        this.inits = inits;
        this.condition = condition;
        this.updates = updates;
        this.instructions = instructions;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("============ FOR ============");

            final String startForLabel = gen.getLabel();

            final String endForLabel = gen.getLabel();
            final String prevBreakLabel = env.breakLabel;
            env.breakLabel = endForLabel;

            final String incrementLabel = gen.getLabel();
            final String prevContinueLabel = env.continueLabel;
            env.continueLabel = incrementLabel;

            env.newScope();

            if(inits != null) {
                if(inits.inits != null) {
                    inits.inits.exec(env, gen);
                } else if(inits.assigns != null) {
                    for(Instruction assign : inits.assigns) {
                        assign.exec(env, gen);
                    }
                }
            }

            gen.addLabel(startForLabel);
            gen.addComment("========= CONDICION =========");
            (condition != null ? condition : new Primitive(line, column, "true", Type.BOOLEAN)).exec(env, gen);
            gen.pop();
            env.popObject();
            gen.addComment("======= FIN CONDICION =======");
            gen.beq(R.T0.n, R.Z.n, endForLabel);

            gen.addComment("======== CUERPO FOR =========");
            instructions.exec(env, gen);

            gen.addLabel(incrementLabel);
            if(updates != null) {
                for(Statement update : updates) {
                    update.exec(env, gen);
                }
            }
            gen.j(startForLabel);

            gen.addLabel(endForLabel);

            gen.addComment("------ REDUCIENDO PILA ------");
            final int bytesToRemove = env.endScope();

            if(bytesToRemove > 0) {
                gen.addi(R.SP.n, R.SP.n, bytesToRemove);
            }

            env.continueLabel = prevContinueLabel;
            env.breakLabel = prevBreakLabel;

            gen.addComment("========== FIN FOR ==========");
        } else {
            this.instructions.exec(env, gen);
        }
    }
}