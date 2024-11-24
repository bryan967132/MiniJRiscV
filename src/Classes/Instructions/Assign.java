package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Env.Pair;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeInst;
public class Assign extends Instruction {
    private String id;
    private Expression value;
    public Assign(int line, int column, String id, Expression value) {
        super(line, column, TypeInst.ASSIGN);
        this.id = id;
        this.value = value;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("-------- ASIGNACION ---------");
            this.value.exec(env, gen);
            gen.pop();
            final StackValue valor = env.popObject();
            final Pair p = env.getObject(this.id);

            if (env.isInsideFunction) {
                gen.addi(R.T1.n, R.S0.n, -p.object.offset * 4);
                gen.sw(R.T0.n, R.T1.n);
                gen.addComment("------ FIN ASIGNACION -------");
                return;
            }

            gen.addi(R.T1.n, R.SP.n, p.offset);
            gen.sw(R.T0.n, R.T1.n);

            gen.push();
            env.pushObject(valor);

            final StackValue pop = env.popObject();
            if(pop.type == Type.DOUBLE) {
                gen.popFloat();
            } else {
                gen.pop();
            }
            gen.addComment("------ FIN ASIGNACION -------");
        }
    }
}