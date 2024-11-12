package Classes.Expressions;
import java.util.ArrayList;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.R;
import Classes.Generator.RiscVGen;
import Classes.Utils.StackValue;
import Classes.Utils.TypeExp;
public class CallFunction extends Expression {
    public String id;
    ArrayList<Expression> args;
    public CallFunction(int line, int column, String id, ArrayList<Expression> args) {
        super(line, column, TypeExp.CALL_FUNC);
        this.id = id;
        this.args = args;
    }
    public void exec(Env env, RiscVGen gen) {
        gen.addComment("====== LLAMADA FUNCION ======");

        final String callReturnLabel = gen.getLabel();

         // 1. Guardar los argumentos
        gen.addi(R.SP.n, R.SP.n, -4 * 2);
        this.args.forEach(param -> {
            param.exec(env, gen);
            env.popObject();
        });
        gen.addi(R.SP.n, R.SP.n, 4 * (this.args.size() + 2));

        // Calcular la dirección del nuevo S0 en T1
        gen.addi(R.T1.n, R.SP.n, -4);

        // Guardar direccion de retorno
        gen.la(R.T0.n, callReturnLabel);
        gen.push(R.T0.n);

        // Guardar el S0
        gen.push(R.S0.n);
        gen.addi(R.S0.n, R.T1.n, 0);

        final int frameSize = env.functions.get(this.id).frameSize;
        gen.addi(R.SP.n, R.SP.n, -(frameSize - 2) * 4);

        // Saltar a la función
        gen.j(this.id);
        gen.addLabel(callReturnLabel);

        // Recuperar el valor de retorno
        final int returnSize = frameSize - 1;
        gen.addi(R.T0.n, R.S0.n, -returnSize * 4);
        gen.lw(R.A0.n, R.T0.n);

        // Regresar el S0 al contexto de ejecución anterior
        gen.addi(R.T0.n, R.S0.n, -4);
        gen.lw(R.S0.n, R.T0.n);

        // Regresar mi SP al contexto de ejecución anterior
        gen.addi(R.SP.n, R.SP.n, frameSize * 4);

        gen.push(R.A0.n);
        env.pushObject(new StackValue(env.functions.get(this.id).returnType, 4));
        gen.addComment("==== FIN LLAMADA FUNCION ====");
    }
}