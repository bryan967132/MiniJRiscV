package Classes.Instructions;
import java.util.ArrayList;
import Classes.Abstracts.Instruction;
import Classes.Abstracts.Statement;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class Block extends Instruction {
    ArrayList<Statement> instructions;
    public Block(int line, int column, ArrayList<Statement> instructions) {
        super(line, column, TypeInst.BLOCK);
        this.instructions = instructions;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("========== BLOQUE ===========");

            env.newScope();
            this.instructions.forEach(i -> i.exec(env, gen));
            gen.addComment("------ REDUCIENDO PILA ------");
            final int bytesToRemove = env.endScope();

            if(bytesToRemove > 0) {
                gen.addi(R.SP.n, R.SP.n, bytesToRemove);
            }

            gen.addComment("======== FIN BLOQUE =========");
        } else {
            this.instructions.forEach(i -> i.exec(env, gen));
        }
    }
}