package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class Return extends Expression {
    Expression exp;
    public Return(int line, int column, Expression exp) {
        super(line, column, TypeExp.RETURN);
        this.exp = exp;
    }
    @SuppressWarnings("unlikely-arg-type")
    public void exec(Env env, RiscVGen gen) {
        if(env.previous == null) {
            gen.addComment("---- DECLARACION FUNCION ----");
            if(this.exp != null) {
                this.exp.exec(env, gen);
                final StackValue v = env.popObject();
                if(v.type == Type.DOUBLE) {
                    gen.popFloat(R.A0.n);
                } else {
                    gen.pop(R.A0.n);
                }

                final int frameSize = env.functions.get(env.isInsideFunction).frameSize;
                final int returnOffest = frameSize - 1;
                gen.addi(R.T0.n, R.S0.n, -returnOffest * 4);
                gen.sw(R.A0.n, R.T0.n);
            }
            gen.j(env.returnLabel);
            gen.addComment("-- FIN DECLARACION FUNCION --");
        }
    }
}