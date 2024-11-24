package Classes.Expressions;
import Classes.Abstracts.Expression;
import Classes.Env.Env;
import Classes.Generator.RiscVGen;
import Classes.Instructions.Assign;
import Classes.Utils.Type;
import Classes.Utils.TypeExp;
public class IncDec extends Expression {
    private String id;
    private String sign;
    public IncDec(int line, int column, String id, String sign) {
        super(line, column, sign.equals("++") ? TypeExp.INC : TypeExp.DEC);
        this.id = id;
        this.sign = sign;
    }
    public void exec(Env env, RiscVGen gen) {
        new Assign(
            line,
            column,
            id,
            new Arithmetic(
                line,
                column,
                new AccessVar(line, column, id),
                sign.equals("++") ? "+" : "-",
                new Primitive(line, column, "1", Type.INT)
            )
        ).exec(env, gen);
        new AccessVar(line, column, id).exec(env, gen);
    }
}