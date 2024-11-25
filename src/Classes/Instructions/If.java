package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Statement;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class If extends Instruction {
    Expression condition;
    Block block;
    Statement except;
    public If(int line, int column, Expression condition, Block block, Statement except) {
        super(line, column, TypeInst.IF);
        this.condition = condition;        
        this.block = block;        
        this.except = except;        
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("============ IF =============");

            gen.addComment("========= CONDICION =========");

            this.condition.exec(env, gen);
            env.popObject();
            gen.pop();

            gen.addComment("======= FIN CONDICION =======");

            if(this.except != null) {
                final String elseLabel = gen.getLabel();
                final String endIfLabel = gen.getLabel();

                gen.beq(R.T0.n, R.Z.n, elseLabel);
                gen.addComment("------ RAMA VERDADERA -------");

                this.block.exec(env, gen);
                gen.j(endIfLabel);

                gen.addComment("-------- RAMA FALSA ---------");
                gen.addLabel(elseLabel);
                this.except.exec(env, gen);
                gen.addLabel(endIfLabel);
            } else {
                final String endIfLabel = gen.getLabel();

                gen.beq(R.T0.n, R.Z.n, endIfLabel);
                gen.addComment("------ RAMA VERDADERA -------");
                this.block.exec(env, gen);
                gen.addLabel(endIfLabel);
            }

            gen.addComment("========== FIN IF ===========");
        } else {
            this.block.exec(env, gen);
            if(this.except != null) this.except.exec(env, gen);
        }
    }
}