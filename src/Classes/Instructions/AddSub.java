package Classes.Instructions;
import Classes.Abstracts.Expression;
import Classes.Abstracts.Instruction;
import Classes.Env.Env;
import Classes.Expressions.AccessVar;
import Classes.Expressions.Arithmetic;
import Classes.Generator.RiscVGen;
import Classes.Utils.TypeInst;
public class AddSub extends Instruction {
    private String id;
    private String sign;
    private Expression exp;
    public AddSub(int line, int column, String id, String sign, Expression exp) {
        super(line, column, sign.equals("+=") ? TypeInst.ADD : TypeInst.SUB);
        this.id = id;
        this.sign = sign;
        this.exp = exp;
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
                sign.equals("+=") ? "+" : "-",
                exp
            )
        ).exec(env, gen);
    }
}