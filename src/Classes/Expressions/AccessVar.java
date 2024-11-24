package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Env.Pair;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class AccessVar extends Expression {
    private String id;
    public AccessVar(int line, int column, String id) {
        super(line, column, TypeExp.ACCESSVAR);
        this.id = id;
    }
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("-------- ACCESO VAR ---------");
            final Pair p = env.getObject(this.id);
            if(p != null) {
                if(env.isInsideFunction) {
                    gen.addi(R.T1.n, R.S0.n, -p.object.offset * 4);
                    gen.lw(R.T0.n, R.T1.n);
                    gen.push(R.T0.n);
                } else {
                    gen.addi(R.T0.n, R.SP.n, p.offset);
                    gen.lw(R.T1.n, R.T0.n);
                    gen.push(R.T1.n);
                }
                env.pushObject(new StackValue(p.object, null));
            } else {
                env.setError("Acceso a variable inexistente. '" + id +"'", line, column);
                env.pushObject(new StackValue(Type.NULL, 4, env.depth));
            }
            gen.addComment("------ FIN ACCESO VAR -------");
        }
    }
}